package com.hh.gridview_recyclerview.materialdesign;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;

import java.util.ArrayList;

public class MaterialDesignActivity extends AppCompatActivity implements SwipeDismissBehavior.OnDismissListener ,View.OnClickListener{
    private TextView text_view;
    private RecyclerView recycle;
    private ArrayList<String> list = new ArrayList<>();
    private Toolbar toolbar;
    private FloatingActionButton fbtn;
    private ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        text_view = (TextView) findViewById(R.id.text_view);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        for (int i = 0; i < 50; i++) {
            list.add(i + "个");
        }
        recycle.setAdapter(new AdapterMaterialDesign(this, list));
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) text_view.getLayoutParams();
//        SwipeDismissBehavior swipeDismissBehavior = new SwipeDismissBehavior();
//        swipeDismissBehavior.setListener(this);
//        layoutParams.setBehavior(swipeDismissBehavior);

//        MyBehavior myBehavior = new MyBehavior();
//        layoutParams.setBehavior(myBehavior);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("标题");

        fbtn = (FloatingActionButton) findViewById(R.id.fbtn);
        btn = (ImageButton) findViewById(R.id.btn);
        fbtn.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    @Override
    public void onDismiss(View view) {
        text_view.setVisibility(View.GONE);
        Snackbar.make(view, "删除了", Snackbar.LENGTH_LONG).setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_view.setVisibility(View.VISIBLE);
                ViewCompat.animate(text_view).alpha(1).start();
            }
        }).show();
    }

    @Override
    public void onDragStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        Snackbar.make(view,"点击了",Snackbar.LENGTH_LONG).show();
    }
}
