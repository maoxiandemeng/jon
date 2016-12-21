package com.yidong.jon.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/16.
 */
public abstract class BaseFragment extends Fragment {
    protected BaseActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView  = inflater.inflate(getLayoutResId(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (BaseActivity) getActivity();
    }

    public abstract int getLayoutResId();

    public void openActivity(Class<? extends BaseActivity> className){
        activity.openActivity(className, null);
    }

    public void openActivity(Class<? extends BaseActivity> className, Bundle bundle){
        activity.openActivity(className, bundle);
    }
}
