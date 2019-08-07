package com.bozhen.animoapplication.main.model.repository;

import android.app.Application;

import com.bozhen.animoapplication.main.model.network.NetworkService;
import com.bozhen.animoapplication.main.model.room.AnimoDb;
import com.bozhen.animoapplication.main.model.room.Regions;
import com.bozhen.animoapplication.main.model.room.RegionsWithUsers;
import com.bozhen.animoapplication.main.model.room.Users;
import com.bozhen.animoapplication.main.model.room.UsersDao;
import com.bozhen.animoapplication.main.model.room.UsersRegionsDao;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;


public class UsersRepository {
    private UsersDao usersDao;
    private UsersRegionsDao usersRegionsDao;
    private NetworkService networkService;
    private static UsersRepository usersRepositoryInstance;
    private UsersRepository(Application application) {
        AnimoDb database = AnimoDb.getInstance(application);
        usersDao = database.usersDao();
        usersRegionsDao = database.usersRegionsDao();
        networkService = NetworkService.getInstance();
    }

    public static UsersRepository getInstance(Application application) {
        if(usersRepositoryInstance==null)
            usersRepositoryInstance = new UsersRepository(application);
        return usersRepositoryInstance;
    }


    public Maybe<List<Regions>> getUserRegions(long id){
        return usersRegionsDao.getUserRegions(id).subscribeOn(Schedulers.io());
    }


    public Maybe<Users> getUserByPhoneDb(String phone) {
        return usersDao.getUserByPhone(phone).subscribeOn(Schedulers.io());
    }

    public Observable<RegionsWithUsers> getregionsWithUsers(String phone){
        return usersDao.getUsersandRegionsInfo(phone);
    }

    public Single<Users> getUserByPhoneNw(String phone){
        return networkService.getAnimoApi().getUser(phone).subscribeOn(Schedulers.io());
    }

    public Completable insertOrUpdateUser(Users users){
        return usersDao.insert(users).subscribeOn(Schedulers.io());
    }
}