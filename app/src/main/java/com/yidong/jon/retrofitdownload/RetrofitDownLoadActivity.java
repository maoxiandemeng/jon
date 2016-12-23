package com.yidong.jon.retrofitdownload;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.retrofitdownload.download.db.DownLoadEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;

public class RetrofitDownloadActivity extends BaseActivity {
    @BindView(R.id.download_recycler)
    RecyclerView downloadRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downloadRecycler.setLayoutManager(new LinearLayoutManager(this));
        String s = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "retrofitDownload" + File.separator;
        File file = new File(s);
        if (!file.isDirectory()) {
            file.mkdir();
        }
        ArrayList<DownLoadEntity> loadTests = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            DownLoadEntity test = new DownLoadEntity();
            test.saveName = s + names[i] + ".apk";
            test.name = names[i];
            test.url = URLS[i];
            loadTests.add(test);
        }

        RetrofitDownloadAdapter downloadAdapter = new RetrofitDownloadAdapter(this, loadTests);
        downloadRecycler.setAdapter(downloadAdapter);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_retrofit_down_load;
    }

    public static final String[] names = {
            "网易云音乐",
            "优酷",
            "腾讯视频",
            "UC浏览器",
            "360手机卫士",
            "前程无忧51job",
            "搜狐视频",
            "微信电话本",
            "淘宝",
            "聚美优品",
            "搜房网"
    };

    public static final String[] URLS = {
            "http://s1.music.126.net/download/android/CloudMusic_2.8.1_official_4.apk",
            "http://dl.m.cc.youku.com/android/phone/Youku_Phone_youkuweb.apk",
            "http://dldir1.qq.com/qqmi/TencentVideo_V4.1.0.8897_51.apk",
            "http://wap3.ucweb.com/files/UCBrowser/zh-cn/999/UCBrowser_V10.6.0.620_android_pf145_(Build150721222435).apk",
            "http://msoftdl.360.cn/mobilesafe/shouji360/360safesis/360MobileSafe_6.2.3.1060.apk",
            "http://www.51job.com/client/51job_51JOB_1_AND2.9.3.apk",
            "http://upgrade.m.tv.sohu.com/channels/hdv/5.0.0/SohuTV_5.0.0_47_201506112011.apk",
            "http://dldir1.qq.com/qqcontacts/100001_phonebook_4.0.0_3148.apk",
            "http://download.alicdn.com/wireless/taobao4android/latest/702757.apk",
            "http://apps.wandoujia.com/apps/com.jm.android.jumei/download",
            "http://download.3g.fang.com/soufun_android_30001_7.9.0.apk"
    };
}
