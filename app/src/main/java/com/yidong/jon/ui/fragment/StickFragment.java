package com.yidong.jon.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseFragment;
import com.yidong.jon.ui.adapter.StickAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 *
 */
public class StickFragment extends BaseFragment {
    public static final String TITLE = "title";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ArrayList<String> mDatas = new ArrayList<String>();

    public static StickFragment newInstance(String title) {
        StickFragment tabFragment = new StickFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_stick;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        String string = getArguments().getString(TITLE);
        for (int i = 0; i < 50; i++) {
            mDatas.add(string + " Value  " + i);
        }

        StickAdapter adapter = new StickAdapter(activity, mDatas);
        recyclerView.setAdapter(adapter);
    }
}
