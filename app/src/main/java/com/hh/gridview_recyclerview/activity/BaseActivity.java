package com.hh.gridview_recyclerview.activity;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.hh.gridview_recyclerview.myApplication.AndFixApplication;
import com.hh.gridview_recyclerview.myinteraface.PermissionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * on 2017/6/17.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndFixApplication.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AndFixApplication.removeActivity(this);
    }

    private static PermissionListener listener;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void requestPermissionss(String[] permissions, PermissionListener listen) {
        listener = listen;
        Activity activity = AndFixApplication.getTopActivity();
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(activity, permissionList.toArray(new String[permissionList.size()]), 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        ArrayList<String> deniedPermission = new ArrayList<>();
        for (int i : grantResults) {
            if (i != PackageManager.PERMISSION_GRANTED) {
                deniedPermission.add(permissions[i]);
            }
        }

        if (deniedPermission.size() > 0) {
            listener.onDenied(deniedPermission);
        } else {
            listener.onGranted();
        }
    }
}
