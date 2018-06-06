package com.hh.gridview_recyclerview.utils.camera;

import android.hardware.camera2.CameraDevice;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017/6/18.
 */

public class MyStateCallback extends CameraDevice.StateCallback{

    @Override
    public void onOpened(@NonNull CameraDevice cameraDevice) {

    }

    @Override
    public void onDisconnected(@NonNull CameraDevice cameraDevice) {

    }

    @Override
    public void onError(@NonNull CameraDevice cameraDevic ,int i) {

    }
}
