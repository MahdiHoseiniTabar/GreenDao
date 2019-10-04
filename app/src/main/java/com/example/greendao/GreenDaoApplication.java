package com.example.greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.greendao.model.DaoMaster;
import com.example.greendao.model.DaoSession;

public class GreenDaoApplication extends Application {
    private static GreenDaoApplication application;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        GreenDaoOpenHelper daoOpenHelper = new GreenDaoOpenHelper(this, "greenDao.db");
        SQLiteDatabase database = daoOpenHelper.getWritableDatabase();
        daoSession = new DaoMaster(database).newSession();

        application = this;
    }

    public static GreenDaoApplication getInstance() {
        return application;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
