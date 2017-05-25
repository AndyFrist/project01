package com.hh.gridview_recyclerview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hh.gridview_recyclerview.R;
/**
 * @author  zkk
 * 简书:    http://www.jianshu.com/u/61f41588151d
 * github: https://github.com/panacena
 */
public class LottieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);

        // activity_simple.xml中 lottie_fileName="data.json"
        // 所以只需要在 app/src/main/assets 中添加AE 生成的 json文件，重命名为data.json就可以显示动画
    }
}
