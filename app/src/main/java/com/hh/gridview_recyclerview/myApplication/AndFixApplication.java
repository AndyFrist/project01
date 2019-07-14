package com.hh.gridview_recyclerview.myApplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.util.LruCache;
import android.view.WindowManager;

import com.alipay.euler.andfix.patch.PatchManager;
import com.hh.gridview_recyclerview.utils.ToastUtil;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.service.PatchResult;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;
import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback;
import com.tinkerpatch.sdk.tinker.callback.ResultCallBack;

import org.xutils.x;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import rx.annotations.Beta;

/**
 * Created by 徐小鹏 on 2017/2/8.
 * <p>
 * 注释：
 */

public class AndFixApplication extends MultiDexApplication {
    public static PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        // 初始化patch管理类
        mPatchManager = new PatchManager(this);

        // 初始化patch版本
        mPatchManager.init("1.0");
//        String appVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//        mPatchManager.init(appVersion);

        // 加载已经添加到PatchManager中的patch
        mPatchManager.loadPatch();
        //设置字体
        initfonts();
        x.Ext.init(this);


        initTinkerPatch();
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

    private static Context appContext;

    public static Context getContext() {
        return appContext;
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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    public static Typeface TypeFaceYaHei;

    private void initfonts() {
        TypeFaceYaHei = Typeface.createFromAsset(getAssets(), "fonts/PingFang Bold.ttf");
        try {
            Field field = Typeface.class.getDeclaredField("MONOSPACE");
            field.setAccessible(true);
            field.set(null, TypeFaceYaHei);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 我们需要确保至少对主进程跟patch进程初始化 TinkerPatch
     */
    private void initTinkerPatch() {

        // 我们可以从这里获得Tinker加载过程的信息
        ApplicationLike tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();

        // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
        TinkerPatch.init(tinkerApplicationLike)
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3);

        // 每隔3个小时(通过setFetchPatchIntervalByHours设置)去访问后台时候有更新,通过handler实现轮训的效果
        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();
    }

}
