package com.bozhen.animoapplication.main.model.room;



import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;


@Dao
public interface RegionsDao {

    @Delete
    void delete(Regions regions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Regions regions);

    @Query("SELECT * FROM regions WHERE id = :id")
    Regions getRegionById(long id);

    @Query("SELECT * FROM regions")
    List<Regions> getAllRegions();
}
