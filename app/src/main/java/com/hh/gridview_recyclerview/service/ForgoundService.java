package com.hh.gridview_recyclerview.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import com.hh.gridview_recyclerview.utils.LogUtil;
import com.hh.gridview_recyclerview.utils.ToastUtil;

public class ForgoundService extends Service {
    private static final String TAG = "ForgoundService";
    public ForgoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyIBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

            String start_value = intent.getStringExtra("start_value");
            LogUtil.d(TAG,start_value);

        return super.onStartCommand(intent, flags, startId);
    }

    private String banzheng(String text) {
        ToastUtil.showToast(text+"把证给办了");
        return text+"把证给办了";
    }

    public class MyIBinder extends Binder{

        public String ban(String text) {
            return banzheng(text);
        }
    }
}
