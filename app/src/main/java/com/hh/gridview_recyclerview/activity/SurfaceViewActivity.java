package com.hh.gridview_recyclerview.activity;

import android.app.Activity;
import android.os.Bundle;

import com.hh.gridview_recyclerview.View.GameView;

public class SurfaceViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameView view = new GameView(this);
        setContentView(view);
    }
}
