package com.yidong.jon.download;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jon on 2016/11/29
 */

public class TasksManagerDBOpenHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "download.db";
    public final static int DATABASE_VERSION = 1;

    public TasksManagerDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + TasksManagerDBController.TABLE_NAME
                + String.format(
                "("
                        + "%s INTEGER PRIMARY KEY, " // id, download id
                        + "%s VARCHAR, " // name
                        + "%s VARCHAR, " // url
                        + "%s VARCHAR " // path
                        + ")"
                , TasksManagerModel.ID
                , TasksManagerModel.NAME
                , TasksManagerModel.URL
                , TasksManagerModel.PATH

        ));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.delete(TasksManagerDBController.TABLE_NAME, null, null);
        onCreate(db);
    }
}
