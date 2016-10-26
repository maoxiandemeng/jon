package com.yidong.jon.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Helpers {
    public static int dpToPx(float dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    public static int pxToDp(float dp, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp / scale + 0.5f);
    }

    public static void showToastShort(Context context, String message){
        Toast toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToastLong(Context context, String message){
        Toast toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
