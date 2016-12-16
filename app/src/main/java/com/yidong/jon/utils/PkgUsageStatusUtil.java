package com.yidong.jon.utils;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jon on 2016/12/5
 */

public class PkgUsageStatusUtil {
    public static final String TAG = PkgUsageStatusUtil.class.getSimpleName();
    public static void getPkgUsageStatus(){
        try {
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            Method serviceMethod = serviceManager.getMethod("getService", java.lang.String.class);
            Object serviceObject = serviceMethod.invoke(null, "usagestats");

            // IUsageStats oIUsageStats =
            // IUsageStats.Stub.asInterface(oRemoteService)
            Class<?> stub = Class
                    .forName("com.android.internal.app.IUsageStats$Stub");
            Method mUsageStatsService = stub.getMethod("asInterface",
                    android.os.IBinder.class);
            Object oIUsageStats = mUsageStatsService.invoke(null,
                    serviceObject);

            // PkgUsageStats[] oPkgUsageStatsArray =
            // mUsageStatsService.getAllPkgUsageStats();
            Class<?> cIUsageStatus = Class
                    .forName("com.android.internal.app.IUsageStats");
            Method mGetAllPkgUsageStats = cIUsageStatus.getMethod(
                    "getAllPkgUsageStats", (Class[]) null);
            Object[] oPkgUsageStatsArray = (Object[]) mGetAllPkgUsageStats
                    .invoke(oIUsageStats, (Object[]) null);
            Log.d(TAG, "[getPkgUsageStats] oPkgUsageStatsArray = "+oPkgUsageStatsArray);

            Class<?> cPkgUsageStats = Class
                    .forName("com.android.internal.os.PkgUsageStats");
            StringBuffer sb = new StringBuffer();
            sb.append("nerver used : ");
            for (Object pkgUsageStats : oPkgUsageStatsArray) {
                // get pkgUsageStats.packageName, pkgUsageStats.launchCount,
                // pkgUsageStats.usageTime
                String packageName = (String) cPkgUsageStats.getDeclaredField(
                        "packageName").get(pkgUsageStats);
                int launchCount = cPkgUsageStats
                        .getDeclaredField("launchCount").getInt(pkgUsageStats);
                long usageTime = cPkgUsageStats.getDeclaredField("usageTime")
                        .getLong(pkgUsageStats);
                if (launchCount > 0)
                    Log.d(TAG, "[getPkgUsageStats] "+ packageName + "  count: "
                            + launchCount + "  time:  " + usageTime);
                else {
                    sb.append(packageName + "; ");
                }
            }
            Log.d(TAG, "[getPkgUsageStats] " + sb.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
