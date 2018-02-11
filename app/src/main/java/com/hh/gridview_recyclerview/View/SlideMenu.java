package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by 徐小鹏 on 2017/2/10.
 * <p>
 * 注释：
 */

public class SlideMenu extends FrameLayout {
    private View menuView, mainView;
    private int menuWidth = 0;
    private Scroller scroller;

    public SlideMenu(Context context) {
        super(context);
        initView();
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        scroller = new Scroller(getContext());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        menuView = getChildAt(0);
        mainView = getChildAt(1);
        menuWidth = menuView.getLayoutParams().width;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        menuView.layout(-menuWidth, 0, 0, menuView.getMeasuredHeight());
        mainView.layout(0, 0, right, bottom);
    }


    private int downX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int offset = downX - moveX;

                if (offset + getScrollX() < -menuWidth) {
                    scrollTo(-menuWidth,0);
                }else if (offset + getScrollX() > 0) {
                    scrollTo(0,0);
                } else{
                    scrollBy(offset, 0);
                }
                downX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }


}
