package com.yidong.jon.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseRecyclerAdapter;
import com.yidong.jon.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jon on 2016/11/21
 */

public class TestRecyclerAdapter extends BaseRecyclerAdapter<Integer> {
    private Context context;

    public TestRecyclerAdapter(Context context, ArrayList<Integer> list) {
        this.context = context;
        mData = list;
    }

    @Override
    protected BaseViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frist_item, parent, false);
        return new FristHolder(view);
    }

    @Override
    protected void onBind(BaseViewHolder holder, int realPosition) {
        FristHolder fristHolder = (FristHolder) holder;
        fristHolder.img.setImageResource(mData.get(realPosition));
    }

    public class FristHolder extends BaseViewHolder{
        @BindView(R.id.image)
        ImageView img;

        public FristHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
