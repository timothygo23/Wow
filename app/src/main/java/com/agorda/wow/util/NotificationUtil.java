package com.agorda.wow.util;

import android.app.Notification;
import android.app.NotificationManager;

/**
 * Created by Timothy on 13/11/2017.
 */

public class NotificationUtil {

    //Notification IDs
    public static final int NOTIFICATION_WALKING = 1;
    public static final int NOTIFICATION_ENEMY = 2;
    public static final int NOTIFICATION_TOWN = 3;

    //Pending IDs
    public static final int PENDING_FIGHT = 1; //fight activity intent
    public static final int PENDING_TOWN = 2; //town activity intent
    public static final int PENDING_ADVENTURE = 3; //adventure activity intent

    private static NotificationManager notificationManager;

    public static void setUp(NotificationManager nm){
        notificationManager = nm;
    }

    public static void notify(int id, Notification notification){
        notificationManager.notify(id, notification);
    }

    public static void cancel(int id){
        notificationManager.cancel(id);
    }

    public static void cancelAll(){
        notificationManager.cancelAll();
    }
}
