package com.hh.gridview_recyclerview.View;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import com.hh.gridview_recyclerview.R;

import java.util.Random;

/**
 * Created by dillon on 2017/5/11.
 */

public class PraiseModel {

    //心的当前坐标
    public Point point;
    //移动动画
    private ValueAnimator moveAnim;
    //放大动画
    private ValueAnimator zoomAnim;
    //透明度
    public int alpha = 255;
    // 心图
    private Bitmap bitmap;
    //绘制bitmap的矩阵  用来做缩放和移动的
    private Matrix matrix = new Matrix();
    //缩放系数
    private float sf = 0;
    //产生随机数
    private Random random;
    private PraiseSurfaceView praiseSurfaceView;
    public boolean isEnd = false;//是否结束
    private int[] bitmapIDs; // 存放需要展示的图
    private Bitmap[] bitmaps; // 存放需要展示的图

    /**
     * 点击坐标x ,y
     * type: 点赞效果类型、0没位移的点赞效果 1有位移的点赞效果
     * time：点赞动画周期
     */
    public PraiseModel(PraiseSurfaceView praiseSurfaceView, int x, int y, int type, long time) {
        this.random = new Random();
        this.praiseSurfaceView = praiseSurfaceView;
        bitmapIDs = new int[]{
                R.drawable.praisebig,
                //   R.drawable.praisebig2,
                //   R.drawable.praisebig3
        };
        bitmaps = new Bitmap[bitmapIDs.length];
        bitmap = getRandomBitmap();
        //始止坐标为点击位置
        init(new Point(x, y), new Point(x, y), type, time);
    }

    private void init(final Point startPoint, Point endPoint, int type, long time) {
        if (type == 0) {
            moveAnim = ValueAnimator.ofObject(new BezierEvaluator(new Point(startPoint.x, startPoint.y)), startPoint, endPoint);
        } else {
            moveAnim = ValueAnimator.ofObject(new BezierEvaluator(new Point(random.nextInt(startPoint.x * 2), Math.abs(endPoint.y - startPoint.y) / 2)), startPoint, endPoint);
        }
        moveAnim.setDuration(time);
        moveAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                point = (Point) animation.getAnimatedValue();
                alpha = (int) ((float) point.y / (float) startPoint.y * 222);
            }
        });
        moveAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                alpha = 0;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        moveAnim.start();
        zoomAnim = ValueAnimator.ofFloat(0, 1f).setDuration(time);
        zoomAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float f = (Float) animation.getAnimatedValue();
                sf = f.floatValue();
            }
        });
        zoomAnim.start();
    }

    public void stop() {
        if (moveAnim != null) {
            moveAnim.cancel();
            moveAnim = null;
        }
        if (zoomAnim != null) {
            zoomAnim.cancel();
            zoomAnim = null;
        }
    }

    //随机从存放的图片获取bitmap
    private Bitmap getRandomBitmap() {
        int n = random.nextInt(bitmapIDs.length);
        Bitmap bitmap = bitmaps[n];
        if (bitmap == null || bitmap.isRecycled()) {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inMutable = true;
            bitmap = BitmapFactory.decodeResource(praiseSurfaceView.getResources(), bitmapIDs[n], opts);
            bitmaps[n] = bitmap;
        }
        return bitmap;
    }

    //主要绘制函数
    public void draw(Canvas canvas, Paint p) {
        if (bitmap != null && alpha > 0) {
            p.setAlpha(alpha);
            matrix.setScale(sf, sf, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
            matrix.postTranslate(point.x - bitmap.getWidth() / 2, point.y - bitmap.getHeight() / 2);
            canvas.drawBitmap(bitmap, matrix, p);
        } else {
            isEnd = true;
        }
    }

    // 二次贝塞尔曲线
    private class BezierEvaluator implements TypeEvaluator<Point> {

        private Point centerPoint;

        public BezierEvaluator(Point centerPoint) {
            this.centerPoint = centerPoint;
        }

        @Override
        public Point evaluate(float t, Point startValue, Point endValue) {
            int x = (int) ((1 - t) * (1 - t) * startValue.x + 2 * t * (1 - t) * centerPoint.x + t * t * endValue.x);
            int y = (int) ((1 - t) * (1 - t) * startValue.y + 2 * t * (1 - t) * centerPoint.y + t * t * endValue.y);
            return new Point(x, y);
        }
    }
}