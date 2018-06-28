package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.hh.gridview_recyclerview.utils.LogUtil;

public class PullableScrollView extends ScrollView implements Pullable {
    private static final String TAG = "PullableScrollView";

    public PullableScrollView(Context context) {
        super(context);
    }

    public PullableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canPullDown() {
        if (getScrollY() == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean canPullUp() {
        if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
            return true;
        else
            return false;
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        LogUtil.d(TAG, "deltaX=" + deltaX + "deltaY=" + deltaY + "scrollX=" + scrollX+"scrollY="+ scrollY+"scrollRangeX=" +scrollRangeX+"scrollRangeY="
                + scrollRangeY+"maxOverScrollX="+maxOverScrollX+"maxOverScrollY="+maxOverScrollY );
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
}
