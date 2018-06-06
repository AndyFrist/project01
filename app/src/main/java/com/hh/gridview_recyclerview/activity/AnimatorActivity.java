package com.hh.gridview_recyclerview.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.hh.gridview_recyclerview.R;

public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView framate;
    private Button objectanimator;
    private Button tween;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        initView();
    }

    private void initView() {
        framate = (ImageView) findViewById(R.id.framate);
        framate.setOnClickListener(this);
        objectanimator = (Button) findViewById(R.id.objectanimator);
        objectanimator.setOnClickListener(this);
        tween = (Button) findViewById(R.id.tween);
        tween.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (framate == v) {
            AnimationDrawable drawable = (AnimationDrawable)framate.getDrawable();
            if (!drawable.isRunning()) {
                drawable.start();
            }else {
                drawable.stop();
                drawable.selectDrawable(1);
            }
        }

        if (v == objectanimator) {
            // 移动动画
            ObjectAnimator transAnimator = ObjectAnimator.ofFloat(objectanimator, "translationX", -500f, 300f);
            // 旋转动画
            ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(objectanimator, "rotation", 0f, 360f);
            // 淡入淡出
            ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(objectanimator, "alpha", 1f, 0f, 1f);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(rotationAnimator).with(alphaAnimator).after(transAnimator);
            animatorSet.setDuration(5000);
            animatorSet.start();
        }

        if (v == tween) {
            TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 160 * 2);
//            animation.setDuration(500);
//            use_now.setAnimation(animation);

            ScaleAnimation sa = new ScaleAnimation(0.2f, 1, 0.2f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            sa.setDuration(500);
            //填充动画的结束位置
//            sa.setRepeatCount(1);
//                sa.setRepeatMode(Animation.REVERSE);
//            sa.setFillAfter(true);

            AnimationSet set = new AnimationSet(false);
            set.addAnimation(animation);
            set.addAnimation(sa);
            set.setFillAfter(true);
            tween.startAnimation(set);
        }
    }
}
