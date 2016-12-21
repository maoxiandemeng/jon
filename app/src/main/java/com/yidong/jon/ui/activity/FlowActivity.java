package com.yidong.jon.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.base.BaseRecyclerAdapter;
import com.yidong.jon.base.BaseViewHolder;
import com.yidong.jon.flow.FlowAdapter;
import com.yidong.jon.flow.FlowLayoutManager;
import com.yidong.jon.utils.Helpers;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class FlowActivity extends BaseActivity {
    @BindView(R.id.rv_flow)
    RecyclerView rvFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rvFlow.setLayoutManager(new FlowLayoutManager());
        FlowAdapter flowAdapter = new FlowAdapter(this, initDatas());
        rvFlow.setAdapter(flowAdapter);

        flowAdapter.setOnRecyclerItemClickListener(new BaseRecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void setOnRecyclerItemClick(BaseViewHolder holder, int position) {
                Helpers.showToastShort(FlowActivity.this, initDatas().get(position));
            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_flow;
    }

    private ArrayList<String> initDatas() {
        ArrayList<String> data = new ArrayList<>();
        data.add("的");
        data.add("的撒");
        data.add("发改委");
        data.add("大神");
        data.add("百合eve额");
        data.add("听不懂");
        data.add("二沃尔沃");
        data.add("切问而近思");
        data.add("阿斯蒂芬");
        data.add("大都是");
        data.add("热");
        data.add("革凡登圣");
        return data;
    }

    @OnClick({R.id.linear, R.id.flow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear:
                rvFlow.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.flow:
                rvFlow.setLayoutManager(new FlowLayoutManager());
                break;
        }
    }
}
