package com.jon.lib.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by jon on 2016/12/2
 */

public class ProviderUtil {

    public static String getProviderNameId(Context context){
        String nameId = null;
        ContentResolver resolver = context.getContentResolver();
        Uri uri = Uri.parse("content://com.yidong.jon.provider.MyContentProvider/my");
        Cursor cursor = resolver.query(uri, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                nameId = cursor.getString(cursor.getColumnIndex("name_id"));
                break;
            }
            cursor.close();
        }
        return nameId;
    }
}
