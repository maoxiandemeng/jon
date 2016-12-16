package com.yidong.jon.ui.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.yidong.jon.badger.ShortcutBadger;
import com.yidong.jon.base.BaseActivity;
import com.yidong.jon.utils.Helpers;
import com.yidong.jon.R;
import com.yidong.jon.widget.CountDownView;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/6/16.
 */
public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.countDownView)
    CountDownView countDownView;

    private Handler handler = new Handler();
    private DownTimeRunnable downTimeRunnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        Log.i("display", "xdpi: " + xdpi + "ydpi: " + ydpi);
        //320.815   318.456

        downTimeRunnable = new DownTimeRunnable();
        handler.postDelayed(downTimeRunnable, 5000);

        countDownView.setCountDownTimerListener(new CountDownView.CountDownTimerListener() {
            @Override
            public void onStartCount() {
                Helpers.showToastShort(WelcomeActivity.this, "计时开始");
            }

            @Override
            public void onFinishCount() {
                Helpers.showToastShort(WelcomeActivity.this, "计时结束");
            }
        });
        countDownView.start(5000);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        boolean applyCount = ShortcutBadger.applyCount(this, 5);
//        Helpers.showToastShort(WelcomeActivity.this, ""+applyCount);
//        sendBadgeNumber();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_welcome;
    }

    @OnClick(R.id.countDownView)
    public void onDownClick() {
        handler.removeCallbacks(downTimeRunnable);
        startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
        finish();
    }

    public class DownTimeRunnable implements Runnable {

        @Override
        public void run() {
            startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
            finish();
        }
    }

    private void sendBadgeNumber() {
        String number = "35";
        if (TextUtils.isEmpty(number)) {
            number = "0";
        } else {
            int numInt = Integer.valueOf(number);
            number = String.valueOf(Math.max(0, Math.min(numInt, 99)));
        }

        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            sendToXiaoMi(Integer.valueOf(number));
        } else if (Build.MANUFACTURER.equalsIgnoreCase("samsung")) {
            sendToSony(number);
        } else if (Build.MANUFACTURER.toLowerCase().contains("sony")) {
            sendToSamsumg(number);
        } else {
            Toast.makeText(this, "Not Support", Toast.LENGTH_LONG).show();
            Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            intent.putExtra("badge_count", Integer.valueOf(number));
            intent.putExtra("badge_count_package_name", getPackageName());
            intent.putExtra("badge_count_class_name", WelcomeActivity.class);
            sendBroadcast(intent);
        }
    }

    private void sendToXiaoMi(int number) {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = null;
        boolean isMiUIV6 = true;
        try {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("您有" + number + "未读消息");
            builder.setTicker("您有" + number + "未读消息");
            builder.setAutoCancel(true);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setDefaults(Notification.DEFAULT_LIGHTS);
            notification = builder.build();
            Class miuiNotificationClass = Class.forName("android.app.MiuiNotification");
            Object miuiNotification = miuiNotificationClass.newInstance();
            Field field = miuiNotification.getClass().getDeclaredField("messageCount");
            field.setAccessible(true);
            field.set(miuiNotification, number);// 设置信息数
            field = notification.getClass().getField("extraNotification");
            field.setAccessible(true);
            field.set(notification, miuiNotification);
            Toast.makeText(this, "Xiaomi=>isSendOk=>1", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            //miui 6之前的版本
            isMiUIV6 = false;
            Intent localIntent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
            localIntent.putExtra("android.intent.extra.update_application_component_name", getPackageName() + "/" + WelcomeActivity.class);
            localIntent.putExtra("android.intent.extra.update_application_message_text", number);
            sendBroadcast(localIntent);
        } finally {
            if (notification != null && isMiUIV6) {
                //miui6以上版本需要使用通知发送
                nm.notify(101010, notification);
            }
        }

    }

    private void sendToSony(String number) {
        boolean isShow = true;
        if ("0".equals(number)) {
            isShow = false;
        }
        Intent localIntent = new Intent();
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", isShow);//是否显示
        localIntent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", WelcomeActivity.class);//启动页
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", number);//数字
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", getPackageName());//包名
        sendBroadcast(localIntent);

        Toast.makeText(this, "Sony," + "isSendOk", Toast.LENGTH_LONG).show();
    }

    private void sendToSamsumg(String number) {
        Intent localIntent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        localIntent.putExtra("badge_count", number);//数字
        localIntent.putExtra("badge_count_package_name", getPackageName());//包名
        localIntent.putExtra("badge_count_class_name", WelcomeActivity.class); //启动页
        sendBroadcast(localIntent);
        Toast.makeText(this, "Samsumg," + "isSendOk", Toast.LENGTH_LONG).show();
    }
}
