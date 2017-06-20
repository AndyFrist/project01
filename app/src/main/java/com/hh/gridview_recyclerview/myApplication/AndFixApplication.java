package com.hh.gridview_recyclerview.myApplication;

import android.app.Activity;
import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;

import java.util.ArrayList;

/**
 * Created by 徐小鹏 on 2017/2/8.
 * <p>
 * 注释：
 */

public class AndFixApplication extends Application {
    public static PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化patch管理类
        mPatchManager = new PatchManager(this);

        // 初始化patch版本
        mPatchManager.init("1.0");
//        String appVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//        mPatchManager.init(appVersion);

        // 加载已经添加到PatchManager中的patch
        mPatchManager.loadPatch();


    }

    private static ArrayList<Activity> activityArrayList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityArrayList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityArrayList.remove(activity);
    }

    public static Activity getTopActivity() {

        return activityArrayList.get(activityArrayList.size()-1);
    }
}
