package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.six_principle.OC_priniciple.ImageLoader;
import com.hh.gridview_recyclerview.six_principle.OC_priniciple.Iml.DoublePImageCache;
import com.hh.gridview_recyclerview.six_principle.OC_priniciple.Iml.MemoryImageCache;
import com.hh.gridview_recyclerview.six_principle.OC_priniciple.Iml.SDcardImageCache;
import com.hh.gridview_recyclerview.six_principle.OC_priniciple.SaveImage;
import com.hh.gridview_recyclerview.six_principle.single.SRPImageLoader;

public class PrincipleActivity extends AppCompatActivity {

    private Button single,openclose;
    private ImageView imagevv;
    private SRPImageLoader srpImageLoader;

    private ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principle);
        single = (Button) findViewById(R.id.single);
        openclose = (Button) findViewById(R.id.openclose);
        imagevv = (ImageView) findViewById(R.id.imagevv);
        srpImageLoader = new SRPImageLoader();
        imageLoader = new ImageLoader(new SDcardImageCache());
        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                srpImageLoader.displayImage(PrincipleActivity.this,"http://image.tianjimedia.com/uploadImages/2012/231/08/UI1077YXW2H4.jpg",imagevv);
            }
        });

        openclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoader.displayImage(PrincipleActivity.this,"http://img5.imgtn.bdimg.com/it/u=595822392,2019971519&fm=26&gp=0.jpg",imagevv);
            }
        });
    }

}
