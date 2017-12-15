package com.agorda.wow.ui.screen;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.util.Log;

import com.agorda.wow.gameElements.db_constants.ObjectCreation;
import com.agorda.wow.gameElements.enemy.Enemy;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.gameElements.player.PlayerState;
import com.agorda.wow.services.WalkingService;
import com.agorda.wow.ui.GameScreenManager;
import com.agorda.wow.ui.MainView;
import com.agorda.wow.ui.ui_element.ScrollingBackground;
import com.agorda.wow.ui.ui_element.player_enemies.EnemySprite;
import com.agorda.wow.ui.ui_element.player_enemies.PlayerSprite;
import com.agorda.wow.ui.ui_element.player_status.PlayerStatus;
import com.agorda.wow.util.DatabaseHelper;
import com.agorda.wow.util.GameSave;
import com.agorda.wow.util.NotificationCreator;
import com.agorda.wow.util.NotificationUtil;
import com.agorda.wow.util.StepCounter;
import com.agorda.wow.util.StepCounterListener;

import java.util.Random;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Timothy on 13/12/2017.
 */

public class AdventureScreen implements Screen, StepCounterListener {
    private GameScreenManager gsm;
    private Context context;

    //back end
    private StepCounter stepCounter;
    private Player player;
    private Enemy enemy;
    private NotificationCreator notificationCreator;

    private SharedPreferences dsp;
    private DatabaseHelper databaseHelper;

    private Random r;
    private int chance;
    private boolean isVisible; //if the activity is onResume or onStop

    //ui
    private ScrollingBackground background;
    private double backgroundScrollTimer;

    private PlayerSprite playerSprite;
    private EnemySprite enemySprite;

    private PlayerStatus playerStatus;

    private boolean encountered;

    public AdventureScreen(Context context, GameScreenManager gsm) {
        this.gsm = gsm;
        this.context = context;
        background = new ScrollingBackground(context, 250 * MainView.SCALE_HEIGHT);
        init();
        initHandler();
    }

    @Override
    public void init() {
        initBackend();
        initUI();
    }

    public void initUI(){
        /*
            create player status bottom here
         */

        playerSprite = new PlayerSprite(context, 150 * MainView.SCALE_WIDTH, background.getBottom(), 0.3f * MainView.SCALE_WIDTH, 0.30f * MainView.SCALE_WIDTH);
        enemySprite = new EnemySprite(context, (MainView.WIDTH - 150) * MainView.SCALE_WIDTH, background.getBottom(), 0.3f * MainView.SCALE_WIDTH, 0.30f * MainView.SCALE_WIDTH);
        if(encountered)
            enemySprite.setEnemySprite(enemy.getId());

        /*
            create header here
         */
        playerStatus = new PlayerStatus(context, 0, background.getBottom());
    }

