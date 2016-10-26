package com.yidong.jon.ui.adapter;

import android.view.View;

/**
 * Created by Administrator on 2016/7/12.
 */
public interface OnRecyclerViewClickListener<T> {
    void onRecyclerViewClick(View view, T data, int position);
}
