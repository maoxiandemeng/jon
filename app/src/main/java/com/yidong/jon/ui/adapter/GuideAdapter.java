package com.yidong.jon.ui.adapter;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.ui.activity.MainActivity;
import com.yidong.jon.utils.Helpers;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
public class GuideAdapter extends PagerAdapter implements View.OnClickListener{
    private BaseActivity activity;
    private List<Integer> images;

    public GuideAdapter(BaseActivity activity, List<Integer> images) {
        this.activity = activity;
        this.images = images;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view;
        if (position == images.size() - 1){
            RelativeLayout layout = new RelativeLayout(activity.getApplicationContext());
            layout.setBackgroundResource(images.get(position));
            Button button = new Button(activity.getApplicationContext());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.leftMargin = params.rightMargin = Helpers.dpToPx(20, activity.getApplicationContext());
            params.bottomMargin = Helpers.dpToPx(40, activity.getApplicationContext());
            button.setLayoutParams(params);
            button.setText("立即进入");
            button.setOnClickListener(this);
            layout.addView(button);
            container.addView(layout);
            view = layout;
        } else {
            ImageView imageView = new ImageView(activity.getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(images.get(position));
            container.addView(imageView);
            view = imageView;
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onClick(View v) {
        activity.startActivity(new Intent(activity, MainActivity.class));
        activity.finish();
    }
}
