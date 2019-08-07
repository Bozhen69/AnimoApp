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


@Dao
public interface ObjectInPlansDao {

    @Delete
    Completable delete(ObjectInPlans objectInPlans);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(ObjectInPlans objectInPlans);

    @Query("SELECT * FROM object_in_plans WHERE id_plan = :plan_id AND type_object='doctor'")
    Flowable<List<ObjectInPlans>> getAllDoctorsinPlan(long plan_id);

    @Query("SELECT * FROM object_in_plans WHERE id_plan = :plan_id AND type_object='pharmacy'")
    List<ObjectInPlans> getAllPharmacyinPlan(long plan_id);

    @Query("SELECT * FROM doctors WHERE id = :id")
    Doctors getDoctorById(long id);

    @Query("SELECT * FROM pharmacy WHERE id = :id")
    Pharmacy getPharmacyById(long id);

    @Query("SELECT * FROM object_in_plans WHERE id_object_in_plans=:id")
    Maybe<ObjectInPlans> getObjectPlanById(long id);

    @Query("SELECT *" +
            " FROM object_in_plans LEFT JOIN doctors ON doctors.id = object_in_plans.id_object WHERE id_plan = :plan_id AND type_object='doctor'")
    Flowable<List<ObjectInPlansDoctors>> getDoctorsinPlanAll(long plan_id);

    @Query("DELETE FROM object_in_plans WHERE id_object_in_plans=:id" )
    Completable delete(long id);


}
