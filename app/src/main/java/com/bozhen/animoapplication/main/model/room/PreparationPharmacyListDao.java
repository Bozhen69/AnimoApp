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
public interface PreparationPharmacyListDao {

    @Delete
    Completable delete(Doctors doctors);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(PreparationPharmacyList preparationPharmacies);

    @Query("SELECT * FROM preparationPharmacyList")
    Flowable<List<PreparationPharmacyList>> getAllPreparationPharmacies();


}
