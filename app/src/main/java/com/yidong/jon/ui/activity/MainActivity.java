package com.yidong.jon.ui.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.provider.db.MyOpenHelper;
import com.yidong.jon.ui.fragment.FourFragment;
import com.yidong.jon.ui.fragment.HomeFragment;
import com.yidong.jon.ui.fragment.ThreeFragment;
import com.yidong.jon.widget.viewpage.ScrollViewPager;
import com.yidong.jon.R;
import com.yidong.jon.ui.adapter.MainViewPagerAdapter;
import com.yidong.jon.ui.fragment.FiveFragment;
import com.yidong.jon.ui.fragment.TwoFragment;
import com.yidong.jon.widget.AlphaIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener{
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.toolBar_tv)
    TextView mTitle;
    @BindView(R.id.viewPager)
    ScrollViewPager mViewPager;
    @BindView(R.id.indicator)
    AlphaIndicator mIndicator;
//    @BindView(R.id.swipeToLoadLayout) SwipeToLoadLayout mSwipeToLoadLayout;
//    @BindView(R.id.swipe_target) RecyclerView mRecyclerView;
//
//    private String[] s = {"dwqd", "214", "dsadsds", "4354", "ngng", "ui", "vds", "ccx", "53", "mjhb", "u65jyn", "dsds", "dfds",
//            "rfgreht", "bssvs", "j65", "14e3d", "dvs", "dvsvds", "dsv", "dvscd", "ds", "facx", "vds", };
//    private MainAdapter adapter;
//    private List<String> list = new ArrayList<>();
    private String[] mTitles = {"首页", "分类", "发现", "消息", "我"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.drawable.guide_home_on);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("第一个"));
        fragments.add(TwoFragment.newInstance("第二个"));
        fragments.add(ThreeFragment.newInstance("第三个"));
        fragments.add(FourFragment.newInstance("第四个"));
        fragments.add(FiveFragment.newInstance("第五个"));
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setCanScroll(true);
        mIndicator.setViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(this);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        mSwipeToLoadLayout.setOnRefreshListener(this);
//        adapter = new MainAdapter(MainActivity.this, list);
//        mRecyclerView.setAdapter(adapter);
//        autoRefresh();
        Uri uri = Uri.parse("content://com.yidong.jon.provider.MyContentProvider/my");
        ContentValues values = new ContentValues();
        values.put(MyOpenHelper.NAME_ID, "1234567");
        getContentResolver().insert(uri, values);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTitle.setText(mTitles[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

//    @Override
//    public void onRefresh() {
//        if (list != null && !list.isEmpty()) list.clear();
//        mSwipeToLoadLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mSwipeToLoadLayout.setRefreshing(false);
//                list.addAll(Arrays.asList(s));
//                adapter.notifyDataSetChanged();
//            }
//        }, 2000);
//    }

//    private void autoRefresh(){
//        mSwipeToLoadLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mSwipeToLoadLayout.setRefreshing(true);
//            }
//        });
//    }
}
