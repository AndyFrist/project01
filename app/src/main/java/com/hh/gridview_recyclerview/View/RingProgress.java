package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.AndroidUtils;

/**
 * Created by Administrator on 2017/11/27.
 */

public class RingProgress extends View {
    private Context context;
    private Paint paint_bockrround;
    private Paint paint_color;
    private RectF rectf;
    private float target = 5000;
    private float value = 500;
    private float ratio = (float) 0.6;
    private float tb;
    private float sweepAngle = 4;   //每个刻度4°
    private float space = 1;        //每个间隔1°
    private float mwidth = 12;      //每个刻度的宽度
    private int screenWidth;

    public RingProgress(Context context) {
        this(context, null);
    }

    public RingProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        tb = getResources().getDimension(R.dimen.width_01);
        paint_bockrround = new Paint();
        paint_bockrround.setColor(0xffe1e1e1);
        paint_bockrround.setAntiAlias(true);// 取消锯齿
        paint_bockrround.setStyle(Paint.Style.STROKE);
        paint_bockrround.setStrokeWidth(mwidth * tb);

        paint_color = new Paint();
        paint_color.setColor(0xffF5614A);
        paint_color.setAntiAlias(true);// 取消锯齿
        paint_color.setStyle(Paint.Style.STROKE);
        paint_color.setStrokeWidth(mwidth * tb);
        screenWidth = AndroidUtils.getScreenWidth(context);
        rectf = new RectF();
        rectf.set(30, 30, (int) (175 * tb) -30, (int) (175 * tb) -30);
    }

    /**
     * 设置当前控件显示在屏幕上的宽高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) (175 * tb), (int) (175 * tb));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 72; i++) {
            if (ratio * 72 > i) {
                canvas.drawArc(rectf, i * (sweepAngle + space) - 90, sweepAngle, false, paint_color);
            } else {
                canvas.drawArc(rectf, i * (sweepAngle + space) - 90, sweepAngle, false, paint_bockrround);
            }
        }
//        canvas.drawLine(0,0,300,300,paint_bockrround);

    }

    public void set(float target, float value) {
        this.target = target;
        this.value = value;
        ratio = value / target;
        invalidate();
    }

    public void set(float value) {
        this.value = value;
        ratio = value / target;
        invalidate();
    }
}
