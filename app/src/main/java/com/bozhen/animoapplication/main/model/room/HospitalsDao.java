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
public interface HospitalsDao {
    @Query("SELECT * FROM hospitals")
    Flowable<List<Hospitals>> getAllHospitals();

    @Delete
    Completable delete(Hospitals hospitals);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Hospitals hospitals);
}
