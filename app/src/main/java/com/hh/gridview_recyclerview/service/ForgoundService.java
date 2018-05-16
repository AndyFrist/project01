package com.hh.gridview_recyclerview.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.activity.AnimatorActivity;

public class ForgoundService extends Service {
    public ForgoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification = new Notification(R.drawable.ic_launcher, "服务正在运行", System.currentTimeMillis());
        Intent notificationIntent = new Intent(this, AnimatorActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//        notification.setLatestEventInfo(this, "消息标题", "消息内容", pendingIntent);
        startForeground(1, notification);
        return super.onStartCommand(intent, flags, startId);
    }
}
