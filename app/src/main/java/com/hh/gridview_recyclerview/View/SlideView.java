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

public class SlideView extends FrameLayout {
    private View mainView;
    private int menuWidth = 0;
    private Scroller scroller;

    public SlideView(Context context) {
        super(context);
        initView();
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        scroller = new Scroller(getContext());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mainView = getChildAt(0);
        menuWidth = mainView.getLayoutParams().width;
    }

//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        mainView.layout(0, 0, right, bottom);
//    }


    private int downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) event.getY();
                int offset = downY - moveY;
                scrollBy(0, offset);

                downY = moveY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }


}
