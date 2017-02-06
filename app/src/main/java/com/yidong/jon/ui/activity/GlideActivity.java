package com.yidong.jon.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.ui.adapter.GlideAdapter;
import com.yidong.jon.ui.adapter.GlideAdapter.Type;

import java.util.ArrayList;

import butterknife.BindView;

public class GlideActivity extends BaseActivity {

    @BindView(R.id.list)
    RecyclerView list;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_glide;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.setLayoutManager(new GridLayoutManager(this, 2));

        ArrayList<Type> dataSet = new ArrayList<>();
        dataSet.add(Type.Mask);
        dataSet.add(Type.NinePatchMask);
        dataSet.add(Type.CropTop);
        dataSet.add(Type.CropCenter);
        dataSet.add(Type.CropBottom);
        dataSet.add(Type.CropSquare);
        dataSet.add(Type.CropCircle);
        dataSet.add(Type.ColorFilter);
        dataSet.add(Type.Grayscale);
        dataSet.add(Type.RoundedCorners);
        dataSet.add(Type.Blur);
        dataSet.add(Type.Toon);
        dataSet.add(Type.Sepia);
        dataSet.add(Type.Contrast);
        dataSet.add(Type.Invert);
        dataSet.add(Type.Pixel);
        dataSet.add(Type.Sketch);
        dataSet.add(Type.Swirl);
        dataSet.add(Type.Brightness);
        dataSet.add(Type.Kuawahara);
        dataSet.add(Type.Vignette);

        list.setAdapter(new GlideAdapter(this, dataSet));
    }

}
