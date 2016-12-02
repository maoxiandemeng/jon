package com.yidong.jon.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yidong.jon.provider.db.MyOpenHelper;

/**
 * Created by jon on 2016/12/2
 */

public class MyContentProvider extends ContentProvider {
    private static final String TAG = MyContentProvider.class.getSimpleName();
    private static final String AUTHORITY = "com.yidong.jon.provider.MyContentProvider";
    public static Uri URI_DATA_ALL = Uri.parse("content://" + AUTHORITY + "/my");

    private static UriMatcher matcher;
    private static final int DATA_ALL = 0;
    private static final int DATA_ONE = 1;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, "my", DATA_ALL);
        matcher.addURI(AUTHORITY, "my/#", DATA_ONE);
    }

    private MyOpenHelper mHelper;
    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {
        mHelper = MyOpenHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int match = matcher.match(uri);
        Log.i(TAG,"query match: "+match);
        switch (match) {
            case DATA_ALL:
                break;
            case DATA_ONE:
                long parseId = ContentUris.parseId(uri);
                selection = "_id = ?";
                selectionArgs = new String[]{String.valueOf(parseId)};
                break;
            default: throw new IllegalArgumentException("error uri " + uri);
        }
        mDb = mHelper.getReadableDatabase();
        Cursor cursor = mDb.query(MyOpenHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        cursor.setNotificationUri(getContext().getContentResolver(), URI_DATA_ALL);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = matcher.match(uri);
        Log.i(TAG,"insert match: "+match);
        if (match != DATA_ALL && match != DATA_ONE) {
            throw new IllegalArgumentException("error uri " + uri);
        }
        mDb = mHelper.getWritableDatabase();
        long rowId = mDb.insert(MyOpenHelper.TABLE_NAME, null, values);
        if (rowId > 0) {
            notifyDataSetChanged();
            return ContentUris.withAppendedId(uri, rowId);
        }
        return null;
    }

    private void notifyDataSetChanged() {
        getContext().getContentResolver().notifyChange(URI_DATA_ALL, null);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
