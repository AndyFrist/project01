package com.hh.gridview_recyclerview.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.service.ForgoundService;
import com.hh.gridview_recyclerview.utils.LogUtil;

public class MyImageViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start_service, post_value_start, get_value_start, bind_service, post_value_bind, get_value_bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_image_view);

        start_service = (Button) findViewById(R.id.start_service);
        start_service.setOnClickListener(this);
        post_value_start = (Button) findViewById(R.id.post_value_start);
        post_value_start.setOnClickListener(this);
        get_value_start = (Button) findViewById(R.id.get_value_start);
        get_value_start.setOnClickListener(this);
        bind_service = (Button) findViewById(R.id.bind_service);
        bind_service.setOnClickListener(this);
        post_value_bind = (Button) findViewById(R.id.post_value_bind);
        post_value_bind.setOnClickListener(this);
        get_value_bind = (Button) findViewById(R.id.get_value_bind);
        get_value_bind.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //开启服务
        if (v == start_service) {
            startService(new Intent(this, ForgoundService.class));
        }
        //传值给服务
        if (v == post_value_start) {
            Intent intent = new Intent(this, ForgoundService.class);
            intent.putExtra("start_value", "我来自activity的值");
            startService(intent);
        }
        if (v == bind_service) {
            myConn = new MyConn();
            bindService(new Intent(this, ForgoundService.class), myConn, BIND_AUTO_CREATE);
        }
        if (v == get_value_bind) {
            String ban = myIBinder.ban("李娜");
            LogUtil.d("李娜",ban);
        }
    }

    private ForgoundService.MyIBinder myIBinder;
    private MyConn myConn;

    class MyConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myIBinder = (ForgoundService.MyIBinder) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
