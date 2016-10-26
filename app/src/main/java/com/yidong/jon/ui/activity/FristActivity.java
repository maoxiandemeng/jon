package com.yidong.jon.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.ui.adapter.OnRecyclerViewClickListener;
import com.yidong.jon.R;
import com.yidong.jon.ui.adapter.FristAdapter;

import butterknife.BindView;

public class FristActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));

        FristAdapter fristAdapter = new FristAdapter(this);
        recyclerView.setAdapter(fristAdapter);

        fristAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void onRecyclerViewClick(View view, Object data, int position) {
                openActivity(SecondActivity.class, createViewInfoBundle(view, (Integer) data));
                overridePendingTransition(0,0);
            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_frist;
    }

    private Bundle createViewInfoBundle(View view, Integer i){
        Bundle bundle = new Bundle();
        int[] loaction = new int[2];
        view.getLocationOnScreen(loaction);
        int left = loaction[0];
        int top = loaction[1];
        int width = view.getWidth();
        int height = view.getHeight();
        bundle.putInt("drawable", i);
        bundle.putInt("left", left);
        bundle.putInt("top", top);
        bundle.putInt("width", width);
        bundle.putInt("height", height);
        Log.i("bundle", "left:"+left+"top:"+top+"width:"+width+"height:"+height);
        return bundle;
    }
}
