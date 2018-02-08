package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.hh.gridview_recyclerview.utils.LogUtil;

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

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);

                int newleft = left;
                if (changedView == leftMenu) {
                    newleft = mainMenu.getLeft() + dx;
                    newleft = fixLeft(newleft);

                    leftMenu.layout(0, 0, mWidth, mHeight);
                    mainMenu.layout(newleft, 0, newleft + mWidth, mHeight);
                }

                setanimation(newleft);
                invalidate();
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (xvel == 0 && mainMenu.getLeft() > range / 2) {
                    open();
                } else if (xvel > 0) {
                    open();
                }else{
                    close();
                }
            }
        };
        viewDragHelper = ViewDragHelper.create(this, callback);
    }

    private void setanimation(int newleft) {
        float persent = newleft * 1.0f / range;
        LogUtil.d(TAG, "百分比" + persent);
        //伴随动画
        // 1、左面板：缩放动画，平移动画，透明度变化
        //缩放动画 0.5 》》0.5 + 0.5 * persent
        leftMenu.setScaleX(0.5f + 0.5f * persent);
        leftMenu.setScaleY(0.5f + 0.5f * persent);
        //平移动画 -mWidth >>> 0
        leftMenu.setTranslationX(-0.5f * mWidth + persent * 0.5f * mWidth);
        //透明度0.5》》1.0
        leftMenu.setAlpha(0.5f + 0.5f * persent);


        // 2、主面板：缩放动画
        mainMenu.setScaleX(1f - 0.2f * persent);
        mainMenu.setScaleY(1f - 0.2f * persent);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void close() {
        close(true);
    }

    private void close(boolean smooth) {
        int finalLeft = 0;
        if (smooth) {
            if (viewDragHelper.smoothSlideViewTo(mainMenu, finalLeft, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }else{
            mainMenu.layout(finalLeft,0,finalLeft + mWidth,mHeight);
        }
    }

    private void open() {
        open(true);
    }

    private void open(boolean smooth) {
        int finalLeft = range;
        if (smooth) {
            if (viewDragHelper.smoothSlideViewTo(mainMenu, finalLeft, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            mainMenu.layout(finalLeft,0,finalLeft + mWidth,mHeight);
        }
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
