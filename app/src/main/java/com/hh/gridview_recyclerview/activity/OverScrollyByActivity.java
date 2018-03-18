package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.Scroll_ListView;
import com.hh.gridview_recyclerview.utils.Cheeses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OverScrollyByActivity extends BaseActivity {
    private Scroll_ListView  over_listview;

    private List<String> myList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_scrolly_by);

        initView();
    }

    private void initView() {
        myList = Arrays.asList(Cheeses.NAMES);
        over_listview = (Scroll_ListView ) findViewById(R.id.over_listview);
//        over_listview.setAdapter(new AdapterOverScrollBy(this, myList));

        over_listview.setOverScrollMode(View.OVER_SCROLL_NEVER);

        // 加Header
        final View mHeaderView = View.inflate(OverScrollyByActivity.this, R.layout.headview_layout, null);
        final ImageView mImage = (ImageView) mHeaderView.findViewById(R.id.head_image);
        over_listview.addHeaderView(mHeaderView);

        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // 当布局填充结束之后, 此方法会被调用

                over_listview.setParallaxImage(mImage);

                mHeaderView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        // 填充数据
        over_listview.setAdapter(new ArrayAdapter<>(OverScrollyByActivity.this, android.R.layout.simple_list_item_1, Cheeses.NAMES));


    }

}
