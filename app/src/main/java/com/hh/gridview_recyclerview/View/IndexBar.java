package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hh.gridview_recyclerview.utils.LogUtil;
import com.hh.gridview_recyclerview.utils.ToastUtil;

/**
 * Created by xuxiaopeng
 * on 2018/1/13 0013.
 */

public class IndexBar extends View {
    private static final String TAG = "IndexBar";
    private static final String[] LETTERS = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private Paint paint;

    public IndexBar(Context context) {
        this(context, null);
    }

    public IndexBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < LETTERS.length; i++) {
            String text = LETTERS[i];
            int x = (int) (cellWidth / 2.0f - paint.measureText(text) / 2.0f);
            Rect rect = new Rect();
            paint.getTextBounds(text, 0, text.length(), rect);
            int textHight = rect.height();
            int y = (int) (cellHeigh / 2.0f + textHight / 2.0f + i * cellHeigh);
            canvas.drawText(text, x, y, paint);
        }
    }

    private  int mindex=-1;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index= (int) (event.getY() /cellHeigh);
        LogUtil.d(TAG,index+"!!!!!!!!!!!!!00000000000000000000000000");
        if (index >= LETTERS.length) {
            index = LETTERS.length - 1;
        }
        String text = LETTERS[index];
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if ( index >= 0 && index <LETTERS.length) {
                    if (mindex != index) {
                        mindex = index;
                        listener.onLetterUpdate(text);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if ( index >= 0 && index <LETTERS.length) {
                    if (mindex != index) {
                        mindex = index;
                        listener.onLetterUpdate(text);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if ( index >= 0 && index <LETTERS.length) {
                    if (mindex != index) {
                        mindex = index;
                        listener.onLetterUpdate(text);
                    }
                }
                break;
        }
        return true;
    }

    private float cellWidth;
    private float cellHeigh;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cellWidth = getMeasuredWidth();
        int height = getMeasuredHeight();
        cellHeigh = (float) (height * 1.0f / LETTERS.length);
    }

    /**
     * 暴露一个字母的监听
     */
    public interface OnLetterUpdateListener{
        void onLetterUpdate(String letter);
    }
    private OnLetterUpdateListener listener;

    public OnLetterUpdateListener getListener() {
        return listener;
    }
    /**
     * 设置字母更新监听
     * @param listener
     */
    public void setListener(OnLetterUpdateListener listener) {
        this.listener = listener;
    }
}
