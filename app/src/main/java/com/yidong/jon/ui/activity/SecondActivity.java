package com.yidong.jon.ui.activity;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private float startScale;
    private float endScale;
    private float endLeft;
    private float endTop;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setAlpha(0f);
        containerLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        Intent intent = getIntent();
        left = intent.getIntExtra("left", 0);
        top = intent.getIntExtra("top", 0);
        width = intent.getIntExtra("width", 0);
        height = intent.getIntExtra("height", 0);




        d = intent.getIntExtra("drawable", 0);
        bitmap = BitmapFactory.decodeResource(getResources(), d);
        Log.i("bundle", "left:"+left+"top:"+top+"width:"+width+"height:"+height);
        bigImage.setImageResource(d);
        readyAnim();
//        containerLayout.setAlpha(0f);
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
        endLeft = location[0];
        endTop = location[1];
//        offsetLeft = left - location[0];
//        offsetTop = top - location[1];
        Log.i("bundle", "location[0]:"+location[0]+"location[1]:"+location[1]);
        Log.i("bundle", "offsetLeft:"+offsetLeft+"offsetTop:"+offsetTop);
//        bigImage.setTranslationX(offsetLeft);
//        bigImage.setTranslationY(offsetTop);
        //缩放到原始图片的大小
        scaleX = (float)width / bigImage.getWidth();
        scaleY = (float)height / bigImage.getHeight();
        Log.i("bundle", "scaleX:"+scaleX+"scaleY:"+scaleY);
        startScale = scaleX > scaleY ? scaleX : scaleY;
//        bigImage.setScaleX(scaleX);
//        bigImage.setScaleY(scaleY);
        float eScaleX = (float)bigImage.getWidth() / bitmap.getWidth();
        float eScaleY = (float)bigImage.getHeight() / bitmap.getHeight();
        endScale = eScaleX > eScaleY ? eScaleY : eScaleX;
    }

    private void enterAnim(){
        bigImage.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new LinearInterpolator());
        PropertyValuesHolder scaleHolder = PropertyValuesHolder.ofFloat("scale", startScale, 1);
        PropertyValuesHolder leftHolder = PropertyValuesHolder.ofFloat("left", left, endLeft);
        PropertyValuesHolder topHolder = PropertyValuesHolder.ofFloat("top", top, endTop);
        PropertyValuesHolder widthHolder = PropertyValuesHolder.ofFloat("width", width, bigImage.getWidth());
        PropertyValuesHolder heightHolder = PropertyValuesHolder.ofFloat("height", height, bigImage.getHeight());
        PropertyValuesHolder alphaHolder = PropertyValuesHolder.ofInt("alpha", 0, 255);
        valueAnimator.setValues(scaleHolder, leftHolder, topHolder, widthHolder, heightHolder, alphaHolder);
//        bigImage.animate()
//                .setDuration(1000)
//                .setInterpolator(new LinearInterpolator())
//                .translationX(0)
//                .translationY(0)
//                .scaleX(1f)
//                .scaleY(1f)
//                .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//
//                    }
//                })
//                .start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float left = (Float) animation.getAnimatedValue("left");
                float top = (Float) animation.getAnimatedValue("top");
                float width = (Float) animation.getAnimatedValue("width");
                float height = (Float) animation.getAnimatedValue("height");
                float scale = (Float) animation.getAnimatedValue("scale");
                Integer bgAlpha = (Integer) animation.getAnimatedValue("alpha");
                bigImage.setLeft((int) left);
                bigImage.setTop((int) top);
                bigImage.setScaleX(scale);
                bigImage.setScaleY(scale);
                bigImage.setMaxWidth((int)width);
                bigImage.setMaxHeight((int)height);
                getWindow().getDecorView().setAlpha(bgAlpha/255f);
            }
        });
        valueAnimator.start();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        exitAnim();
    }

    private void exitAnim() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new LinearInterpolator());
        PropertyValuesHolder scaleHolder = PropertyValuesHolder.ofFloat("scale", 1, startScale);
        PropertyValuesHolder leftHolder = PropertyValuesHolder.ofFloat("left", endLeft, left);
        PropertyValuesHolder topHolder = PropertyValuesHolder.ofFloat("top", endTop, top);
        PropertyValuesHolder widthHolder = PropertyValuesHolder.ofFloat("width", bigImage.getWidth(), width);
        PropertyValuesHolder heightHolder = PropertyValuesHolder.ofFloat("height", bigImage.getHeight(), height);
        PropertyValuesHolder alphaHolder = PropertyValuesHolder.ofInt("alpha", 255, 0);
        valueAnimator.setValues(scaleHolder, leftHolder, topHolder, widthHolder, heightHolder, alphaHolder);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float left = (Float) animation.getAnimatedValue("left");
                float top = (Float) animation.getAnimatedValue("top");
                float width = (Float) animation.getAnimatedValue("width");
                float height = (Float) animation.getAnimatedValue("height");
                float scale = (Float) animation.getAnimatedValue("scale");
                Integer bgAlpha = (Integer) animation.getAnimatedValue("alpha");
                bigImage.setLeft((int) left);
                bigImage.setTop((int) top);
                bigImage.setScaleX(scale);
                bigImage.setScaleY(scale);
//                bigImage.setMaxWidth((int)width);
//                bigImage.setMaxHeight((int)height);
                getWindow().getDecorView().setAlpha(bgAlpha/255f);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
//        bigImage.setVisibility(View.VISIBLE);
//        bigImage.animate()
//                .setDuration(1000)
//                .setInterpolator(new LinearInterpolator())
//                .translationX(offsetLeft)
//                .translationY(offsetTop)
//                .scaleX(scaleX)
//                .scaleY(scaleY)
//                .withEndAction(new Runnable() {
//                    @Override
//                    public void run() {
//                        finish();
//                        overridePendingTransition(0,0);
//                    }
//                }).start();
    }
}
