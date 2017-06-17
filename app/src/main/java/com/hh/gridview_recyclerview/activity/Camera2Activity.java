package com.hh.gridview_recyclerview.activity;

import android.graphics.SurfaceTexture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.MyTexureView;

public class Camera2Activity extends AppCompatActivity {
    private static final String TAG = "Camera2Activity";
    private MyTexureView my_surfacetexure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);
        my_surfacetexure = (MyTexureView) findViewById(R.id.my_surfacetexure);
        SurfaceTexture texture = my_surfacetexure.getSurfaceTexture();
        Surface surface = new Surface(texture);

    }
}
