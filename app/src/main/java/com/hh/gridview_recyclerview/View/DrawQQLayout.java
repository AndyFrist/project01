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
import android.widget.LinearLayout;

import com.hh.gridview_recyclerview.utils.LogUtil;

/**
 * Created by xuxiaopeng on 2018/2/7.
 */

public class DrawQQLayout extends LinearLayout {
    private static final String TAG = "DrawLayout";
    private ViewDragHelper.Callback cb;
    private ViewDragHelper viewDragHelper;
    private View mChild;

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

        cb = new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                //根据建议值修改移动的位置
                //left = child.getLeft + dx
                if (child == mChild) {
                    return fixLeft(left);
                }
                return left;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                close();
            }
        };

        viewDragHelper = ViewDragHelper.create(this, cb);
    }

    private void close() {
        close(true);
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
    public void close(boolean smooth) {
        int finalLeft = 0;
        if (smooth) {
            //a触发一个平滑动画
            //返回true表示还没有移动到指定位置，需要刷新界面
            if (viewDragHelper.smoothSlideViewTo(mChild, finalLeft, 0)) {
                //参数传this（child所在的parent）
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            mChild.layout(finalLeft, 0, finalLeft + mWidth, 0 + mHeight);
        }
    }

    /**
     * 设置范围
     *
     * @param left
     * @return
     */
    private int fixLeft(int left) {
        if (left < -range) {
            return -range;
        } else if (left > range) {
            return range;
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
        if (getChildCount() < 1) {
            throw new IllegalStateException("至少有1个孩子view");
        }
        mChild = getChildAt(0);

    }

    int range;
    int mHeight;
    int mWidth;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = mChild.getMeasuredHeight();
        mWidth = mChild.getMeasuredWidth();
        range = (int) (0.5 * mWidth);

    }
}
