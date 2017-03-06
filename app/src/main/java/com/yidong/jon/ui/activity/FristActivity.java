package com.yidong.jon.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.base.BaseMvpActivity;
import com.yidong.jon.base.BaseRecyclerAdapter;
import com.yidong.jon.base.BaseViewHolder;
import com.yidong.jon.imageloader.ImageLoader;
import com.yidong.jon.imageloader.ImageLoaderUtil;
import com.yidong.jon.model.VideoEntity;
import com.yidong.jon.presenter.FristPresenter;
import com.yidong.jon.ui.adapter.OnRecyclerViewClickListener;
import com.yidong.jon.R;
import com.yidong.jon.ui.adapter.FristAdapter;
import com.yidong.jon.ui.adapter.TestRecyclerAdapter;
import com.yidong.jon.view.FristView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FristActivity extends BaseMvpActivity<FristPresenter> implements FristView{
    private static final String TAG = "FristActivity";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));

        final ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.jessica);
        list.add(R.drawable.sunny);
        list.add(R.drawable.taeyeon);
        list.add(R.drawable.tiffany);
        list.add(R.drawable.yoona);
        list.add(R.drawable.yuri);
        list.add(R.drawable.jessica);
        list.add(R.drawable.sunny);
        list.add(R.drawable.taeyeon);
        list.add(R.drawable.tiffany);
        list.add(R.drawable.yoona);
        list.add(R.drawable.yuri);

        TestRecyclerAdapter adapter = new TestRecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.header_view, recyclerView, false);
        adapter.setmHeaderView(headerView);
        adapter.setmEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, recyclerView, false));
//        FristAdapter fristAdapter = new FristAdapter(this);
//        recyclerView.setAdapter(fristAdapter);
//
//        fristAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
//            @Override
//            public void onRecyclerViewClick(View view, Object data, int position) {
//                openActivity(SecondActivity.class, createViewInfoBundle(view, (Integer) data));
//                overridePendingTransition(0,0);
//            }
//        });
        adapter.setOnRecyclerItemClickListener(new BaseRecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void setOnRecyclerItemClick(BaseViewHolder holder, int position) {
//                Toast.makeText(FristActivity.this, "position"+position, Toast.LENGTH_SHORT).show();
                openActivity(SecondActivity.class, createViewInfoBundle(holder.itemView, list.get(position)));
                overridePendingTransition(0,0);
            }
        });

        ImageView img = ButterKnife.findById(headerView, R.id.header_img);

        getVideoList();
        ImageLoader build = new ImageLoader.Builder().url("http://mvavatar1.meitudata.com/581eb003a2b2d6798.jpg")
                .placeHolder(R.mipmap.ic_launcher)
                .imgView(img)
                .build();
        ImageLoaderUtil.getInstance().loadImage(this, build);
    }

    @Override
    protected FristPresenter createPresenter() {
        return new FristPresenter(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_frist;
    }

    public void getVideoList() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("page", 1);
        map.put("count", 10);
        map.put("max_id", "");

        presenter.getInfo(map);
    }

    private Bundle createViewInfoBundle(View view, Integer i){
        Bundle bundle = new Bundle();
        int[] loaction = new int[2];
        view.getLocationOnScreen(loaction);
        int left = loaction[0];
        int top = loaction[1];
        int width = view.getWidth();
        int height = view.getHeight();
        bundle.putInt("drawable", i);
        bundle.putInt("left", left);
        bundle.putInt("top", top - getStatusBarHeight(this));
        bundle.putInt("width", width);
        bundle.putInt("height", height);
        Log.i("bundle", "left:"+left+"top:"+top+"width:"+width+"height:"+height);
        return bundle;
    }

    @Override
    public void showVideo(List<VideoEntity> list) {
        Log.i(TAG, "list: "+list.toString());
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        java.lang.reflect.Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
}
