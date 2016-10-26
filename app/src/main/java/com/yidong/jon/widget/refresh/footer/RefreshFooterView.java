package com.yidong.jon.widget.refresh.footer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yidong.jon.R;
import com.yidong.jon.widget.refresh.SwipeLoadMoreTrigger;
import com.yidong.jon.widget.refresh.SwipeTrigger;

/**
 * Created by Administrator on 2016/6/17.
 */
public class RefreshFooterView extends FrameLayout implements SwipeTrigger, SwipeLoadMoreTrigger {
    private TextView tvLoadMore;
    private ImageView ivSuccess;
    private ProgressBar progressBar;
    private int mFooterHeight;

    public RefreshFooterView(Context context) {
        this(context, null, 0);
    }

    public RefreshFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFooterHeight = getResources().getDimensionPixelOffset(R.dimen.load_more_footer_height);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvLoadMore = (TextView) findViewById(R.id.tvLoadMore);
        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onLoadMore() {
        tvLoadMore.setText("正在加载更多中...");
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onPrepare() {
        ivSuccess.setVisibility(GONE);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            ivSuccess.setVisibility(GONE);
            progressBar.setVisibility(GONE);
            if (-y >= mFooterHeight) {
                tvLoadMore.setText("释放加载更多");
            } else {
                tvLoadMore.setText("加载更多");
            }
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        progressBar.setVisibility(GONE);
        ivSuccess.setVisibility(VISIBLE);
    }

    @Override
    public void onReset() {
        ivSuccess.setVisibility(GONE);
    }
}
