package com.yidong.jon;

import android.app.Application;
import android.os.StrictMode;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;

/**
 * Created by Administrator on 2016/10/10.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (!LeakCanary.isInAnalyzerProcess(this)){
            enabledStrictMode();
            RefWatcher refWatcher = LeakCanary.install(this);
        }
    }
    private void enabledStrictMode() {
        if (SDK_INT >= GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                    .detectAll() //
                    .penaltyLog() //
                    .penaltyDeath() //
                    .build());
        }
    }

}
