package com.hh.gridview_recyclerview.myApplication;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.WindowManager;

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

        return activityArrayList.get(activityArrayList.size() - 1);
    }

    /**
     * 创建全局变量
     * 全局变量一般都比较倾向于创建一个单独的数据类文件，并使用static静态变量
     * <p>
     * 这里使用了在Application中添加数据的方法实现全局变量
     * 注意在AndroidManifest.xml中的Application节点添加android:name=".MyApplication"属性
     */
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();


    public WindowManager.LayoutParams getMywmParams() {
        return wmParams;
    }


    static final int maxMenmory = (int) Runtime.getRuntime().maxMemory() / 1024;
    // 计算缓存内存大小
    static int cacheSize = maxMenmory / 4;
    // 图片缓存（内存缓存）
    public static LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(cacheSize) {
        @Override
        protected int sizeOf(String key, Bitmap value) {
            // 计算图片大小
            return value.getRowBytes() * value.getHeight() / 1024;
        }
    };
}
