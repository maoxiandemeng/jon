package com.yidong.jon.nestedscroll;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jon on 2017/2/13
 */

public class NestedScrollView extends View implements NestedScrollingChild {
    private NestedScrollingChildHelper childHelper = new NestedScrollingChildHelper(this);
    private float downY;
    private int[] consumed = new int[2];
    private int[] offsetInWindow = new int[2];
    private float downX;

    public NestedScrollView(Context context) {
        this(context, null, 0);
    }

    public NestedScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setNestedScrollingEnabled(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionMasked = MotionEventCompat.getActionMasked(event);
        //取第一根手指接触屏幕的id
        int pointerId = MotionEventCompat.getPointerId(event, 0);
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                //获取当前手指的Y值
                downY = getPointerY(event, pointerId);
                downX = getPointerX(event, pointerId);
                if (downY == -1) return false;
                if (downX == -1) return false;
                //通知父View开始滑动
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL|ViewCompat.SCROLL_AXIS_HORIZONTAL);
                break;
            case MotionEvent.ACTION_MOVE:
                float pointerY = getPointerY(event, pointerId);
                float pointerX = getPointerX(event, pointerId);
                if (downY == -1) return false;
                int offsetY = (int) (pointerY - downY);
                int offsetX = (int) (pointerX - downX);
                // 通知父View, 子View想滑动 deltaY 个偏移量，父View要不要先滑一下，然后把父View滑了多少，告诉子View一下
                // 下面这个方法的前两个参数为在x，y方向上想要滑动的偏移量
                // 第三个参数为一个长度为2的整型数组，父View将消费掉的距离放置在这个数组里面
                // 第四个参数为一个长度为2的整型数组，父View在屏幕里面的偏移量放置在这个数组里面
                // 返回值为 true，代表父View有消费任何的滑动.
                if (dispatchNestedPreScroll(offsetX, offsetY, consumed, offsetInWindow)) {
                    // 偏移量需要减掉被父View消费掉的
                    offsetY -= consumed[1];
                    offsetX -= consumed[0];
                }
                // 上面的 (int)offsetY 会造成精度丢失，这里把精度给舍弃掉
//                if(Math.floor(Math.abs(offsetY)) == 0) {
//                    offsetY = 0;
//                }
                // 这里移动子View，下面的min,max是为了控制边界，避免子View越界
                setY(Math.min(Math.max(getY() + offsetY, 0), ((View) getParent()).getHeight() - getHeight()));
                setX(Math.min(Math.max(getX() + offsetX, 0), ((View) getParent()).getWidth() - getWidth()));
//                scrollBy(-offsetX, -offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

//    @Override
//    public void scrollTo(int x, int y) {
//        int h = ((View) getParent()).getHeight() - getHeight();
//        int w = ((View) getParent()).getWidth() - getWidth();
//        if (x > w) x = w;
//        if (x < 0) x = 0;
//        if (y > h) y = h;
//        if (y < 0) y = 0;
//        super.scrollTo(x, y);
//    }

    private float getPointerY(MotionEvent event, int pointerId) {
        int pointerIndex = MotionEventCompat.findPointerIndex(event, pointerId);
        if (pointerIndex < 0) return -1;
        return MotionEventCompat.getY(event, pointerIndex);
    }

    private float getPointerX(MotionEvent event, int pointerId) {
        int pointerIndex = MotionEventCompat.findPointerIndex(event, pointerId);
        if (pointerIndex < 0) return -1;
        return MotionEventCompat.getX(event, pointerIndex);
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        childHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return childHelper.isNestedScrollingEnabled();
    }

    //滑动还未开始前调用，相当于ACTION_DOWN
    @Override
    public boolean startNestedScroll(int axes) {
        return childHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        childHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return childHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return childHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return childHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return childHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return childHelper.dispatchNestedPreFling(velocityX,velocityX);
    }

}
