package com.bozhen.animoapplication.main.model.repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.bozhen.animoapplication.main.model.room.AnimoDb;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.DoctorsDao;
import com.bozhen.animoapplication.main.model.room.Hospitals;
import com.bozhen.animoapplication.main.model.room.HospitalsDao;
import com.bozhen.animoapplication.main.model.room.ObjectInPlans;
import com.bozhen.animoapplication.main.model.room.ObjectInPlansDao;
import com.bozhen.animoapplication.main.model.room.Plans;
import com.bozhen.animoapplication.main.model.room.PlansDao;
import com.bozhen.animoapplication.main.model.room.Posts;
import com.bozhen.animoapplication.main.model.room.PostsDao;
import com.bozhen.animoapplication.main.model.room.Specialty;
import com.bozhen.animoapplication.main.model.room.SpecialtyDao;
import com.bozhen.animoapplication.main.model.room.Users;
import com.bozhen.animoapplication.main.model.room.UsersDao;
import com.bozhen.animoapplication.main.utils.Settings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class DoctorsRepository {
    private static DoctorsRepository doctorsRepository;

    DoctorsDao doctorsDao;
    HospitalsDao hospitalsDao;
    SpecialtyDao specialtyDao;
    PostsDao postsDao;
    PlansDao plansDao;
    ObjectInPlansDao objectInPlansDao;


    public DoctorsRepository(Application application) {
        AnimoDb database = AnimoDb.getInstance(application);
        doctorsDao = database.doctorsDao();
        hospitalsDao = database.hospitalsDao();
        specialtyDao = database.specialtyDao();
        postsDao = database.postsDao();
        plansDao = database.plansDao();
        objectInPlansDao = database.objectInPlansDao();
    }

    public String addDoctorInPlan(long plan_id,long doctor_id){
        if(plan_id!=-1){
            ObjectInPlans objectInPlans = new ObjectInPlans(0,doctor_id,"doctor",plan_id,"","","","");
            objectInPlansDao.insert(objectInPlans).subscribeOn(Schedulers.io()).subscribe();
            return "Доктор успешно добавлен в ваш план";
        }
        else
            return "При добавлении доктора произошла ошибка";
    }

    public static DoctorsRepository newInstance(Application application) {
        if(doctorsRepository==null)
            doctorsRepository = new DoctorsRepository(application);
        return doctorsRepository;
    }

    public Doctors getDoctorById(long id){
        return doctorsDao.getDoctorInfoById(id);
    }

    public Flowable<List<Doctors>> getAllDoctors(){
        return doctorsDao.getAllDoctors().subscribeOn(Schedulers.io());
    }

    public Flowable<List<Hospitals>> getAllHospitals(){
        return hospitalsDao.getAllHospitals().subscribeOn(Schedulers.io());
    }

    public Flowable<List<Specialty>> getAllSpecialty(){
        return specialtyDao.getAllSpecialty().subscribeOn(Schedulers.io());
    }

    public Flowable<List<Posts>> getAllPosts(){
        return postsDao.getAllDoctorsPosts().subscribeOn(Schedulers.io());
    }

    public Flowable<Doctors> getDoctor(long id){
        return doctorsDao.getDoctorById(id).subscribeOn(Schedulers.io());
    }

    public Single<Long> insertOrUpdate(Doctors doctors){
        Log.i("InsetorUpdate Doctors","update");
        return doctorsDao.insert(doctors).subscribeOn(Schedulers.io());
    }
}
