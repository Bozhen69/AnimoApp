package com.bozhen.animoapplication.main.model.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface PostsDao {
    @Query("SELECT * FROM posts " +
            "WHERE who = 'doctor'")
    Flowable<List<Posts>> getAllDoctorsPosts();

    @Delete
    Completable delete(Posts posts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Posts posts);
}
