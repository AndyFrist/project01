package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by 徐小鹏 on 2017/2/24.
 * <p>
 * 注释：
 */

public class MyTouchRelativeLayout extends RelativeLayout {
    public MyTouchRelativeLayout(Context context) {
        super(context);
    }

    public MyTouchRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouchRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("MyTouchRelativeLayout-------------dispatchTouchEvent"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        System.out.println("MyTouchRelativeLayout-------------onInterceptTouchEvent"+ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("MyTouchRelativeLayout-------------onTouchEvent"+event.getAction());
        return super.onTouchEvent(event);
    }
}
