package com.bozhen.animoapplication.main.model.room;


import java.util.List;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface DoctorsDao {
    @Delete
    Completable delete(Doctors doctors);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insert(Doctors doctors);

    @Query("SELECT * FROM doctors")
    Flowable<List<Doctors>> getAllDoctors();

    @Query("SELECT * FROM doctors WHERE id = :id")
    Flowable<Doctors> getDoctorById(long id);

    @Query("SELECT * FROM doctors WHERE id = :id")
    Doctors getDoctorInfoById(long id);
}
