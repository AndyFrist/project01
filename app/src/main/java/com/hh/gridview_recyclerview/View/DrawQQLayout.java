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

public class DrawQQLayout extends FrameLayout {
    private static final String TAG = "DrawQQLayout";

    private ViewDragHelper.Callback cb;
    private ViewDragHelper viewDragHelper;
    private ViewGroup mainMenu;

    public DrawQQLayout(Context context) {
        this(context, null);
    }

    public DrawQQLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawQQLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

                return fixLeft(left);
            }


            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return fixLeft(top);
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                //当位置改变时候，处理要做的事（更新状态，伴随动画，重绘界面）
                super.onViewPositionChanged(changedView, left, top, dx, dy);
//                int newleft = left;
//                if (changedView == leftMenu) {
//                    newleft = mainMenu.getLeft() + dx;
//                    LogUtil.d("DrawQQLayout", "newleft" + newleft + "dx" + dx + "mainMenu.getLeft()" + mainMenu.getLeft());
//
//                    newleft = fixLeft(newleft);
//                    leftMenu.layout(0, 0, mWidth, mHeigh);
//                    mainMenu.layout(0 + newleft, 0, mWidth + newleft, mHeigh);
//                }
//                //更新状态执行动画
//                dispatchDrawEvent(newleft);
//                //为了兼容低版本2.3以下，每次修改值后重绘
//                invalidate();
            }


            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                //当View被释放处理的事（执行动画）bbb
                // releasedChild 被释放的view
                //xvel 水平方向的速度
                //yvel 垂直方向的速度
                super.onViewReleased(releasedChild, xvel, yvel);
                close();
            }
        };

        viewDragHelper = ViewDragHelper.create(this, cb);

    }

//    private void dispatchDrawEvent(int newleft) {
//        //伴随动画
//        float persent = newleft * 1.0f / range;
//        // 1、左面板：缩放动画，平移动画，透明度变化
//        //缩放动画 0.5 》》0.5 + 0.5 * peesent
//        leftMenu.setScaleX(0.5f + 0.5f * persent);
//        leftMenu.setScaleY(0.5f + 0.5f * persent);
//        //平移动画 -mWidth /2 >>> 0
//        leftMenu.setTranslationX( -mWidth / 2.0f + persent * (mWidth / 2) );
//        //透明度0.5》》1.0
//        leftMenu.setAlpha(0.5f + 0.5f * persent);
//
//
//        // 2、主面板：缩放动画
//        mainMenu.setScaleX((float) (1 - persent * 0.2));
//        mainMenu.setScaleY((float) (1 - persent * 0.2));
//
//
//    }

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
            //a触发一个平滑动画
            //返回true表示还没有移动到指定位置，需要刷新界面
            if (viewDragHelper.smoothSlideViewTo(mainMenu, finalLeft, 0)) {
                //参数传this（child所在的parent）
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            mainMenu.layout(0, 0, mWidth, mHeigh);
        }
    }

    private void open() {
        open(true);
    }

    private void open(boolean smooth) {
        int finalLeft = range;
        if (smooth) {
            //a触发一个平滑动画
            //返回true表示还没有移动到指定位置，需要刷新界面
            if (viewDragHelper.smoothSlideViewTo(mainMenu, finalLeft, 0)) {
                //参数传this（child所在的parent）
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            mainMenu.layout(range, 0, range + mWidth, mHeigh);
        }
    }

    private int fixLeft(int top) {

        return top;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.d("touch","dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.d("touch","onInterceptTouchEvent");

        viewDragHelper.shouldInterceptTouchEvent(ev);
        if (ev.equals(MotionEvent.ACTION_UP)) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("touch","onTouchEvent");

        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mainMenu = (ViewGroup) getChildAt(0);

        if (!(mainMenu instanceof ViewGroup)) {

            throw new RuntimeException("孩子不是ViewGroup");
        }

        if (getChildCount() < 1) {
            throw new RuntimeException("至少有1个子孩子");
        }
    }


    int mHeigh = 0;
    int mWidth = 0;
    int range = 0;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeigh = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        range = (int) (0.3 * mWidth);
    }
}
