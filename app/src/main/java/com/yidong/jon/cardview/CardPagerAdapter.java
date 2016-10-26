package com.yidong.jon.cardview;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yidong.jon.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/29.
 */
public class CardPagerAdapter extends PagerAdapter implements CardAdapter{
    private ArrayList<CardView> views;
    private float mBaseElevation;

    public CardPagerAdapter() {
        views = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            views.add(null);
        }
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.card_page_item, container, false);
        container.addView(view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }
        cardView.setMaxCardElevation(MAX_ELEVATION_FACTOR * mBaseElevation);
        views.set(position, cardView);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        views.set(position, null);
    }
}
