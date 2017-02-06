package com.yidong.jon.retrofit.download;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yidong.jon.MyApplication;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by jon on 2016/12/26
 */

public class DbDownUtil {
    private static final String DB_NAME = "down.db";
    private static volatile DbDownUtil instance;
    private Context context;
    private DaoMaster.DevOpenHelper openHelper;

    public DbDownUtil() {
        context = MyApplication.context;
        openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
    }

    public static DbDownUtil getInstance() {
        if (instance == null) {
            synchronized (DbDownUtil.class) {
                if (instance == null) {
                    instance = new DbDownUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 得到可读的数据库
     * @return
     */
    private SQLiteDatabase getReadableDatabase(){
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        }
        return openHelper.getReadableDatabase();
    }

    /**
     * 得到可写的数据库
     * @return
     */
    private SQLiteDatabase getWriteableDatabase(){
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        }
        return openHelper.getWritableDatabase();
    }

    /**
     * 保存到数据库
     * @param info
     */
    public void save(DownInfo info){
        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DownInfoDao infoDao = daoSession.getDownInfoDao();
        infoDao.insert(info);
    }

    /**
     * 更新到数据库
     * @param info
     */
    public void update(DownInfo info){
        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DownInfoDao infoDao = daoSession.getDownInfoDao();
        infoDao.update(info);
    }

    /**
     * 删除数据库的一条info
     * @param info
     */
    public void delete(DownInfo info){
        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DownInfoDao infoDao = daoSession.getDownInfoDao();
        infoDao.delete(info);
    }

    /**
     * 根据传入的id获得DownInfo
     * @param id
     * @return
     */
    public DownInfo queryDownById(long id){
        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DownInfoDao infoDao = daoSession.getDownInfoDao();
        QueryBuilder<DownInfo> queryBuilder = infoDao.queryBuilder();
        queryBuilder.where(DownInfoDao.Properties.Id.eq(id));
        List<DownInfo> list = queryBuilder.list();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    /**
     * 获取所有的DownInfo
     * @return
     */
    public List<DownInfo> queryAll(){
        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DownInfoDao infoDao = daoSession.getDownInfoDao();
        QueryBuilder<DownInfo> queryBuilder = infoDao.queryBuilder();
        return queryBuilder.list();
    }
}
