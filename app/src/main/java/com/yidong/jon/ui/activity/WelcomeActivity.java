package com.yidong.jon.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.utils.Helpers;
import com.yidong.jon.R;
import com.yidong.jon.widget.CountDownView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/6/16.
 */
public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.countDownView)
    CountDownView countDownView;

    private Handler handler = new Handler();
    private DownTimeRunnable downTimeRunnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        Log.i("display", "xdpi: "+xdpi+"ydpi: "+ydpi);
        //320.815   318.456

        downTimeRunnable = new DownTimeRunnable();
        handler.postDelayed(downTimeRunnable, 5000);

        countDownView.setCountDownTimerListener(new CountDownView.CountDownTimerListener() {
            @Override
            public void onStartCount() {
                Helpers.showToastShort(WelcomeActivity.this, "计时开始");
            }

            @Override
            public void onFinishCount() {
                Helpers.showToastShort(WelcomeActivity.this, "计时结束");
            }
        });
        countDownView.start(5000);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_welcome;
    }

    @OnClick(R.id.countDownView)
    public void onDownClick(){
        handler.removeCallbacks(downTimeRunnable);
        startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
        finish();
    }

    public class DownTimeRunnable implements Runnable{

        @Override
        public void run() {
            startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
            finish();
        }
    }
}
