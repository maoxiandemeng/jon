package com.yidong.jon.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yidong.jon.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/17.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
    private Context context;
    private List<String> list;

    public MainAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_recycler_item, parent, false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null || list.isEmpty() ? 0 : list.size();
    }

    public class MainHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv)
        TextView tv;

        public MainHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
