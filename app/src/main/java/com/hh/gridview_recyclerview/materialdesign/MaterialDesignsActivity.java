package com.hh.gridview_recyclerview.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.activity.BaseActivity;

public class MaterialDesignsActivity extends BaseActivity implements View.OnClickListener {

    private Button material1, material2, material3, material4, material5, material6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_designs);
        material1 = (Button) findViewById(R.id.material1);
        material2 = (Button) findViewById(R.id.material2);
        material3 = (Button) findViewById(R.id.material3);
        material4 = (Button) findViewById(R.id.material4);
        material5 = (Button) findViewById(R.id.material5);
        material6 = (Button) findViewById(R.id.material6);

        material1.setOnClickListener(this);
        material2.setOnClickListener(this);
        material3.setOnClickListener(this);
        material4.setOnClickListener(this);
        material5.setOnClickListener(this);
        material6.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v == material1) {
            intent.setClass(this, MaterialDesignActivity1.class);
            startActivity(intent);
        }

        if (v == material2) {
            intent.setClass(this, MaterialDesignActivity2.class);
            startActivity(intent);
        }
        if (v == material3) {
            intent.setClass(this, MaterialDesignActivity3.class);
            startActivity(intent);
        }
        if (v == material4) {
            intent.setClass(this, MaterialDesignActivity4.class);
            startActivity(intent);
        }
    }
}
