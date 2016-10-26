package com.yidong.jon.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/10.
 */
public class HungryLoadingView extends View {
    private static final String TAG = HungryLoadingView.class.getSimpleName();

    private static final int SHADOW_COLOR = Color.LTGRAY;
    private int mShadowColor = SHADOW_COLOR;

    //画笔
    private Paint mBitmapPaint;
    private Paint mShadowPaint;

    //建议图片大小相同
    private ArrayList<Bitmap> mBitmapList;
    private Bitmap mCurrentBitmap;
    private int mCurrentIndex;

    private float mPercent;

    private float mHalfShadowHeight;
    private float mHalfShadowWidth;
    private float mTopMargin;

    private Matrix matrix;
    private RectF mShadowRectF;
    private ValueAnimator animator;

    public HungryLoadingView(Context context) {
        this(context, null, 0);
    }

    public HungryLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HungryLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HungryLoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        mPercent = 0f;
        mCurrentIndex = 0;
        matrix = new Matrix();
        mShadowRectF = new RectF();
        mBitmapList = new ArrayList<>();

        mShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mShadowPaint.setStyle(Paint.Style.FILL);
        mShadowPaint.setColor(mShadowColor);

        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void addBitmap(int bitMapId) {
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), bitMapId);
            addBitmap(bitmap);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            mBitmapList.add(bitmap);
        }
    }

    public void addBitmaps(ArrayList<Bitmap> bitmaps) {
        if (bitmaps != null) {
            mBitmapList.addAll(bitmaps);
        }
    }

    public void start() {
        stop();
        if (animator == null) {
            animator = ValueAnimator.ofFloat(0f, 1f, 0f);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (float) valueAnimator.getAnimatedValue();

                    if (value != mPercent) {
                        mPercent = value;
                        postInvalidate();
                    }
                }
            });

            animator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationStart(Animator animation) {
                    //重置index
                    mCurrentIndex = 0;
                    mCurrentBitmap = mBitmapList.get(mCurrentIndex);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    if (mBitmapList != null && mBitmapList.size() > 0) {
                        mCurrentIndex++;
                        if (mCurrentIndex >= mBitmapList.size()) {
                            mCurrentIndex = 0;
                        }
                        mCurrentBitmap = mBitmapList.get(mCurrentIndex);
                    }
                }
            });
        }
        animator.setDuration(1000);
        animator.start();
    }

    public void stop() {
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mHalfShadowWidth = Math.max(16, getWidth() / 2f * mPercent);
        mHalfShadowHeight = Math.max(8, getHeight() / 8f * mPercent);

        mShadowRectF.set(getWidth() / 2f - mHalfShadowWidth,
                getHeight() * 7 / 8f - mHalfShadowHeight,
                getWidth() / 2f + mHalfShadowWidth,
                getHeight() * 7 / 8f + mHalfShadowHeight);
        canvas.drawOval(mShadowRectF, mShadowPaint);

        if (mCurrentBitmap != null) {
            canvas.save();
            mTopMargin = (getHeight() * 0.9f - mCurrentBitmap.getHeight()) * mPercent;
            canvas.translate(getWidth() / 2f - mCurrentBitmap.getWidth() / 2f, mTopMargin);
            canvas.drawBitmap(mCurrentBitmap, matrix, mBitmapPaint);
            canvas.restore();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, 2 * width);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }
}
