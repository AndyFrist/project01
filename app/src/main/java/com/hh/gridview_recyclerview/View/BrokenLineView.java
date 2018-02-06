package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.AndroidUtils;
import com.hh.gridview_recyclerview.utils.LogUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/2.
 */

public class BrokenLineView extends View {
    private static final String TAG = "BrokenLineView";
    private ArrayList<Integer> list;
    private Paint paint_side;
    private Paint paint_fill;
    private Paint paint_line;
    private float tb;
    private int max = 0;        //折线的峰值
    private float dx = 0;       //每个点的横坐标间隔
    private float ratio_y = 0;  //高和峰值的比值
    private int size;           //横坐标个数
    private float width = 0;
    private float hight = 0;

    public BrokenLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BrokenLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BrokenLineView(Context context) {
        this(context, null);
    }

    private void init() {
        tb = getResources().getDimension(R.dimen.width_01);

        paint_side = new Paint();
        paint_side.setColor(Color.GRAY);
        paint_side.setAntiAlias(true);// 取消锯齿
        paint_side.setStrokeWidth(2 * tb);

        paint_fill = new Paint();
        paint_fill.setColor(Color.GRAY);
        paint_fill.setAntiAlias(true);// 取消锯齿
        paint_fill.setStrokeWidth(2 * tb);
        //参数一为渐变起初点坐标x位置，参数二为y轴位置，参数三和四分辨对应渐变终点，最后参数为平铺方式，这里设置为镜像.
        LinearGradient lg=new LinearGradient(0,0,100,100,Color.RED,Color.BLUE, Shader.TileMode.MIRROR);
        paint_fill.setShader(lg);

        paint_line = new Paint();
        paint_line.setColor(Color.GRAY);
        paint_line.setAntiAlias(true);// 取消锯齿
        paint_line.setStrokeWidth(2 * tb);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        hight = getMeasuredHeight();
        LogUtil.d(TAG, "hight" + hight + "width" + width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(0, 0, 0, hight, paint_side);         //纵坐标
        canvas.drawLine(0, hight, width, hight, paint_side);                //横坐标


        for (int i = 0; i < size; i++) {
            canvas.drawCircle(i * dx, hight - list.get(i) * ratio_y, 3 * tb, paint_line);
            LogUtil.d(TAG, "画" + list.get(i));
            if (i > 0) {
                canvas.drawLine((i-1) * dx,hight - list.get(i-1) * ratio_y,(i) * dx,hight - list.get(i) * ratio_y,paint_line);
                LogUtil.d(TAG, "画线" + list.get(i));
            }
        }
        for (int i = 0; i < width; i++) {
            canvas.drawLine(i,0, i, hight, paint_fill);
        }
    }

    public void setData(ArrayList<Integer> list) {
        this.list = list;
        if (list == null || list.size() == 0) {
            return;
        }
        size = list.size();

        for (int i = 0; i < size; i++) {
            if (max < list.get(i)) {
                max = list.get(i);
            }
        }
        dx = width / (size - 1);
        ratio_y = hight / max;
        LogUtil.d(TAG, "设置数据" );
        invalidate();
    }
}
