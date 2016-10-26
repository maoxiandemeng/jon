package com.yidong.jon.widget.viewpage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.yidong.jon.R;

/**
 * Created by Administrator on 2016/6/17.
 */
public class RectIndicator extends View {
    private static final int DEFAULT_WIDTH = 40;
    private static final int DEFAULT_HEIGHT = 20;

    private int normalRadiusColor = 0xDD333333;  //普通点的颜色
    private int selectedRadiusColor = 0xAAFF0000; //选中点的颜色
    private float dotPadding = 10;   //每个点之间的距离
    private boolean isStroke = true; //是否是空心
    private float normalStrokeWidth = 1;    //空心的线宽
    private boolean isBlink = false;    //是否是以闪现的方式滑动

    private int dotNum;  //要展示的点的数量,默认按照Adapter的Count，如果设置Num，以设置的为准
    private Paint mNormalPaint;
    private Paint mSelectedPaint;
    private float rectWidth;
    private float rectHeight;
    private float mRx;  //第一个Rect的X坐标
    private float mRy;  //第一个Rect的Y坐标
    private ViewPager mViewPager;
    private MyOnPageChangeListener mListener;
    private float mTranslationX;  //移动的偏移量
    private boolean isDetached;   //是否被回收过

    public RectIndicator(Context context) {
        this(context, null);
    }

    public RectIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rectWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rectWidth, getResources().getDisplayMetrics());
        rectHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rectHeight, getResources().getDisplayMetrics());
        dotPadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dotPadding, getResources().getDisplayMetrics());
        normalStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, normalStrokeWidth, getResources().getDisplayMetrics());

        TypedArray array = getResources().obtainAttributes(attrs, R.styleable.RectIndicator);
        rectWidth = array.getDimension(R.styleable.RectIndicator_ri_rectWidth, DEFAULT_WIDTH);
        rectHeight = array.getDimension(R.styleable.RectIndicator_ri_rectHeight, DEFAULT_HEIGHT);
        normalRadiusColor = array.getColor(R.styleable.RectIndicator_ri_normalColor, normalRadiusColor);
        selectedRadiusColor = array.getColor(R.styleable.RectIndicator_ri_selectedColor, selectedRadiusColor);
        dotPadding = array.getDimension(R.styleable.RectIndicator_ri_dotPadding, dotPadding);
        isStroke = array.getBoolean(R.styleable.RectIndicator_ri_isStroke, isStroke);
        normalStrokeWidth = array.getDimension(R.styleable.RectIndicator_ri_normalStrokeWidth, normalStrokeWidth);
        isBlink = array.getBoolean(R.styleable.RectIndicator_ri_isBlink, isBlink);
        array.recycle();
        ;

        initPaint();
    }

    private void initPaint() {
        mNormalPaint = new Paint();
        mNormalPaint.setAntiAlias(true);
        mNormalPaint.setColor(normalRadiusColor);
        mNormalPaint.setStrokeWidth(normalStrokeWidth);
        if (isStroke)
            mNormalPaint.setStyle(Paint.Style.STROKE);
        else mNormalPaint.setStyle(Paint.Style.FILL);

        mSelectedPaint = new Paint();
        mSelectedPaint.setAntiAlias(true);
        mSelectedPaint.setColor(selectedRadiusColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float availableWidth = w - getPaddingLeft() - getPaddingRight();
        float availableHeight = h - getPaddingTop() - getPaddingBottom();
        float drawWidth = (dotNum - 1) * dotPadding + rectWidth * dotNum;
        if (dotNum <= 0) drawWidth = 0;
        mRx = (availableWidth - drawWidth) / 2 + getPaddingLeft();
        mRy = availableHeight + getPaddingTop();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (dotNum > 0) {
            for (int i = 0; i < dotNum; i++) {
                //绘制普通的Rect
                canvas.drawRect(mRx + i * (dotPadding + rectWidth), mRy - rectHeight, mRx + rectWidth + i * (dotPadding + rectWidth), mRy, mNormalPaint);
            }
            //绘制选中的Rect
            canvas.drawRect(mRx + mTranslationX, mRy - rectHeight, mRx + mTranslationX + rectWidth, mRy, mSelectedPaint);
        }
    }

    public RectIndicator setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        if (mViewPager == null || mViewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager不能为空或者ViewPager没有设置Adapter！");
        }
        dotNum = mViewPager.getAdapter().getCount();
        mListener = new MyOnPageChangeListener();
        mViewPager.addOnPageChangeListener(mListener);
        return this;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isDetached && mViewPager != null && mListener != null) {
            mViewPager.addOnPageChangeListener(mListener);
            isDetached = false;
        }
    }

    /**
     * 移除监听
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mViewPager != null && mListener != null)
            mViewPager.removeOnPageChangeListener(mListener);
        isDetached = true;
    }

    private class MyOnPageChangeListener extends ViewPager.SimpleOnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (dotNum > 0) {
                if (isBlink) {
                    if (positionOffset == 0)
                        mTranslationX = position * (dotPadding + rectWidth);
                } else {
                    //对循环滑动的ViewPager做特殊的处理,滑动到边界的时候，继续滑动，小点应该返回
                    if ((position == dotNum - 1) && positionOffset > 0)
                        mTranslationX = (dotNum - 1) * (dotPadding + rectWidth) * (1 - positionOffset);
                    else
                        mTranslationX = (position + positionOffset) * (dotPadding + rectWidth);
                }
                invalidate();
            }
        }
    }
}