    public void initBackend(){
        //sensors
        SensorManager sensorManager = (SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        stepCounter = new StepCounter(sensorManager, this);

        //back end elements
        dsp = PreferenceManager.getDefaultSharedPreferences(context);
        databaseHelper = new DatabaseHelper(context);

        //load player
        player = GameSave.loadPlayer(dsp,databaseHelper);
        //temporary no button yet, by default the player is walking
        if(player.getState() != PlayerState.ENCOUNTERED){
            player.setState(PlayerState.WALKING);
            GameSave.changePlayerState(dsp.edit(), player.getState());
            stepCounter.start();
            encountered = false;
        }
        if(player.getState() == PlayerState.WALKING) {
            stepCounter.start();
            encountered = false;
        }else if(player.getState() == PlayerState.ENCOUNTERED) {
            enemy = GameSave.loadEnemy(dsp, databaseHelper);
            encountered = true;
        }

        //notification
        NotificationUtil.setUp((NotificationManager)context.getSystemService(NOTIFICATION_SERVICE));
        notificationCreator = new NotificationCreator(context);

        //local variables
        r = new Random();
        chance = 0;
        isVisible = true;
        backgroundScrollTimer = 0;
    }

    @Override
    public void initHandler() {
        /*
        for walk button press
            if(isChecked){
                    //is walking
                    player.setState(PlayerState.WALKING);
                    stepCounter.start();
                }else{
                    //not walking
                    player.setState(PlayerState.CAMPING);
                    stepCounter.stop();
                }
                GameSave.changePlayerState(dsp.edit(), player.getState());
         */
    }

    @Override
    public void render(Canvas canvas) {
        background.draw(canvas);
        playerSprite.draw(canvas);
        if(encountered) {
            enemySprite.draw(canvas);
        }

        playerStatus.draw(canvas);
    }

    @Override
    public void update(double deltaTime) {
        background.update();
        if(backgroundScrollTimer > 0) {
            backgroundScrollTimer -= deltaTime;
        }else{
            background.setEnabled(false);
        }

    }

    @Override
    public void resume() {
        isVisible = true;

        NotificationUtil.cancelAll();
        if(stepCounter.isActive()){
            //stop walking service
            Intent services = new Intent(context, WalkingService.class);
            context.stopService(services);
        }

        //change activity based on the current state of the player
        if(player.getState() == PlayerState.ENCOUNTERED){
            encountered = true;
            //gsm.pop(new FightScreen(context, gsm));
        }else if(player.getState() == PlayerState.TOWN){
            gsm.pop(new TownScreen(context, gsm));
        }
    }

    @Override
    public void pause() {
        isVisible = false;
        if(stepCounter.isActive()) {
            NotificationUtil.notify(NotificationUtil.NOTIFICATION_WALKING, notificationCreator.walkNotifcation(player));
            //start walking service
            Intent services = new Intent(context, WalkingService.class);
            context.startService(services);
        }
    }

    @Override
    public void onStep() {
        chance++;

        if(player.walk()){
            backgroundScrollTimer = 250;
            background.setEnabled(true);

            if(!isVisible)
                NotificationUtil.notify(NotificationUtil.NOTIFICATION_WALKING, notificationCreator.walkNotifcation(player));

            //encountered an enemy
            if(encountered()) {
                player.setState(PlayerState.ENCOUNTERED);
                GameSave.changeStepCount(dsp.edit(), player.getDestination().getSteps());
                GameSave.changePlayerState(dsp.edit(), player.getState());

                //create random enemy
                enemy = databaseHelper.getEnemy(r.nextInt(ObjectCreation.ENEMY_COUNT - 1));
                //enemy sprite
                enemySprite.setEnemySprite(enemy.getId());
                encountered = true;
                //stop background movement
                background.setEnabled(false);
                backgroundScrollTimer = 0;

                //save enemy to the pref
                GameSave.saveEnemy(dsp.edit(), enemy);

                stepCounter.stop();

                if(!isVisible) {
                    NotificationUtil.cancel(NotificationUtil.NOTIFICATION_WALKING);
                    NotificationUtil.notify(NotificationUtil.NOTIFICATION_ENEMY, notificationCreator.fightNotification(player, enemy));
                }
            }
            //**adventure_tv_steps.setText("Steps: " + player.getDestination().getSteps());
            GameSave.changeStepCount(dsp.edit(), player.getDestination().getSteps());
        }else{
            //reached the next town
            stepCounter.stop();

            if(isVisible) {
                player.setState(PlayerState.TOWN);
                player.setCurrentTown(player.getDestination().getNextTown());
                player.setDestination(null);

                GameSave.save(dsp.edit(), player);
                gsm.pop(new TownScreen(context, gsm));
            }else{
                NotificationUtil.cancel(NotificationUtil.NOTIFICATION_WALKING);
                NotificationUtil.notify(NotificationUtil.NOTIFICATION_TOWN, notificationCreator.townNotifcation(player.getDestination().getNextTown()));

                player.setState(PlayerState.TOWN);
                player.setCurrentTown(player.getDestination().getNextTown());
                player.setDestination(null);

                GameSave.save(dsp.edit(), player);
            }

        }
    }

    /* ALGORITHM FOR ENCOUNTERING AN ENEMY */
    public boolean encountered(){
        boolean encounter = false;

        int stepsNeeded = player.getDestination().getStepsNeeded();

        float percent = (chance * 1.0f / stepsNeeded * 1.0f) * 100;
        float result = r.nextFloat() * 100;

        if(result <= percent && percent >= 20) {
            encounter = true;
        }

        return encounter;
    }
}
