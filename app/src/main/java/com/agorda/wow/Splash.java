package com.agorda.wow;

import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.agorda.wow.util.NotificationUtil;

public class Splash extends AppCompatActivity {
    private final int loadTime = 1500;

    private SharedPreferences dsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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

                if (!dsp.contains ("is_new_player")) {
                    Intent i = new Intent (getBaseContext(), Setup.class);
                    startActivity (i);
                    finish();
                } else { //temporary previous activity
                    Intent i = new Intent (getBaseContext(), Town.class);
                    startActivity(i);
                    finish();
                }

                //temp
                /*Intent setup = new Intent(getBaseContext(), Setup.class);
                startActivity(setup);
                finish();*/
            }
        }, loadTime);
    }
}
