package com.yidong.jon.ui.activity;

import android.os.Bundle;
import android.os.Handler;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.widget.loadview.LoadingConfig;
import com.yidong.jon.widget.loadview.LoadingLayout;

import butterknife.BindView;

public class LoadingActivity extends BaseActivity {
    @BindView(R.id.loading_layout)
    LoadingLayout loadingLayout;
//    @BindView(R.id.loading_view)
//    HungryLoadingView loadingView;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        loadingView.addBitmap(R.drawable.v4);
//        loadingView.addBitmap(R.drawable.v5);
//        loadingView.addBitmap(R.drawable.v6);
//        loadingView.addBitmap(R.drawable.v7);
//        loadingView.addBitmap(R.drawable.v8);
//        loadingView.addBitmap(R.drawable.v9);
//        loadingView.start();
        LoadingConfig config = new LoadingConfig.Builder().builder();
        loadingLayout.setLoadingConfig(config);
        loadingLayout.setStatus(LoadingLayout.LOADING);
        loadingLayout.setOnReloadListener(new LoadingLayout.onReloadListener() {
            @Override
            public void onReload() {
                loadingLayout.setStatus(LoadingLayout.LOADING);
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingLayout.setStatus(LoadingLayout.EMPTY);
            }
        }, 2000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingLayout.setStatus(LoadingLayout.SUCCESS);
            }
        }, 4000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingLayout.setStatus(LoadingLayout.ERROR);
            }
        }, 6000);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_loading;
    }
}
