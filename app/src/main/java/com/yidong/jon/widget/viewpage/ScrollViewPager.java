package com.yidong.jon.widget.viewpage;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/6/20.
 */
public class ScrollViewPager extends ViewPager {
    //默认为true表示可以滑动，false表示不滑动
    private boolean canScroll;

    public ScrollViewPager(Context context) {
        this(context, null);
    }

    public ScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        canScroll = true;
    }

    public boolean isCanScroll() {
        return canScroll;
    }

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (canScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (canScroll) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }
}
