package com.yidong.jon.download;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liulishuo.filedownloader.FileDownloader;
import com.yidong.jon.R;
import com.yidong.jon.base.BaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;

public class DownloadActivity extends BaseActivity {

    @BindView(R.id.download_recycler)
    RecyclerView downloadRecycler;
    private TaskItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downloadRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskItemAdapter(this);
        downloadRecycler.setAdapter(adapter);


        TasksManager.getImpl().onCreate(new WeakReference<>(this));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_download;
    }

    public void postNotifyDataChanged() {
        if (adapter != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        TasksManager.getImpl().onDestroy();
        adapter = null;
        FileDownloader.getImpl().pauseAll();
        super.onDestroy();
    }
}
