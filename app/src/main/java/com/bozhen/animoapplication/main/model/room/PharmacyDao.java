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
public interface PharmacyDao {
    @Delete
    void delete(Pharmacy pharmacy);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Pharmacy pharmacy);

    @Query("SELECT * FROM pharmacy")
    Flowable<List<Pharmacy>> getAllPharmacy();
}
