package com.yidong.jon.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by jon on 2016/12/8
 */

public class NetworkReceiver extends BroadcastReceiver {
    public static final int NET_NO = 0;
    public static final int NET_WIFI = 1;
    public static final int NET_MOBILE = 2;
    private int net_state = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            // 获取网络连接管理的对象
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 判断当前网络是否已经连接
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    String type = info.getTypeName();
                    if (type.equalsIgnoreCase("WIFI")) {
                        if (net_state != NET_WIFI) {
                            Toast.makeText(context, "已切换WIFI连接", Toast.LENGTH_SHORT).show();
                        }
                        net_state = NET_WIFI;
                    } else if (type.equalsIgnoreCase("MOBILE")) {
                        if (net_state != NET_MOBILE) {
                            Toast.makeText(context, "已切换2G/3G/4G,请注意流量使用", Toast.LENGTH_SHORT).show();
                        }
                        net_state = NET_MOBILE;
                    }
                }
            } else {
                if (net_state != NET_NO) {
                    setNetworkMethod(context);
                }
                net_state = 0;
            }
        }
    }

    public static void setNetworkMethod(final Context context) {
        // 提示对话框 这里需要加权限<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
        //还需要手机打开悬浮窗权限
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        AlertDialog dialog = builder.setTitle("网络设置提示")
                .setMessage("网络连接不可用,是否进行设置?")
                .setPositiveButton("设置",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Intent intent = new Intent(
                                        Settings.ACTION_WIRELESS_SETTINGS);
                                context.startActivity(intent);
                            }
                        })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        }).create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
    }
}
