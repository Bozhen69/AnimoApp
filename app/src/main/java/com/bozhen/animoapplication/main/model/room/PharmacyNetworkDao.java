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
public interface PharmacyNetworkDao {
    @Query("SELECT * FROM pharmacyNetwork")
    Flowable<List<PharmacyNetwork>> getAllNetowrks();

    @Delete
    Completable delete(PharmacyNetwork pharmacyNetwork);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(PharmacyNetwork pharmacyNetwork);
}
