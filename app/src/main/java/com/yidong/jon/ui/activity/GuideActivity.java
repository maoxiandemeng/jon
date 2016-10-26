package com.yidong.jon.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.ui.adapter.GuideAdapter;
import com.yidong.jon.widget.viewpage.RectIndicator;
import com.yidong.jon.R;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/6/16.
 */
public class GuideActivity extends BaseActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    @BindView(R.id.circleIndicator)
    RectIndicator mRectIndicator;

    private Integer[] ids = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Integer> images = Arrays.asList(ids);
        GuideAdapter adapter = new GuideAdapter(this, images);
        mViewPager.setAdapter(adapter);
        mRectIndicator.setViewPager(mViewPager);
    }


    @Override
    public int getLayoutResId() {
        return R.layout.activity_guide;
    }

}
