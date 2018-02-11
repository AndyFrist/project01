package com.hh.gridview_recyclerview.six_principle.OC_priniciple;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/7/22.
 */

public interface SaveImage {

    void put(String url, Bitmap bitmap);

    Bitmap get(String url);
}
