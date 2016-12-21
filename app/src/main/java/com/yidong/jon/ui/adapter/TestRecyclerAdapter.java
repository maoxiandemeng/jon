package com.yidong.jon.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    private static final int TYPE_THREE = 3;
    private Context context;

    public TestRecyclerAdapter(Context context, ArrayList<Integer> list) {
        this.context = context;
        mData = list;
    }

    @Override
    public int getDefItemViewType(int position) {
        if (mData.get(position) == R.drawable.taeyeon) {
            return TYPE_THREE;
        }
        return 0;
    }

    @Override
    protected BaseViewHolder onCreate(ViewGroup parent, int viewType) {
        if (viewType == TYPE_THREE) {
            View view = LayoutInflater.from(context).inflate(R.layout.frist_item3, parent, false);
            return new ThreeHolder(view);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.frist_item, parent, false);
        return new FristHolder(view);
    }

    @Override
    protected void onBind(BaseViewHolder holder, int realPosition, int itemViewType) {
        if (itemViewType == TYPE_THREE) {
            ThreeHolder threeHolder = (ThreeHolder) holder;
            threeHolder.img.setImageResource(mData.get(realPosition));
            threeHolder.tv.setText("GGGGG" + realPosition);
            return;
        }
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

    public class ThreeHolder extends BaseViewHolder{
        @BindView(R.id.image)
        ImageView img;
        @BindView(R.id.tv)
        TextView tv;

        public ThreeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
