package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hh.gridview_recyclerview.R;

/**
 * Created by xuxiapeng
 * <p>
 * on 2017/12/8.
 */

public class MyImageView extends View {
    private Paint paint;
    private float lt, lb, rt, rb;
    private RectF r_LT, r_LB, r_RT, r_RB;
    private Drawable drawable;
    private Bitmap drawBitmap;
    private int width;
    private int hight;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.my_imageview);

        lt = array.getDimension(R.styleable.my_imageview_lt, 0);
        lb = array.getDimension(R.styleable.my_imageview_lb, 0);
        rt = array.getDimension(R.styleable.my_imageview_rt, 0);
        rb = array.getDimension(R.styleable.my_imageview_rb, 0);
        drawable = array.getDrawable(R.styleable.my_imageview_drawable);
        BitmapDrawable bd = (BitmapDrawable) drawable;
        drawBitmap = bd.getBitmap();
        init();
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init() {
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        hight = getMeasuredHeight();

        System.out.println("width=" + width +"hight=" + hight);

        int width1 = getWidth();
        int height = getHeight();

        System.out.println("呵呵width1=" + width1 +"height=" + height);

        r_LT = new RectF(0, 0, this.width * 2 / 3, hight * 2 / 3);
        r_LB = new RectF(0, hight * 1 / 3, this.width * 2 / 3, hight);
        r_RT = new RectF(this.width * 1 / 3, 0, this.width, hight * 2 / 3);
        r_RB = new RectF(this.width * 1 / 3, hight * 1 / 3, this.width, hight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap circleImage = createImage(drawBitmap, width, hight);
        canvas.drawBitmap(circleImage, 0, 0, paint);

    }

    private Bitmap createImage(Bitmap source, int width, int hight) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(width, hight, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        //左上
        canvas.drawRoundRect(r_LT, 20, 20, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        //右上
//        canvas.drawRoundRect(r_RT, 20, 20, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
//
//        //左下
//        canvas.drawRoundRect(r_LB, 200, 200, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
//
//        //右下
//        canvas.drawRoundRect(r_RB, 200, 200, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));

        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

}
