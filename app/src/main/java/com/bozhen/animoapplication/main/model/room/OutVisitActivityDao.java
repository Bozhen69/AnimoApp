package com.bozhen.animoapplication.main.model.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface OutVisitActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(OutVisitActivity outVisitActivity);

    @Query("SELECT * FROM outvisit_activity")
    Flowable<List<OutVisitActivity>> getAllActivites();
}
