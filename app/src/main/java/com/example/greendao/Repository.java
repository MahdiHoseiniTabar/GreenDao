package com.example.greendao;

import com.example.greendao.model.DaoSession;
import com.example.greendao.model.NoteDao;
import com.example.greendao.model.User;
import com.example.greendao.model.UserDao;

import org.greenrobot.greendao.Property;

import java.util.List;

public class Repository {
    private static Repository repository;
    private DaoSession daoSession;
    private UserDao userDao;
    private NoteDao noteDao;

    private Repository() {
        daoSession = GreenDaoApplication.getInstance().getDaoSession();
        noteDao = daoSession.getNoteDao();
        userDao = daoSession.getUserDao();
    }

    public static Repository getInstance() {
        if (repository == null)
            repository = new Repository();
        return repository;
    }

    public void insertUser(User user){
        userDao.insert(user);
    }

    public List<User> getUserList(){
        return userDao.loadAll();
    }

    public User getUser(String userName){
        return userDao.queryBuilder()
                .where(UserDao.Properties.UserName.eq(userName))
                .unique();
    }

}
