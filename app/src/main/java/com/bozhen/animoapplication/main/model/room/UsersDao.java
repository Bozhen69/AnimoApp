package com.bozhen.animoapplication.main.model.room;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
public interface UsersDao{

    @Delete
    Completable delete(Users users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Users users);

    @Query("SELECT * FROM users")
    Flowable<List<Users>> getAllUsers();

    @Query("SELECT * FROM users WHERE id = :id")
    Maybe<Users> getUserById(long id);

    @Query("SELECT * FROM users WHERE phone = :phone")
    Maybe<Users> getUserByPhone(String phone);

    @Query("SELECT users.first_name, users.surname, users.patronymic, users.email, users.date, regions.id, regions.name "
    +"FROM users "+
    "LEFT JOIN users_regions ON users_regions.user_id = users.id " +
            "LEFT JOIN regions ON users_regions.region_id = regions.id "+
    "WHERE users.phone = :phone "+
    "GROUP BY users.id")
    Observable<RegionsWithUsers> getUsersandRegionsInfo(String phone);

}
