package com.yidong.jon.nestedscroll;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.yidong.jon.R;

/**
 * Created by jon on 2017/2/15
 */

public class NestedScrollLayout extends LinearLayout implements NestedScrollingParent {
    private NestedScrollingParentHelper parentHelper;
    private RelativeLayout frameLayout;
    private ImageView imageView;
    private TextView textView;
    private TextView titleView;
    private LinearLayout linearLayout;
    private int height;
    private MyNestedScrollChild child;

    private Scroller mScroller;
    private Listener listener;
    private int titleHeight;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public NestedScrollLayout(Context context) {
        this(context, null, 0);
    }

    public NestedScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        parentHelper = new NestedScrollingParentHelper(this);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onFinishInflate() {
        frameLayout = (RelativeLayout) getChildAt(0);
        linearLayout = (LinearLayout) findViewById(R.id.content);
//        imageView = (ImageView) findViewById(R.id.image);
        titleView = (TextView) findViewById(R.id.title);
        titleView.setAlpha(0f);
//        linearLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                if (listener != null) {
//                    float fraction = (linearLayout.getY() - titleHeight) / height;
//                    listener.scrollChange(fraction);
//                }
//                return true;
//            }
//        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        titleHeight = titleView.getMeasuredHeight();
        height = frameLayout.getMeasuredHeight() - titleView.getMeasuredHeight();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getMeasuredHeight() - titleHeight);
        linearLayout.setLayoutParams(params);

    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
//        if (target instanceof RecyclerView) return true;
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
        Log.i("info", "dy: " + dy + "  getScrollY" + getScrollY());
//        if (showImg(dy) || hideImg(dy)) {
//            scrollBy(0, dy);
//            consumed[1] = dy;
//        }
//        boolean hiddenTop = dy > 0 && getScrollY() < imageHeight;
//        boolean showTop = dy < 0 && getScrollY() >= 0;

//        if (hiddenTop || showTop) {
//            scrollBy(0, dy);
//            consumed[1] = dy;
//        }
        int scrollY = getScrollY();
        if (scrollY < height) {
             scrollBy(0, dy);
             consumed[1] = dy;
            ViewCompat.setTranslationY(titleView, scrollY);
        } else if (scrollY == height) {
             if (dy < 0) {
                 scrollBy(0, dy);
                 consumed[1] = dy;
             }
            ViewCompat.setTranslationY(titleView, scrollY);
        }
        if (listener != null) {
            listener.scrollChange(Float.valueOf(scrollY)/height);
        }

    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
//        if (getScrollY() < imageHeight) {
//            fling((int) velocityY);
//            return true;
//        }
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return parentHelper.getNestedScrollAxes();
    }

    public void fling(int velocityY) {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, height);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) y = 0;
        if (y > height) y = height;
        super.scrollTo(x, y);
    }

    //下拉的时候是否要向下滚动以显示图片
    public boolean showImg(int dy) {
        if (dy > 0) {
            if (getScrollY() > 0) {
                return true;
            }
        }

        return false;
    }

    //上拉的时候，是否要向上滚动，隐藏图片
    public boolean hideImg(int dy) {
        if (dy < 0) {
            if (getScrollY() < height) {
                return true;
            }
        }
        return false;
    }

    public interface Listener{
        void scrollChange(float f);
    }
}
