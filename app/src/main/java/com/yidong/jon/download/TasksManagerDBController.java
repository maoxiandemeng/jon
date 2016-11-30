package com.yidong.jon.download;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.yidong.jon.MyApplication;
import com.yidong.jon.R;

import java.util.ArrayList;

/**
 * Created by jon on 2016/11/29
 */

public class TasksManagerDBController {
    public final static String TABLE_NAME = "download";
    private final SQLiteDatabase db;

    public TasksManagerDBController() {
        TasksManagerDBOpenHelper openHelper = new TasksManagerDBOpenHelper(MyApplication.context);

        db = openHelper.getWritableDatabase();
    }

    public ArrayList<TasksManagerModel> getAllTasks() {
        final Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        final ArrayList<TasksManagerModel> list = new ArrayList<>();
        try {
            if (!c.moveToLast()) {
                return list;
            }

            do {
                TasksManagerModel model = new TasksManagerModel();
                model.setId(c.getInt(c.getColumnIndex(TasksManagerModel.ID)));
                model.setName(c.getString(c.getColumnIndex(TasksManagerModel.NAME)));
                model.setUrl(c.getString(c.getColumnIndex(TasksManagerModel.URL)));
                model.setPath(c.getString(c.getColumnIndex(TasksManagerModel.PATH)));
                list.add(model);
            } while (c.moveToPrevious());
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return list;
    }

    public TasksManagerModel addTask(final String url, final String path) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(path)) {
            return null;
        }

        // have to use FileDownloadUtils.generateId to associate TasksManagerModel with FileDownloader
        final int id = FileDownloadUtils.generateId(url, path);

        TasksManagerModel model = new TasksManagerModel();
        model.setId(id);
        model.setName(MyApplication.context.getString(R.string.tasks_manager_demo_name, id));
        model.setUrl(url);
        model.setPath(path);

        final boolean succeed = db.insert(TABLE_NAME, null, model.toContentValues()) != -1;
        return succeed ? model : null;
    }
}
