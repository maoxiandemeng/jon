package com.yidong.jon.retrofitdownload;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseRecyclerAdapter;
import com.yidong.jon.base.BaseViewHolder;
import com.yidong.jon.retrofit.download.DownInfo;
import com.yidong.jon.retrofit.download.DownState;
import com.yidong.jon.retrofit.download.HttpDownManager;
import com.yidong.jon.retrofit.download.listener.HttpDownOnNextListener;
import com.yidong.jon.retrofitdownload.download.DownLoadBackListener;
import com.yidong.jon.retrofitdownload.download.DownLoadManager;
import com.yidong.jon.retrofitdownload.download.db.DownLoadEntity;
import com.yidong.jon.retrofitdownload.retrofit.NetWorkRequest;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jon on 2016/12/22
 */

public class RetrofitDownloadAdapter extends BaseRecyclerAdapter<DownLoadEntity> {
    private static final String TAG = "RetrofitDownloadAdapter";

    public RetrofitDownloadAdapter(Context context, ArrayList<DownLoadEntity> mData) {
        super(context, mData);
        NetWorkRequest.getInstance().init(context, "http://newapi.meipai.com/output/");
    }

    @Override
    public int getDefItemViewType(int position) {
        return 0;
    }

    @Override
    protected BaseViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.retrofit_download_item, parent, false);
        return new RetrofitDownloadHolder(view);
    }

    @Override
    protected void onBind(BaseViewHolder holder, final int realPosition, int itemViewType) {
        final DownLoadEntity test = mData.get(realPosition);
        final RetrofitDownloadHolder downloadHolder = (RetrofitDownloadHolder) holder;
        downloadHolder.downloadName.setText(test.name);

        downloadHolder.downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownLoadManager.getInstance().downLoad(test, realPosition+"", new DownLoadBackListener() {
                    @Override
                    public void onStart(double percent) {
                        Log.i(TAG, "onStart: ");
                    }

                    @Override
                    public void onCancel() {
                        Log.i(TAG, "onCancel: ");
                    }

                    @Override
                    public void onDownLoading(double percent) {
                        Log.i(TAG, "onDownLoading: "+percent);
                        downloadHolder.downloadProgress.setProgress((int)(percent*100));
                    }

                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(DownLoadEntity downLoadEntity, Throwable throwable) {
                        Log.i(TAG, "onError: "+throwable.getMessage());
                    }
                }, 0);
            }
        });

    }

    public class RetrofitDownloadHolder extends BaseViewHolder {
        @BindView(R.id.download_name)
        TextView downloadName;
        @BindView(R.id.download_progress)
        ProgressBar downloadProgress;
        @BindView(R.id.download_btn)
        Button downloadBtn;

        public RetrofitDownloadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
