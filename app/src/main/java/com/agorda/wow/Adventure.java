package com.agorda.wow;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.agorda.wow.gameElements.player.Destination;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.util.StepCounter;
import com.agorda.wow.util.StepCounterListener;

public class Adventure extends AppCompatActivity implements StepCounterListener {
    private StepCounter stepCounter;
    private Player player;

    private TextView adventure_tv_steps;
    private ToggleButton adventure_tb_walk;

    public static final int NOTIFICATION_ID = 0;
    public static final int PENDING_INTENT_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);

        //sensors
        SensorManager sensorManager = (SensorManager)getSystemService(getBaseContext().SENSOR_SERVICE);
        stepCounter = new StepCounter(sensorManager, this);

        player = new Player("tim");
        player.setDestination(new Destination(new com.agorda.wow.gameElements.town.Town("town1", 0), new com.agorda.wow.gameElements.town.Town("town2", 12)));

        adventure_tv_steps = (TextView)findViewById(R.id.adventure_tv_steps);

        adventure_tb_walk = (ToggleButton)findViewById(R.id.adventure_tb_walk);
        adventure_tb_walk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //is walking
                    stepCounter.start();
                }else{
                    //not walking
                    stepCounter.stop();
                }
            }
        });
    }

    @Override
    public void onStep() {
        if(player.walk()){
            //chance to encounter monster base on the number of steps the player has made

        }else{
            stepCounter.stop();
            //add pop up "you reached the town"
            Intent town = new Intent(getBaseContext(), Town.class);
            startActivity(town);
            finishAffinity();
        }
        adventure_tv_steps.setText("Steps: " + player.getDestination().getSteps());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(stepCounter.isActive()) {
            //add persistent notif
            NotificationManager notificationManager = (NotificationManager) getSystemService (NOTIFICATION_SERVICE);

            Intent i = new Intent (this, Adventure.class);
            i.setAction(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pi = PendingIntent.getActivity(this, PENDING_INTENT_ID, i, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder (this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle ("You are walking")
                    .setContentText ("xD")
                    .setOngoing (true)
                    .setContentIntent (pi);

            notificationManager.notify(NOTIFICATION_ID, builder.build ());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(stepCounter.isActive()){
            //remove persistent notif
            NotificationManager notificationManager = (NotificationManager) getSystemService (NOTIFICATION_SERVICE);
            notificationManager.cancel(NOTIFICATION_ID);
        }
    }
}
