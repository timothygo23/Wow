package com.agorda.wow;

import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.agorda.wow.gameElements.db_constants.ObjectId;
import com.agorda.wow.gameElements.equipment.Weapon;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.gameElements.player.PlayerState;
import com.agorda.wow.ui.MainView;
import com.agorda.wow.util.DatabaseHelper;
import com.agorda.wow.util.NotificationUtil;

public class Splash extends AppCompatActivity {
    private final int loadTime = 1500;

    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);
        float scale_width = 1080 / getWindowManager().getDefaultDisplay().getWidth();
        float scale_height = 1920 / getWindowManager().getDefaultDisplay().getHeight();
        mainView = new MainView(getBaseContext(), scale_width, scale_height);
        setContentView(mainView);

        //sets up the db (creates it when it doesn't exists yet)
        //DatabaseHelper dbh = new DatabaseHelper(this);
        //dbh.getWritableDatabase();

        /*doTransition();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //create intent for first screen
                //check shared preferences if first time user
                //if -> true
                //create setup activity
                //if -> false
                //create previous activity of user

                dsp = PreferenceManager.getDefaultSharedPreferences (getBaseContext ());

                if (!dsp.contains("state")) {
                    Intent i = new Intent (getBaseContext(), Setup.class);
                    startActivity (i);
                    finish();
                } else { //temporary previous activity
                    Intent i = null;
                    PlayerState ps = PlayerState.valueOf(dsp.getString("state", "Town"));

                    if(ps == PlayerState.TOWN || ps == PlayerState.DEAD){
                        i = new Intent(getBaseContext(), Town.class);
                    }else if(ps == PlayerState.WALKING || ps == PlayerState.CAMPING){
                        i = new Intent(getBaseContext(), Adventure.class);
                    }else if(ps == PlayerState.FIGHTING){
                        i = new Intent(getBaseContext(), Fight.class);
                    }

                    startActivity(i);
                    finish();
                }

            }
        }, loadTime);*/
    }

    public void doTransition(){
        Fade fadeIn = new Fade(Fade.IN);
        ViewGroup view = (ViewGroup)findViewById(R.id.logo);

        ImageView imageView = new ImageView(getBaseContext());
        imageView.setImageDrawable(getDrawable(R.drawable.logo));
        imageView.setScaleX(0.65f);
        imageView.setScaleY(0.65f);

        TransitionManager.beginDelayedTransition(view, fadeIn);

        view.addView(imageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainView.pause();
    }
}
