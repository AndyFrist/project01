package com.hh.gridview_recyclerview.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.activity.AnimatorActivity;
import com.hh.gridview_recyclerview.utils.LogUtil;

public class ForgoundService extends Service {
    private static final String TAG = "ForgoundService";
    public ForgoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle extras = intent.getExtras();
        if (extras != null) {
            String start_value = intent.getExtras().getString("start_value");
            LogUtil.d(TAG,start_value);
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
