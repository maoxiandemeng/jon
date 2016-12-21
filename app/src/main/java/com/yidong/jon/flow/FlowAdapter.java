package com.yidong.jon.flow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseRecyclerAdapter;
import com.yidong.jon.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jon on 2016/12/19
 */

public class FlowAdapter extends BaseRecyclerAdapter<String> {
    private Context context;

    public FlowAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        mData = list;
    }

    @Override
    public int getDefItemViewType(int position) {
        return 0;
    }

    @Override
    protected BaseViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.flow_item, parent, false);
        return new FlowHolder(view);
    }

    @Override
    protected void onBind(BaseViewHolder holder, int realPosition, int itemViewType) {
        FlowHolder flowHolder = (FlowHolder) holder;
        flowHolder.tv.setText(mData.get(realPosition));
    }

    public class FlowHolder extends BaseViewHolder{
        @BindView(R.id.tv)
        TextView tv;

        public FlowHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
