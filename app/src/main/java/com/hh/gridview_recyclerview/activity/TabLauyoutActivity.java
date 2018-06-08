package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;

import java.util.ArrayList;

public class TabLauyoutActivity extends BaseActivity {

    private TabLayout tab;
    private ViewPager pager;
    private Toolbar toobar;
    private DrawerLayout drawerlayout;
    private Myadapter myadapter;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_lauyout);
        tab = (TabLayout) findViewById(R.id.tab);
        pager = (ViewPager) findViewById(R.id.pager);
        toobar = (Toolbar)findViewById(R.id.toobar);
        setSupportActionBar(toobar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(TabLauyoutActivity.this,drawerlayout,toobar,0,0);
        drawerlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();






        init();
    }

    private void init() {
        for (int i = 0; i < 40; i++) {
            list.add("第" + i + "页");
        }
        myadapter = new Myadapter();
        pager.setAdapter(myadapter);
        tab.setTabsFromPagerAdapter(myadapter);
        tab.setupWithViewPager(pager);
    }

    class Myadapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;//官方推荐写法
        }

        private View view;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            view = View.inflate(TabLauyoutActivity.this, R.layout.item_tablayout, null);
            TextView viewById = (TextView) view.findViewById(R.id.tab_tv);
            viewById.setText(list.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //删除页卡
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);//页卡标题
        }
    }
}
