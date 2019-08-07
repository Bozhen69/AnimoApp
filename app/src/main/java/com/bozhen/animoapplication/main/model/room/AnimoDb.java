package com.bozhen.animoapplication.main.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Users.class, Specialty.class, Posts.class, Regions.class, UsersRegions.class, Doctors.class, Pharmacy.class, Plans.class, ObjectInPlans.class, Hospitals.class, OutVisitActivity.class
,PharmacyNetwork.class,PharmacyNetworkPreparations.class, PreparationPharmacyList.class,},version = 1)
public abstract class AnimoDb extends RoomDatabase {

    private static AnimoDb instance;

    public abstract UsersDao usersDao();
    public abstract RegionsDao regionsDao();
    public abstract PharmacyNetworkDao pharmacyNetworkDao();
    public abstract PharmacyNetworkPreparationsDao pharmacyNetworkPreparationsDao();
    public abstract PreparationPharmacyListDao preparationPharmacyListDao();
    public abstract DoctorsDao doctorsDao();
    public abstract PharmacyDao pharmacyDao();
    public abstract HospitalsDao hospitalsDao();
    public abstract UsersRegionsDao usersRegionsDao();
    public abstract PlansDao plansDao();
    public abstract OutVisitActivityDao outVisitActivityDao();
    public abstract SpecialtyDao specialtyDao();
    public abstract ObjectInPlansDao objectInPlansDao();
    public abstract PostsDao postsDao();
    public static synchronized AnimoDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                     AnimoDb.class, "Animo_database")
                    .build();
        }
        return instance;
    }
}
