package com.bozhen.animoapplication.main.model.network;

import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.Hospitals;
import com.bozhen.animoapplication.main.model.room.ObjectInPlans;
import com.bozhen.animoapplication.main.model.room.OutVisitActivity;
import com.bozhen.animoapplication.main.model.room.Pharmacy;
import com.bozhen.animoapplication.main.model.room.PharmacyNetwork;
import com.bozhen.animoapplication.main.model.room.PharmacyNetworkPreparations;
import com.bozhen.animoapplication.main.model.room.Plans;
import com.bozhen.animoapplication.main.model.room.Posts;
import com.bozhen.animoapplication.main.model.room.PreparationPharmacyList;
import com.bozhen.animoapplication.main.model.room.Regions;
import com.bozhen.animoapplication.main.model.room.Specialty;
import com.bozhen.animoapplication.main.model.room.Users;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AnimoApi {
    @GET("/api/getUserInfo/{phone}")
    public Single<Users> getUser(@Path("phone") String phone);

    @GET("/api/getUserRegion/{id}")
    public Single<List<Regions>> getRegions(@Path("id") long id);

    @GET("/api/getDoctorsByRegionId/{id}")
    public Single<List<Doctors>> getDoctorsByRegion(@Path("id") long id);

    @GET("/api/getPharmacyByRegionId/{id}")
    public Single<List<Pharmacy>> getPharmacyByRegion(@Path("id") long id);

    @GET ("/api/getAllHospitals")
    public Single<List<Hospitals>> getAllHospitals();

    @GET ("/api/getAllSpecialty")
    public Single<List<Specialty>> getallSpecialty();

    @GET ("/api/getAllPosts")
    public Single<List<Posts>> getallPosts();

    @GET ("/api/getPlansByUserId/{id}")
    public Single<List<Plans>> getaUserPlans(@Path("id") long id);

    @GET ("/api/getPlanObject/{id}")
    public Single<List<ObjectInPlans>> getObjectPlans(@Path("id") long id);

    @GET("/api/getAllActivites")
    public Single<List<OutVisitActivity>> getallOutvisitActivities();

    @GET("/api/getPharmacyNetwork")
    public Single<List<PharmacyNetwork>> getallPharmacyNetworks();

    @GET("/api/getallPreparation")
    public Single<List<PreparationPharmacyList>> getallPreparationsPharmacy();

    @GET("/api/getPharmacyNetworkPreparations")
    public Single<List<PharmacyNetworkPreparations>> getaNetworkPreparationsForPharmacies();

}
