package com.bozhen.animoapplication.main.model.room;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Maybe;


@Dao
public interface UsersRegionsDao {

    @Delete
    void delete(UsersRegions usersRegions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(UsersRegions usersRegions);


    @Query("SELECT * FROM regions INNER JOIN users_regions ON " +
            "region_id = id" +
            " WHERE user_id = :id ")
    Maybe<List<Regions>> getUserRegions(long id);
}
