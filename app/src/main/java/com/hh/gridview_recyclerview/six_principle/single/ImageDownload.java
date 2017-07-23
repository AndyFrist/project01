package com.hh.gridview_recyclerview.six_principle.single;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/7/22.
 */

public class ImageDownload {
    public static Bitmap download(String url) {
        Bitmap bitmap = null;
        try {
            URL url1 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
            bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
