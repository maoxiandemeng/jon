package com.yidong.jon.retrofitdownload.download;


import android.os.Handler;
import android.os.Looper;

public class MainThreadImpl implements IMainThread {

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    private Handler mHandler;
    private static MainThreadImpl sMMainThreadImpl = new MainThreadImpl();

    public static MainThreadImpl getMainThread() {
        return sMMainThreadImpl;
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }
}
