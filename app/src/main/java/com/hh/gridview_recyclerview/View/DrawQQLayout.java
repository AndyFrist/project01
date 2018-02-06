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
import com.hp.hpl.sparta.xpath.TrueExpr;

/**
 * Created by Administrator
 * on 2018/1/23 0023.
 */

public class DrawQQLayout extends FrameLayout {
    private static final String TAG = "DrawQQLayout";
    private ViewDragHelper.Callback callback;
    private ViewDragHelper viewDragHelper;
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
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return super.getViewHorizontalDragRange(child);
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (child == mainMenu) {
                    return fixLeft(left);
                }
                return left;
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                //当位置改变时候，处理要做的事（更新状态，伴随动画，重绘界面）
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                int newleft = left;
                if (changedView == leftMenu) {
                    newleft = mainMenu.getLeft() + dx;
                    LogUtil.d("DrawQQLayout", "newleft" + newleft + "dx" + dx + "mainMenu.getLeft()" + mainMenu.getLeft());
                    newleft = fixLeft(newleft);

                    leftMenu.layout(0, 0, mWidth, mHeight);
                    mainMenu.layout(0 + newleft, 0, mWidth + newleft, mHeight);
                }

                animation(newleft);
                //为了兼容低版本2.3以下，每次修改值后重绘
                invalidate();
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (xvel == 0 && mainMenu.getLeft() > range / 2) {
                    open();
                } else if (xvel > 0) {
                    open();
                } else {
                    close();
                }
            }
        };
        viewDragHelper = ViewDragHelper.create(this, callback);
    }

    private void animation(int newleft) {
        float peesent = newleft * 1.0f / range;
        LogUtil.d(TAG, "百分比" + peesent);
        //伴随动画
        // 1、左面板：缩放动画，平移动画，透明度变化
        //缩放动画 0.5 》》0.5 + 0.5 * peesent
        leftMenu.setScaleX(0.5f + 0.5f * peesent);
        leftMenu.setScaleY(0.5f + 0.5f * peesent);
        //平移动画 -mWidth >>> 0
        leftMenu.setTranslationX(-mWidth / 2 + mWidth * peesent /2);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //aa持续平滑动画（高频调用）
        //如果返回true，动画还需要继续执行
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
        }
    }

    private int fixLeft(int left) {
        if (left > range) {
            return range;
        } else if (left < 0) {
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

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        leftMenu = (ViewGroup) getChildAt(0);
        mainMenu = (ViewGroup) getChildAt(1);
    }

    int mWidth;
    int mHeight;
    int range;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        range = (int) (mWidth * 0.6);
    }
}
