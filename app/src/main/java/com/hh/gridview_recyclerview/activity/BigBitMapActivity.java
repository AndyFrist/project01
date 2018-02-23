package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.adapter.MyGridVIewAdapter;

import java.util.ArrayList;

public class BigBitMapActivity extends AppCompatActivity {
    private GridView grid_view;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_bit_map);
        grid_view = (GridView) findViewById(R.id.grid_view);
        list = new ArrayList<>();
        initData();
        grid_view.setAdapter(new MyGridVIewAdapter(this, list));
    }

    private void initData() {
        list.add("http://pic.58pic.com/58pic/13/86/94/38p58PICYiA_1024.jpg");
        list.add("http://pic1.win4000.com/wallpaper/3/53a3b16b65dbd.jpg");
        list.add("http://pic1.win4000.com/wallpaper/3/5498e1998e637.jpg");
        list.add("http://pic1.win4000.com/wallpaper/b/546b16fba04d8.jpg");
        list.add("http://pic.qiantucdn.com/58pic/18/20/84/35258PICYiu_1024.jpg");
        list.add("http://a.hiphotos.baidu.com/image/h%3D360/sign=6ae5af29bb12c8fcabf3f0cbcc0292b4/8326cffc1e178a82a16fa2a9f403738da977e86c.jpg");
        list.add("http://c.hiphotos.baidu.com/image/pic/item/83025aafa40f4bfbc9c817b9074f78f0f63618c6.jpg");
        list.add("http://c.hiphotos.baidu.com/image/pic/item/267f9e2f0708283845c852f9bc99a9014d08f112.jpg");
    }

}
