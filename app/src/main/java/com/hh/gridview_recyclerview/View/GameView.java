package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hh.gridview_recyclerview.R;

/**
 * Created by Administrator on 2017/4/10.
 * xiaomi
 */
//想让 SurfaceHolder.Callback 生效必须SurfaceHolder.addcallBack(SurfaceHolder.Callback）
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private boolean flag;
    SurfaceHolder holder;
    private Thing thing;

    public GameView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);       //保证surfaceCreated，surfaceChanged，surfaceDestroyed
    }

    @Override
    public SurfaceHolder getHolder() {
        return super.getHolder();
    }

    private MyThread thread;

    public class MyThread extends Thread {
        @Override
        public void run() {
            while (flag) {
                try {
                    drawUI();
                    sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    ;

    Paint paint = new Paint();
    int i = 0;

    private void drawUI() {
        i++;
//        锁定画布
        Canvas canvas = holder.lockCanvas(new Rect(0, 0, 300, 300));
//        绘制界面
        if (i > 10) {
            i = 0;
            paint.setColor(Color.WHITE);
        } else {
            if (i % 2 == 0) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.BLUE);
            }
        }
        canvas.drawRect(0, 0, 300, 300, paint);

//        thing.paintSelf(canvas);
//        解锁画布并提交
        holder.unlockCanvasAndPost(canvas);

        Canvas canvas1 = holder.lockCanvas(new Rect(300, 300, 400, 400));
        canvas1.drawCircle(350, 350, 30, paint);
        holder.unlockCanvasAndPost(canvas1);
    }


    //    surface创建时调用
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread = new MyThread();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aa);
        thing = new Thing(bitmap, new Point(0, 0));
        flag = true;
        thread.start();
    }

    //    surface改变时调用
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    //    surface回收时调用
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        flag = false;
        thread = null;
    }
}
