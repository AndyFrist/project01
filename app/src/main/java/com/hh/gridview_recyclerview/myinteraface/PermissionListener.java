package com.hh.gridview_recyclerview.myinteraface;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/17.
 */

public interface PermissionListener {
    void onGranted();

    void onDenied(ArrayList<String> deniedPermission);
}
