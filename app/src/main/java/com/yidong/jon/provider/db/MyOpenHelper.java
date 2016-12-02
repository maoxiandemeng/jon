package com.yidong.jon.provider.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jon on 2016/12/2
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "my.db";
    public static final String TABLE_NAME = "my";
    private static final int VERSION = 1;
    public static final String NAME_ID = "name_id";
    private volatile static MyOpenHelper instance;

    public MyOpenHelper(Context context) {
        super(context.getApplicationContext(), DB_NAME, null, VERSION);
    }

    public static MyOpenHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (MyOpenHelper.class) {
                if (instance == null) {
                    synchronized (MyOpenHelper.class) {
                        instance = new MyOpenHelper(context);
                    }
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + " ("  +
                "_id integer primary key autoincrement, " +
                NAME_ID + " text" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
