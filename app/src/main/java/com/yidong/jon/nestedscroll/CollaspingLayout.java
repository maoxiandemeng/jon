package com.yidong.jon.nestedscroll;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by jon on 2017/2/13
 */

public class CollaspingLayout extends FrameLayout implements NestedScrollingParent {
    private NestedScrollingParentHelper parentHelper = new NestedScrollingParentHelper(this);

    public CollaspingLayout(Context context) {
        super(context);
    }

    public CollaspingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CollaspingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        parentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        parentHelper.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        float moveY = getY() + dy;
        View parentView = (View) getParent();
        int consumedY;
        // 如果超过了父View的上边界，只消费子View到父View上边的距离
        if (moveY <= 0) {
            consumedY = - (int) getY();
        } else if (moveY >= parentView.getHeight() - getHeight()) {
            // 如果超过了父View的下边界，只消费子View到父View
            consumedY = (int) (parentView.getHeight() - getHeight() - getY());
        } else {
            // 其他情况下全部消费
            consumedY = dy;
        }

        // 对父View进行移动
        setY(getY() + consumedY);
//        scrollBy(0, -dy);

        // 将父View消费掉的放入consumed数组中
        consumed[1] = consumedY;
    }

//    @Override
//    public void scrollTo(int x, int y) {
//        int i = ((View) getParent()).getHeight() - getHeight();
//        if (y > i) y = i;
//        if (y < 0) y = 0;
//        super.scrollTo(x, y);
//    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return true;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return true;
    }

    @Override
    public int getNestedScrollAxes() {
        return parentHelper.getNestedScrollAxes();
    }
}
