package com.hh.gridview_recyclerview.utils;

import android.content.Context;
import android.view.WindowManager;

import com.hh.gridview_recyclerview.myApplication.AndFixApplication;

/**
 * Created by Administrator on 2017/11/27.
 */

public class AndroidUtils {
    /**
     * 取得屏幕适应比例系数（以720*1280为标准）
     */
    public static float getRATIO() {
        int screenWidth = getScreenWidth(AndFixApplication.getContext());
        int screenHeight = getScreenHeight(AndFixApplication.getContext());
        float ratioWidth = (float) screenWidth / 720;
        float ratioHeight = (float) screenHeight / 1280;
        return Math.min(ratioWidth, ratioHeight);
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
    }
    /**
     * @param context
     * @return 状态栏的高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
