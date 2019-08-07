package com.bozhen.animoapplication.main.model.repository;

import android.app.Application;
import android.os.Bundle;

import com.bozhen.animoapplication.main.model.room.AnimoDb;
import com.bozhen.animoapplication.main.model.room.Pharmacy;
import com.bozhen.animoapplication.main.model.room.PharmacyDao;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class PharmacyRepository {
    private static PharmacyRepository pharmacyRepository;
    private PharmacyDao pharmacyDao;

    public PharmacyRepository(Application application) {
        AnimoDb animoDb =  AnimoDb.getInstance(application);
        pharmacyDao = animoDb.pharmacyDao();
    }

    public static PharmacyRepository newInstance(Application application) {
        if(pharmacyRepository==null)
            pharmacyRepository = new PharmacyRepository(application);
        return pharmacyRepository;
    }

    public Flowable<List<Pharmacy>> getAllPharmacy(){
        return pharmacyDao.getAllPharmacy().subscribeOn(Schedulers.io());
    }
}
