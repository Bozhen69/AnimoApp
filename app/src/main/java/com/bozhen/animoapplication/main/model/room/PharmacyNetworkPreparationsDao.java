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
public interface PharmacyNetworkPreparationsDao {
    @Query("SELECT * FROM pharmacyNetworkPreparations")
    Flowable<List<PharmacyNetworkPreparations>> getAllNetowrkPreparations();

    @Delete
    Completable delete(PharmacyNetworkPreparations pharmacyNetworkPreparations);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(PharmacyNetworkPreparations pharmacyNetworkPreparations);
}
