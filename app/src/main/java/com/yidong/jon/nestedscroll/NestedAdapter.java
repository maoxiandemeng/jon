package com.yidong.jon.nestedscroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseRecyclerAdapter;
import com.yidong.jon.base.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by jon on 2017/2/15
 */

public class NestedAdapter extends BaseRecyclerAdapter<String> {

    public NestedAdapter(Context context, ArrayList<String> mData) {
        super(context, mData);
    }

    @Override
    public int getDefItemViewType(int position) {
        return 0;
    }

    @Override
    protected BaseViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nested_item, parent, false);
        return new NestedHolder(view);
    }

    @Override
    protected void onBind(BaseViewHolder holder, int realPosition, int itemViewType) {

    }

    public class NestedHolder extends BaseViewHolder {

        public NestedHolder(View itemView) {
            super(itemView);
        }
    }
}
