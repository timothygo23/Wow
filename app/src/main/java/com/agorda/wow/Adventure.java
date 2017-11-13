package com.agorda.wow;

import android.app.NotificationManager;
import android.content.Intent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.agorda.wow.gameElements.player.Destination;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.gameElements.player.PlayerState;
import com.agorda.wow.service.WalkingService;
import com.agorda.wow.util.NotificationCreator;
import com.agorda.wow.util.NotificationUtil;
import com.agorda.wow.util.StepCounter;
import com.agorda.wow.util.StepCounterListener;

public class Adventure extends AppCompatActivity implements StepCounterListener {
    private StepCounter stepCounter;
    private Player player;

    private TextView adventure_tv_steps;
    private ToggleButton adventure_tb_walk;

    private boolean isVisible; //if the activity is onResume or onStop
    private NotificationCreator notificationCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);

        //sensors
        SensorManager sensorManager = (SensorManager)getSystemService(getBaseContext().SENSOR_SERVICE);
        stepCounter = new StepCounter(sensorManager, this);

        player = new Player("tim", new com.agorda.wow.gameElements.town.Town("town1", "", 0));
        player.setDestination(new Destination(player.getCurrentTown(), new com.agorda.wow.gameElements.town.Town("town2","This new town is nice.",12)));

        adventure_tv_steps = (TextView)findViewById(R.id.adventure_tv_steps);

        adventure_tb_walk = (ToggleButton)findViewById(R.id.adventure_tb_walk);
        adventure_tb_walk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //is walking
                    player.setState(PlayerState.WALKING);
                    stepCounter.start();
                }else{
                    //not walking
                    player.setState(PlayerState.CAMPING);
                    stepCounter.stop();
                }
            }
        });

        init();
    }

    public void init(){
        isVisible = true;
        NotificationUtil.setUp((NotificationManager)getSystemService(NOTIFICATION_SERVICE));
        notificationCreator = new NotificationCreator(getBaseContext());
    }

    @Override
    public void onStep() {
        if(player.walk()){
            if(!isVisible)
                NotificationUtil.notify(NotificationUtil.NOTIFICATION_WALKING, notificationCreator.walkNotifcation(player));

            //encountered an enemy
            if(player.getDestination().getSteps() == 10) {
                player.setState(PlayerState.FIGHTING);
                stepCounter.stop();

                if(isVisible){
                    goToFightActivity();
                }else{
                    NotificationUtil.cancel(NotificationUtil.NOTIFICATION_WALKING);
                    NotificationUtil.notify(NotificationUtil.NOTIFICATION_ENEMY, notificationCreator.fightNotification(player));
                }
            }

        }else{
            //reached the next town
            player.setState(PlayerState.TOWN);
            player.setCurrentTown(player.getDestination().getNextTown());
            player.setDestination(null);
            stepCounter.stop();

            if(isVisible) {
                goToTownActivity();
            }else{
                NotificationUtil.cancel(NotificationUtil.NOTIFICATION_WALKING);
                NotificationUtil.notify(NotificationUtil.NOTIFICATION_TOWN, notificationCreator.townNotifcation(player.getDestination().getNextTown()));
            }

        }
        adventure_tv_steps.setText("Steps: " + player.getDestination().getSteps());
    }

    @Override
    protected void onStop() {
        super.onStop();
        isVisible = false;
        if(stepCounter.isActive()) {
            NotificationUtil.notify(NotificationUtil.NOTIFICATION_WALKING, notificationCreator.walkNotifcation(player));
            //start walking service
            Intent services = new Intent(getBaseContext(), WalkingService.class);
            startService(services);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isVisible = true;

        NotificationUtil.cancelAll();
        if(stepCounter.isActive()){
            //stop walking service
            Intent services = new Intent(getBaseContext(), WalkingService.class);
            stopService(services);
        }

        //change activity based on the current state of the player
        if(player.getState() == PlayerState.FIGHTING){
            goToFightActivity();
        }else if(player.getState() == PlayerState.TOWN){
            goToTownActivity();
        }
    }

    public void goToFightActivity(){
        //create pop up you encountered enemy

        //create fighting intent
        Intent fightIntent = new Intent(getBaseContext(), Fight.class);
        startActivity(fightIntent);
        finish();
    }

    public void goToTownActivity(){
        //create popup you reached the next town

        //create town intent
        Intent townIntent = new Intent(getBaseContext(), Town.class);
        startActivity(townIntent);
        finish();
    }

}
