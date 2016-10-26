package com.yidong.jon.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yidong.jon.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/31.
 */
public class StickAdapter extends RecyclerView.Adapter<StickAdapter.StickHolder> {
    private Context context;
    private ArrayList<String> list;

    public StickAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public StickHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stick_item, parent, false);
        return new StickHolder(view);
    }

    @Override
    public void onBindViewHolder(StickHolder holder, int position) {
        String s = list.get(position);
        holder.tv.setText(s);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StickHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv)
        TextView tv;

        public StickHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
