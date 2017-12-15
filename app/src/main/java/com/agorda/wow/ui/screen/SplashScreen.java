package com.agorda.wow.ui.screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;

import com.agorda.wow.R;
import com.agorda.wow.gameElements.player.PlayerState;
import com.agorda.wow.ui.GameScreenManager;
import com.agorda.wow.ui.MainView;
import com.agorda.wow.ui.ui_element.Background;
import com.agorda.wow.ui.ui_element.FadeBitmap;
import com.agorda.wow.util.DatabaseHelper;

/**
 * Created by Timothy on 08/12/2017.
 */

public class SplashScreen implements Screen, ScreenListener {
    private GameScreenManager gsm;
    private Context context;

    private Background bg;
    private Paint backgroundPaint;

    private FadeBitmap logo;

    private SharedPreferences dsp;

    public SplashScreen(Context context, GameScreenManager gsm){
        this.context = context;
        this.gsm = gsm;

        init();
        initTransition();
    }

    @Override
    public void init() {
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.parseColor("#383838"));
        backgroundPaint.setStyle(Paint.Style.FILL);

        bg = new Background(context);
        bg.set_X(0)
                .set_Y(0)
                .setPaint(backgroundPaint);

        //sets up the db (creates it when it doesn't exists yet)
        DatabaseHelper dbh = new DatabaseHelper(context);
        dbh.getWritableDatabase();
        dbh.close();

        dsp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void initHandler() {

    }

    public void initTransition(){
        logo = new FadeBitmap(context);
        logo.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.logo));
        logo.setFade(FadeBitmap.FADE_IN, this);
    }

    @Override
    public void render(Canvas canvas) {
        bg.setWidth(MainView.WIDTH * MainView.SCALE_WIDTH)
                .setHeight(MainView.HEIGHT * MainView.SCALE_HEIGHT).draw(canvas);
        logo.draw(canvas);
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void switchScreen() {
        /*
            check state of the player then switch the screen accordingly
         */
        if (!dsp.contains("state")) {
            gsm.pop(new PrologueScreen(context, gsm));
        } else {
            PlayerState ps = PlayerState.valueOf(dsp.getString("state", "Town"));

            if(ps == PlayerState.TOWN || ps == PlayerState.DEAD){
                gsm.pop(new TownScreen(context, gsm));
            }else if(ps == PlayerState.WALKING || ps == PlayerState.CAMPING || ps == PlayerState.ENCOUNTERED){
                gsm.pop(new AdventureScreen(context, gsm));
            }else if(ps == PlayerState.FIGHTING){
                gsm.pop(new FightScreen(context, gsm));
            }
        }
    }
}
