package com.hh.gridview_recyclerview.View;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by Administrator on 2017/4/10.
 */

public abstract class Sprite {
    private Bitmap bitmap;
    private Point point;

    public Sprite(Bitmap bitmap, Point point) {
        this.bitmap = bitmap;
        this.point = point;
    }

    public void paintSelf(Canvas canvas) {
        canvas.drawBitmap(bitmap, point.x, point.y, null);
    }

}
