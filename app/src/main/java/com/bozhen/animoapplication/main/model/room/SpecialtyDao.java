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
public interface SpecialtyDao {

    @Delete
    Completable delete(Specialty specialty);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Specialty specialty);

    @Query("SELECT * FROM specialty")
    Flowable<List<Specialty>> getAllSpecialty();

}
