package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by xuxiaopeng on 2018/2/7.
 */

public class DrawQQLayout extends FrameLayout {
    private static final String TAG = "DrawLayout";
    private ViewDragHelper viewDragHelper;
    private ViewDragHelper.Callback callback;
    private ViewGroup leftMenu;
    private ViewGroup mainMenu;

    public DrawQQLayout(@NonNull Context context) {
        this(context, null);
    }

    public DrawQQLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawQQLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {


        callback = new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                //根据建议值修改移动的位置
                //left = child.getLeft + dx
                if (child == mainMenu) {
                    return fixLeft(left);
                }
                return left;
            }


        };
        viewDragHelper = ViewDragHelper.create(this, callback);
    }

    private int fixLeft(int left) {
        if (left > range ) {
            return range;
        }else if (left < 0){
            return 0;
        }
        return left;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            viewDragHelper.processTouchEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private int mHeight;
    private int mWidth;
    private int range;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = leftMenu.getMeasuredHeight();
        mWidth = leftMenu.getMeasuredWidth();
        range = (int) (mWidth *0.6);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount < 2) {
            throw new IllegalStateException("至少得有两个孩子");
        }
        if (!(getChildAt(0) instanceof ViewGroup && getChildAt(1) instanceof ViewGroup)) {
            throw new IllegalStateException("孩子控件不是容器");
        }
        leftMenu = (ViewGroup) getChildAt(0);
        mainMenu = (ViewGroup) getChildAt(1);
    }
}
