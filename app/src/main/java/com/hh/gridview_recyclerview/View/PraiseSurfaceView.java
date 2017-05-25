package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by dillon on 2017/5/11.
 */

public class PraiseSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private Paint paint;//画笔
    private DrawThread drawThread;// 负责绘制的工作线程
    private SurfaceHolder surfaceHolder;
    private ArrayList<PraiseModel> praiseBeenList = new ArrayList<>();// 心的个数

    public PraiseSurfaceView(Context context) {
        this(context, null);
    }

    public PraiseSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PraiseSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**设置画布  背景透明*/
        this.setZOrderOnTop(true);
        this.setFocusable(true);
        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setAntiAlias(true);
        drawThread = new DrawThread();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (drawThread == null) {
            drawThread = new DrawThread();
        }
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (drawThread != null) {
            stop();
        }
    }

    //点赞动作  控制画面最大心的个数
    public void addPraise(PraiseModel praiseModel) {
        praiseBeenList.add(praiseModel);
        if (praiseBeenList.size() > 5) {
            praiseBeenList.remove(0);
        }
        start();
    }

    class DrawThread extends Thread {
        boolean isRun = true;

        @Override
        public void run() {
            super.run();
            /**绘制的线程 死循环 不断的跑动*/
            while (isRun) {
                Canvas canvas = null;
                try {
                    synchronized (surfaceHolder) {
                        canvas = surfaceHolder.lockCanvas();
                        /**清除画面*/
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                        boolean isEnd = true;

                        /**对所有心进行遍历绘制*/
                        for (int i = 0; i < praiseBeenList.size(); i++) {
                            isEnd = praiseBeenList.get(i).isEnd;
                            praiseBeenList.get(i).draw(canvas,paint);
                        }
                        /**这里做一个性能优化的动作，由于线程是死循环的 在没有心需要的绘制的时候会结束线程*/
                        if (isEnd) {
                            isRun = false;
                            drawThread = null;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
                try {
                    /**用于控制绘制帧率*/
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void start() {
        if (drawThread == null) {
            drawThread = new DrawThread();
            drawThread.start();
        }
    }

    public void stop() {
        if (drawThread != null) {
            for (int i = 0; i < praiseBeenList.size(); i++) {
                praiseBeenList.get(i).stop();
            }

            drawThread.isRun = false;
            drawThread = null;
        }

    }
}
