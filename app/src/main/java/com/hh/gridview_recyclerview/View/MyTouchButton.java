package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by 徐小鹏 on 2017/2/24.
 * <p>
 * 注释：
 */

public class MyTouchButton extends Button {
    public MyTouchButton(Context context) {
        super(context);
    }

    public MyTouchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        System.out.println("MyTouchButton####################dispatchTouchEvent"+event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("MyTouchButton####################onTouchEvent"+event.getAction());
//        return super.onTouchEvent(event);
        return false;
    }
}
