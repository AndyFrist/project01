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
    public static void requestPermissionss(String[] permissions, @NonNull PermissionListener listen) {
        listener = listen;
        Activity activity = AndFixApplication.getTopActivity();
        List<String> permissionList = new ArrayList<>();//没有被同意的权限列表
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                //检查统计没有被同意的权限
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(activity, permissionList.toArray(new String[permissionList.size()]), 1);
        }else{
            listener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        ArrayList<String> deniedPermission = new ArrayList<>();//被拒绝的权限列表
        int k = 0;
        for (int i : grantResults) {
            if (i != PackageManager.PERMISSION_GRANTED) {
                //检查统计被拒绝的权限
                deniedPermission.add(permissions[k]);
            }
            k++;
        }

        if (deniedPermission.size() > 0) {
            listener.onDenied(deniedPermission);
        } else {
            listener.onGranted();
        }
    }
}
