package com.yidong.jon.ui.activity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.utils.Helpers;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.leolin.shortcutbadger.ShortcutBadger;

public class BadgerActivity extends BaseActivity {

    @BindView(R.id.add_num)
    EditText addNum;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.remove_num)
    EditText removeNum;
    @BindView(R.id.remove)
    Button remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        String code = getChannelCode();
        removeNum.setText(code);
        Helpers.showToastShort(this, code);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_badger;
    }

    @OnClick({R.id.add, R.id.remove})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                String addCount = addNum.getText().toString();
                if (TextUtils.isEmpty(addCount)) {
                    Helpers.showToastShort(this, "个数不能为空！");
                    return;
                }
                boolean applyCount = ShortcutBadger.applyCount(this, Integer.parseInt(addCount));
                Helpers.showToastShort(this, ""+applyCount);
                break;
            case R.id.remove:
                String removeCount = remove.getText().toString();
                if (TextUtils.isEmpty(removeCount)) {
                    Helpers.showToastShort(this, "个数不能为空！");
                    return;
                }
                boolean count = ShortcutBadger.removeCount(this);
                Helpers.showToastShort(this, ""+count);
                break;
        }
    }

    /**
     * 得到渠道的表示符
     * @return
     */
    private String getChannelCode(){
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Object channel = info.metaData.get("UMENG_CHANNEL");
            if (channel != null) {
                return channel.toString();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getChannel(Context context) {
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        String ret = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.startsWith("mtchannel")) {
                    ret = entryName;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String[] split = ret.split("_");
        if (split != null && split.length >= 2) {
            return ret.substring(split[0].length() + 1);

        } else {
            return "";
        }
    }
}
