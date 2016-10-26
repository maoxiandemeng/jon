package com.yidong.jon.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yidong.jon.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/31.
 */
public class FristAdapter extends RecyclerView.Adapter<FristAdapter.FristHolder> {
    private Context context;
    private ArrayList<Integer> list;
    private OnRecyclerViewClickListener onRecyclerViewClickListener;

    public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener onRecyclerViewClickListener) {
        this.onRecyclerViewClickListener = onRecyclerViewClickListener;
    }

    public FristAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        list.add(R.drawable.jessica);
        list.add(R.drawable.sunny);
        list.add(R.drawable.taeyeon);
        list.add(R.drawable.tiffany);
        list.add(R.drawable.yoona);
        list.add(R.drawable.yuri);
    }

    @Override
    public FristHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frist_item, parent, false);
        return new FristHolder(view);
    }

    @Override
    public void onBindViewHolder(final FristHolder holder, final int position) {
        final Integer d = list.get(position);
        holder.img.setImageResource(d);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecyclerViewClickListener != null) {
                    onRecyclerViewClickListener.onRecyclerViewClick(holder.img, d, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FristHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image)
        ImageView img;

        public FristHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
