package com.hh.gridview_recyclerview.materialdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hh.gridview_recyclerview.R;

import java.util.ArrayList;

public class MaterialDesignActivity2 extends AppCompatActivity {
    private Toolbar toolbar2;
    private RecyclerView recycle2;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design2);
        toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        recycle2 = (RecyclerView) findViewById(R.id.recycle2);
        for (int i = 0; i < 50; i++) {
            list.add(i + "个");
        }
        recycle2.setAdapter(new AdapterMaterialDesign(this, list));

        setSupportActionBar(toolbar2);
    }
}
