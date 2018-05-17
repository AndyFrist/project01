package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.adapter.Adaptertable;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {


    private ListView table_lisv;
    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        table_lisv = (ListView) findViewById(R.id.table_lisv);

        for (int i = 0;i<100;i++) {
            data.add(i + "");
        }
        table_lisv.setAdapter(new Adaptertable(this,data));
    }
}
