package com.agorda.wow.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.agorda.wow.Adventure;
import com.agorda.wow.Fight;
import com.agorda.wow.R;
import com.agorda.wow.Splash;
import com.agorda.wow.gameElements.enemy.Enemy;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.gameElements.town.Town;

import static android.app.Notification.DEFAULT_ALL;
import static android.app.Notification.DEFAULT_LIGHTS;
import static android.app.Notification.DEFAULT_SOUND;
import static android.app.Notification.DEFAULT_VIBRATE;

/**
 * Created by Timothy on 13/11/2017.
 */

public class NotificationCreator {
    private  Context context;

    private PendingIntent adventureIntent;

    public NotificationCreator(Context c){
        context = c;
        initPendingIntents();
    }

    public void initPendingIntents(){
        //init pending intents that doesnt need any extras
        Intent i = new Intent (context, Splash.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        adventureIntent = PendingIntent.getActivity(context, NotificationUtil.PENDING_ADVENTURE, i, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public Notification walkNotifcation (Player player){
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder (context)
                .setSmallIcon(R.drawable.notification_walk)
                .setContentTitle ("Walking to " + player.getDestination().getNextTown().getName())
                .setContentText ("Steps Left: " + (player.getDestination().getStepsNeeded() - player.getDestination().getSteps()))
                .setOngoing (true)
                .setContentIntent (adventureIntent)
                .addAction(R.drawable.notification_walk, "Stop", null);
        return builder.build();
    }

    public Notification fightNotification(Player player, Enemy enemy){
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder (context)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle ("You've encountered a "+enemy.getName()+"!")
                .setContentText ("Player's HP: " + player.getData().getHP() + ", MP: " + player.getData().getMP())
                .setDefaults(DEFAULT_ALL)
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .addAction(R.drawable.logo, "Fight", adventureIntent)
                .addAction(R.drawable.logo, "Later", null);
        return builder.build();
    }

    public Notification townNotifcation(Town town){
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder (context)
                .setSmallIcon(R.drawable.notification_town)
                .setContentTitle ("Safefully reached Town " + town.getName() + "!")
                .setContentText (town.getDescription())
                .setDefaults(DEFAULT_ALL)
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .addAction(R.drawable.notification_town, "Go", adventureIntent)
                .addAction(R.drawable.notification_town, "Later", null);
        return builder.build();
    }
}
