package com.hh.gridview_recyclerview.utils;

import android.content.Context;
import android.view.WindowManager;

import com.hh.gridview_recyclerview.myApplication.AndFixApplication;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;

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

    public static int px2dip(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static String omit4Float(float f) {
        BigDecimal b = new BigDecimal(f);
        float f1 = b.setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
        DecimalFormat decimalFormat = new DecimalFormat("##0.0000");
        String s = decimalFormat.format(f1);
        return s;
    }


    public static int getYear() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR);
    }

    public static int getMonth() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.MONTH) + 1;
    }

    public static int getDay() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.DAY_OF_MONTH);
    }
}
