package com.hh.gridview_recyclerview.activity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.MyTexureView;
import com.hh.gridview_recyclerview.View.MyTexureView2;
import com.hh.gridview_recyclerview.utils.camera.MyStateCallback;

public class Camera2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button close_camera2, takepicture, teke_phote;
    private RelativeLayout rl_root;
    private MyTexureView2 myTexureView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera3);
        initView();
    }

    private void initView() {
        close_camera2 = (Button) findViewById(R.id.close_camera2);
        takepicture = (Button) findViewById(R.id.takepicture);
        teke_phote = (Button) findViewById(R.id.teke_phote);
        rl_root = (RelativeLayout) findViewById(R.id.rl_root);
        close_camera2.setOnClickListener(this);
        takepicture.setOnClickListener(this);
        teke_phote.setOnClickListener(this);
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

//        rl_root.addView(myTexureView,0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_camera2:
                finish();//关闭页面
                break;
            case R.id.takepicture:
                break;
            case R.id.teke_phote:
                break;
        }
    }
}
