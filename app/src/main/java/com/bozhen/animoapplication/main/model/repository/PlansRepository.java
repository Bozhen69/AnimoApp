package com.bozhen.animoapplication.main.model.repository;

import android.app.Application;
import android.util.Log;

import com.bozhen.animoapplication.main.model.room.AnimoDb;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.ObjectInPlans;
import com.bozhen.animoapplication.main.model.room.ObjectInPlansDao;
import com.bozhen.animoapplication.main.model.room.ObjectInPlansDoctors;
import com.bozhen.animoapplication.main.model.room.OutVisitActivity;
import com.bozhen.animoapplication.main.model.room.OutVisitActivityDao;
import com.bozhen.animoapplication.main.model.room.Pharmacy;
import com.bozhen.animoapplication.main.model.room.Plans;
import com.bozhen.animoapplication.main.model.room.PlansDao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class PlansRepository {
    private static PlansRepository plansRepository;
    private PlansDao plansDao;
    private ObjectInPlansDao objectInPlansDao;
    private OutVisitActivityDao outVisitActivityDao;
    public PlansRepository(Application application) {
        AnimoDb animoDb = AnimoDb.getInstance(application);
        plansDao = animoDb.plansDao();
        objectInPlansDao = animoDb.objectInPlansDao();
        outVisitActivityDao = animoDb.outVisitActivityDao();
    }

    public static PlansRepository getInstance(Application application) {
        if(plansRepository==null)
            plansRepository = new PlansRepository(application);
        return plansRepository;
    }


    public Maybe<Plans> getallUserPlans(long id, String date){
        return plansDao.getAllUserPlans(id,date);
    }

    public Single<Long> insertOrUpdatePlan(Plans plans){
       return plansDao.insert(plans);
    }

    public Flowable<List<ObjectInPlans>> getObjectDoctors(long id){
        return objectInPlansDao.getAllDoctorsinPlan(id);
    }

    public Flowable<List<ObjectInPlansDoctors>> getDoctorsAll(long id_plan){
        return objectInPlansDao.getDoctorsinPlanAll(id_plan);
    }

    public Flowable<List<Doctors>> getallDoctorsinPlan(final long id_plan) {
      Flowable<List<Doctors>> flowable = Flowable.create(
               new FlowableOnSubscribe<List<Doctors>>(){
                   @Override
                   public void subscribe(final FlowableEmitter<List<Doctors>> emitter) throws Exception {
                       Log.i("Start_Doctors_flow"," was start");
                      final List<ObjectInPlans> objectInPlans;
                      objectInPlansDao.getAllDoctorsinPlan(id_plan)
                              .subscribe(
                                      new DisposableSubscriber<List<ObjectInPlans>>() {
                                          @Override
                                          public void onNext(List<ObjectInPlans> objectInPlans) {
                                              List<Doctors> doctors = new ArrayList<>();
                                              for(ObjectInPlans objinpl:objectInPlans){
                                                  Doctors doc = objectInPlansDao.getDoctorById(objinpl.getId_object());
                                                  if(doc!=null)
                                                      doctors.add(doc);
                                              }
                                              emitter.onNext(doctors);
                                          }

                                          @Override
                                          public void onError(Throwable t) {

                                          }

                                          @Override
                                          public void onComplete() {
                                              emitter.onComplete();
                                          }
                                      }
                              )
                      ;
                   }
               }, BackpressureStrategy.BUFFER
       );
      return flowable;
    }
    public void deleteObjectInPlan(long id){
        objectInPlansDao.delete(id).subscribeOn(Schedulers.io()).subscribe();
    }

    public void NewWorkState(long id, final String location){
        objectInPlansDao.getObjectPlanById(id)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new DisposableMaybeObserver<ObjectInPlans>() {
                            @Override
                            public void onSuccess(ObjectInPlans objectInPlans) {
                                Log.i("NewWorkState","on next");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                if(objectInPlans.getPlan_time_start()==null || objectInPlans.getPlan_time_start().equals("")){
                                    Log.i("NewWorkState","havent start");
                                    objectInPlans.setPlan_time_start(dateFormat.format(Calendar.getInstance().getTime()));
                                    objectInPlans.setPlan_location_start(location);
                                }
                                else if(objectInPlans.getPlan_time_end()==null || objectInPlans.getPlan_time_end().equals("")){
                                    Log.i("NewWorkState","havent end");
                                    objectInPlans.setPlan_time_end(dateFormat.format(Calendar.getInstance().getTime()));
                                    objectInPlans.setPlan_location_end(location);
                                }
                                Log.i("NewWorkState",objectInPlans.getPlan_time_start());
                                objectInPlansDao.insert(objectInPlans).subscribe();
                                dispose();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Log.i("NewWorkState","on complete");
                            }
                        }
                );
    }

    public Flowable<List<Pharmacy>> getallPharmacyinPlan(final long id_plan){
        Flowable<List<Pharmacy>> flowable = Flowable.create(
                new FlowableOnSubscribe<List<Pharmacy>>(){
                    @Override
                    public void subscribe(FlowableEmitter<List<Pharmacy>> emitter) throws Exception {
                        List<Pharmacy> pharmacies = new ArrayList<>();
                        List<ObjectInPlans> objectInPlans = objectInPlansDao.getAllPharmacyinPlan(id_plan);
                        for(ObjectInPlans objinpl:objectInPlans){
                            Pharmacy pharm = objectInPlansDao.getPharmacyById(objinpl.getId_object());
                            if(pharm!=null)
                            pharmacies.add(pharm);
                        }
                        emitter.onNext(pharmacies);
                        emitter.onComplete();
                    }
                }, BackpressureStrategy.BUFFER
        );
        return flowable;
    }

    public Maybe<Plans> getPlanById(long id){
        return plansDao.getPlanById(id);
    }

    public Flowable<List<OutVisitActivity>> getAllActivities(){
        return outVisitActivityDao.getAllActivites();
    }

    public Maybe<ObjectInPlans> getObjectInPlan(long id){
        return objectInPlansDao.getObjectPlanById(id);
    }
}

