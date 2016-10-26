package com.yidong.jon.ui.activity;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.cardview.CardPagerAdapter;
import com.yidong.jon.cardview.ShadowTransformer;
import com.yidong.jon.R;

import butterknife.BindView;

public class CardPageActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.viewPager) ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar.setNavigationIcon(R.drawable.guide_home_on);
        CardPagerAdapter adapter = new CardPagerAdapter();
        ShadowTransformer transformer = new ShadowTransformer(viewPager, adapter);
        transformer.enableScaling(true);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(false, transformer);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_card_page;
    }
}
