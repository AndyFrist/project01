package com.hh.gridview_recyclerview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;

import com.hh.gridview_recyclerview.Bean.Haohan;
import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.IndexBar;
import com.hh.gridview_recyclerview.adapter.AdapterIndex;
import com.hh.gridview_recyclerview.utils.Cheeses;
import com.hh.gridview_recyclerview.utils.LogUtil;
import com.hh.gridview_recyclerview.utils.PinyinUtils;
import com.hh.gridview_recyclerview.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;

public class IndexActivity extends AppCompatActivity {
    private static final String TAG = "IndexActivity";
    private ListView index_lv;
    private IndexBar index_bar;
    private String[] NAMES;
    private ArrayList<Haohan> haohans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initView();
    }

    private void initView() {
        index_lv = (ListView) findViewById(R.id.index_lv);
        index_bar = (IndexBar) findViewById(R.id.index_bar);
        index_bar.setListener(new IndexBar.OnLetterUpdateListener() {
            @Override
            public void onLetterUpdate(String letter) {
                ToastUtil.showToast(letter);
                for (int i =0;i<haohans.size();i++) {
                    String s = haohans.get(i).getPinyin().charAt(0)+"";
                    if (TextUtils.equals(s,letter)) {
                        index_lv.setSelection(i);
                        break;
                    }
                }
            }
        });
        NAMES = Cheeses.NAMES;
        for (int i = 0; i < NAMES.length; i++) {
            String name = NAMES[i];
            String pinyin = PinyinUtils.getPinyin(name);
            Haohan haohan = new Haohan(pinyin, name);
            haohans.add(haohan);
        }
        Collections.sort(haohans);

        index_lv.setAdapter(new AdapterIndex(this,haohans));
    }
}
