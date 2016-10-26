package com.yidong.jon.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;

import com.yidong.jon.widget.stick.StickIndicator;
import com.yidong.jon.R;
import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.ui.fragment.StickFragment;

import butterknife.BindView;

public class StickActivity extends BaseActivity {
    @Nullable
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @Nullable
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @Nullable
    @BindView(R.id.indicator)
    StickIndicator indicator;

    private String[] mTitles = new String[]{"简介", "评价", "相关"};
    private FragmentPagerAdapter mAdapter;
    private StickFragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragments = new StickFragment[mTitles.length];
        indicator.setTitles(mTitles);
        indicator.setViewPager(viewPager);
        for (int i = 0; i < mTitles.length; i++) {
            StickFragment fragment = StickFragment.newInstance(mTitles[i]);
            mFragments[i] = fragment;
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

        };

        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.scroll(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       swipeLayout.setRefreshing(false);
                   }
               }, 3000);
            }
        });

//        swipeLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//               swipeLayout.postDelayed(new Runnable() {
//                   @Override
//                   public void run() {
//                       swipeLayout.setRefreshing(false);
//                   }
//               }, 3000);
//            }
//        });

//        swipeLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                swipeLayout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeLayout.setLoadingMore(false);
//                    }
//                }, 3000);
//            }
//        });

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_stick;
    }
}
