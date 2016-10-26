package com.yidong.jon.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.R;

import butterknife.BindView;

public class SecondActivity extends BaseActivity {
    @BindView(R.id.container)
    RelativeLayout containerLayout;
    @BindView(R.id.big_image)
    ImageView bigImage;
    private int left;
    private int top;
    private int width;
    private int height;
    private int offsetLeft;
    private int offsetTop;
    private float scaleX;
    private float scaleY;
    private int d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        left = intent.getIntExtra("left", 0);
        top = intent.getIntExtra("top", 0);
        width = intent.getIntExtra("width", 0);
        height = intent.getIntExtra("height", 0);
        d = intent.getIntExtra("drawable", 0);
        bigImage.setImageResource(d);
        readyAnim();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_second;
    }

    private void readyAnim() {
        bigImage.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                bigImage.getViewTreeObserver().removeOnPreDrawListener(this);
                preAnim();
                enterAnim();
                return true;
            }
        });
    }

    private void preAnim() {
        int[] location = new int[2];
        bigImage.getLocationOnScreen(location);
        //开始启动动画的初始位置
        offsetLeft = left - location[0];
        offsetTop = top - location[1];
        Log.i("bundle", "location[0]:"+location[0]+"location[1]:"+location[1]);
        Log.i("bundle", "offsetLeft:"+offsetLeft+"offsetTop:"+offsetTop);
        bigImage.setTranslationX(offsetLeft);
        bigImage.setTranslationY(offsetTop);
        //缩放到原始图片的大小
        scaleX = (float)width / bigImage.getWidth();
        scaleY = (float)height / bigImage.getHeight();
        Log.i("bundle", "scaleX:"+scaleX+"scaleY:"+scaleY);
        bigImage.setScaleX(scaleX);
        bigImage.setScaleY(scaleY);
    }

    private void enterAnim(){
        bigImage.setVisibility(View.VISIBLE);
        containerLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        bigImage.animate()
                .setDuration(1000)
                .setInterpolator(new LinearInterpolator())
                .translationX(0)
                .translationY(0)
                .scaleX(1f)
                .scaleY(1f)
                .start();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        exitAnim();
    }

    private void exitAnim() {
        bigImage.setVisibility(View.VISIBLE);
        bigImage.animate()
                .setDuration(1000)
                .setInterpolator(new LinearInterpolator())
                .translationX(offsetLeft)
                .translationY(offsetTop)
                .scaleX(scaleX)
                .scaleY(scaleY)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        overridePendingTransition(0,0);
                    }
                }).start();
    }
}
