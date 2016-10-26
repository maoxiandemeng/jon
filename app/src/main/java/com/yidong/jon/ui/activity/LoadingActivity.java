package com.yidong.jon.ui.activity;

import android.os.Bundle;

import com.yidong.jon.widget.HungryLoadingView;
import com.yidong.jon.R;
import com.yidong.jon.base.BaseActivity;

import butterknife.BindView;

public class LoadingActivity extends BaseActivity {
    @BindView(R.id.loading_view)
    HungryLoadingView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingView.addBitmap(R.drawable.v4);
        loadingView.addBitmap(R.drawable.v5);
        loadingView.addBitmap(R.drawable.v6);
        loadingView.addBitmap(R.drawable.v7);
        loadingView.addBitmap(R.drawable.v8);
        loadingView.addBitmap(R.drawable.v9);
        loadingView.start();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_loading;
    }
}
