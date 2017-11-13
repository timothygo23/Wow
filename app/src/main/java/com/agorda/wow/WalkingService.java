package com.agorda.wow;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.agorda.wow.util.NotificationUtil;

public class WalkingService extends Service {

    public WalkingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        NotificationUtil.cancel(NotificationUtil.NOTIFICATION_WALKING);
        stopSelf();
    }
}
