package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by 徐小鹏 on 2017/2/24.
 * <p>
 * 注释：
 */

public class MyTouchFrameLayout extends FrameLayout {
    public MyTouchFrameLayout(Context context) {
        super(context);
    }

    public MyTouchFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouchFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("MyTouchFrameLayout**********************dispatchTouchEvent"+ev.getAction());
        return super.dispatchTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        System.out.println("MyTouchFrameLayout**********************onInterceptTouchEvent"+ev.getAction());
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("MyTouchFrameLayout**********************onTouchEvent"+event.getAction());
        return super.onTouchEvent(event);
    }
}
