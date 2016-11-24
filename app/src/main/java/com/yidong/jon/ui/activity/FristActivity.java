package com.yidong.jon.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.base.BaseRecyclerAdapter;
import com.yidong.jon.base.BaseViewHolder;
import com.yidong.jon.ui.adapter.OnRecyclerViewClickListener;
import com.yidong.jon.R;
import com.yidong.jon.ui.adapter.FristAdapter;
import com.yidong.jon.ui.adapter.TestRecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class FristActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));

        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.jessica);
        list.add(R.drawable.sunny);
        list.add(R.drawable.taeyeon);
        list.add(R.drawable.tiffany);
        list.add(R.drawable.yoona);
        list.add(R.drawable.yuri);

        TestRecyclerAdapter adapter = new TestRecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);
        adapter.setmHeaderView(LayoutInflater.from(this).inflate(R.layout.header_view, recyclerView, false));
        adapter.setmEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, recyclerView, false));
//        FristAdapter fristAdapter = new FristAdapter(this);
//        recyclerView.setAdapter(fristAdapter);
//
//        fristAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
//            @Override
//            public void onRecyclerViewClick(View view, Object data, int position) {
//                openActivity(SecondActivity.class, createViewInfoBundle(view, (Integer) data));
//                overridePendingTransition(0,0);
//            }
//        });
        adapter.setOnRecyclerItemClickListener(new BaseRecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void setOnRecyclerItemClick(BaseViewHolder holder, int position) {
                Toast.makeText(FristActivity.this, "position"+position, Toast.LENGTH_SHORT).show();
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
