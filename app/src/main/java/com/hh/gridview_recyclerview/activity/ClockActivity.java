package com.hh.gridview_recyclerview.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hh.gridview_recyclerview.R;

import java.util.Date;

public class ClockActivity extends AppCompatActivity {
    private static final String TAG = "ClockActivity";
    private MyBroadCast myBroadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        IntentFilter filter = new IntentFilter();
        myBroadCast = new MyBroadCast();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(myBroadCast, filter);
    }


    long time = -1;

    class MyBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action)) {
                // 开屏
                Log.i(TAG, "开屏: ");
            } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                // 锁屏
                Date date = new Date();
                time = date.getTime();
                Log.i(TAG, "锁屏: " + time);

            } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
                // 解锁
                Date date = new Date();
                long currentTime = date.getTime();
                long sub = currentTime - time;
                if (sub / 1000 > 3 ) {
                    Intent intent1 = new Intent(ClockActivity.this, MainActivity.class);
                    context.startActivity(intent1);
                }


                Log.i(TAG, "解锁: "  + (sub / 1000) );


            }

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadCast);
    }
}

