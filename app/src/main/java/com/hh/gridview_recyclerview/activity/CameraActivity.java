package com.hh.gridview_recyclerview.activity;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.MyTexureView;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CameraActivity";
    private FrameLayout fl_crmera;
    private MyTexureView myTexureView;
    private Camera mCamera;
    private Button take_picture,close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera2);
        fl_crmera = (FrameLayout) findViewById(R.id.fl_crmera);
        initView();
        try {
            mCamera = Camera.open(); // 试图获取Camera实例
            myTexureView = new MyTexureView(this, mCamera);
            fl_crmera.addView(myTexureView);
            myTexureView.setSurfaceTextureListener(myTexureView);
        } catch (Exception e) {
            // 摄像头不可用（正被占用或不存在）
            Log.d(TAG, "onCreate: " + e.toString());
        }
    }

    private void initView() {
        take_picture = (Button) findViewById(R.id.take_picture);
        close = (Button) findViewById(R.id.close);
        take_picture.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        releaseCamera(); // 在暂停事件中立即释放摄像头
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release(); // 为其它应用释放摄像头
            mCamera = null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.take_picture:

                break;
            case R.id.close:
                finish();
                break;
        }
    }
}
