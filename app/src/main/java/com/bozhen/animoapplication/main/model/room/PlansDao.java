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
import io.reactivex.Single;


@Dao
public interface PlansDao {
    @Delete
    void delete(Plans plans);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insert(Plans plans);

    @Query("SELECT * FROM plans WHERE id_user = :id AND date = :date")
    Maybe<Plans> getAllUserPlans(long id,String date);

    @Query("SELECT * FROM plans WHERE id=:id")
    Maybe<Plans> getPlanById(long id);

}
