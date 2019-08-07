package com.bozhen.animoapplication.main.model.repository;

import android.app.Application;
import android.util.Log;

import com.bozhen.animoapplication.main.model.network.NetworkService;
import com.bozhen.animoapplication.main.model.room.AnimoDb;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.DoctorsDao;
import com.bozhen.animoapplication.main.model.room.Hospitals;
import com.bozhen.animoapplication.main.model.room.HospitalsDao;
import com.bozhen.animoapplication.main.model.room.ObjectInPlans;
import com.bozhen.animoapplication.main.model.room.ObjectInPlansDao;
import com.bozhen.animoapplication.main.model.room.OutVisitActivity;
import com.bozhen.animoapplication.main.model.room.OutVisitActivityDao;
import com.bozhen.animoapplication.main.model.room.Pharmacy;
import com.bozhen.animoapplication.main.model.room.PharmacyDao;
import com.bozhen.animoapplication.main.model.room.PharmacyNetwork;
import com.bozhen.animoapplication.main.model.room.PharmacyNetworkDao;
import com.bozhen.animoapplication.main.model.room.PharmacyNetworkPreparations;
import com.bozhen.animoapplication.main.model.room.PharmacyNetworkPreparationsDao;
import com.bozhen.animoapplication.main.model.room.Plans;
import com.bozhen.animoapplication.main.model.room.PlansDao;
import com.bozhen.animoapplication.main.model.room.Posts;
import com.bozhen.animoapplication.main.model.room.PostsDao;
import com.bozhen.animoapplication.main.model.room.PreparationPharmacyList;
import com.bozhen.animoapplication.main.model.room.PreparationPharmacyListDao;
import com.bozhen.animoapplication.main.model.room.Regions;
import com.bozhen.animoapplication.main.model.room.RegionsDao;
import com.bozhen.animoapplication.main.model.room.Specialty;
import com.bozhen.animoapplication.main.model.room.SpecialtyDao;
import com.bozhen.animoapplication.main.model.room.UsersDao;
import com.bozhen.animoapplication.main.model.room.UsersRegions;
import com.bozhen.animoapplication.main.model.room.UsersRegionsDao;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoadingRepository {
    private static LoadingRepository loadingRepository;
    private NetworkService networkService;
    private PharmacyDao pharmacyDao;
    private DoctorsDao doctorsDao;
    private RegionsDao regionsDao;
    private HospitalsDao hospitalsDao;
    private UsersRegionsDao usersRegionsDao;
    private SpecialtyDao specialtyDao;
    private PostsDao postsDao;
    private PharmacyNetworkDao pharmacyNetworkDao;
    private PharmacyNetworkPreparationsDao pharmacyNetworkPreparationsDao;
    private PreparationPharmacyListDao preparationPharmacyListDao;
    private OutVisitActivityDao outVisitActivityDao;
    private PlansDao plansDao;
    private ObjectInPlansDao objectInPlansDao;
    public static LoadingRepository getInstance(Application application) {
        if(loadingRepository==null)
            loadingRepository = new LoadingRepository(application);
        return loadingRepository;
    }

    private LoadingRepository(Application application) {
        AnimoDb database = AnimoDb.getInstance(application);
        networkService = NetworkService.getInstance();
        doctorsDao = database.doctorsDao();
        pharmacyDao = database.pharmacyDao();
        regionsDao = database.regionsDao();
        usersRegionsDao = database.usersRegionsDao();
        hospitalsDao = database.hospitalsDao();
        specialtyDao = database.specialtyDao();
        outVisitActivityDao = database.outVisitActivityDao();
        postsDao = database.postsDao();
        plansDao = database.plansDao();
        objectInPlansDao = database.objectInPlansDao();
        pharmacyNetworkDao = database.pharmacyNetworkDao();
        pharmacyNetworkPreparationsDao = database.pharmacyNetworkPreparationsDao();
        preparationPharmacyListDao = database.preparationPharmacyListDao();
    }

    public void loadUserDataById(final long user_id){
        networkService.getAnimoApi().getRegions(user_id).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<List<Regions>>() {
            @Override
            public void onSuccess(List<Regions> regions) {
                for (final Regions r:
                        regions) {
                        Log.i("Load Regions",r.getName() + " id - "+r.getId());
                        LoadPharmacyByRegion(r.getId());
                        LoadDoctorsByRegion(r.getId());
                        LoadPlansByUser(user_id);
                        loadHospitals();
                        loadSpecialty();
                        loadPosts();
                        loadAllPharmacyNetwork();
                        loadAllPharmacyNetworkPreparations();
                        loadAllPreparationsForPharmacy();
                        LoadOutVisitActivities();
                        regionsDao.insert(r)
                                .concatWith(
                                        usersRegionsDao.insert(new UsersRegions(user_id, r.getId()))
                                ).subscribe();
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    private void LoadOutVisitActivities()
    {
        networkService.getAnimoApi().getallOutvisitActivities()
                .subscribe(
                        new DisposableSingleObserver<List<OutVisitActivity>>(){
                            @Override
                            public void onSuccess(List<OutVisitActivity> outVisitActivities) {
                                for(OutVisitActivity out:outVisitActivities){
                                    outVisitActivityDao.insert(out).subscribe();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }

    private void loadPosts(){
        networkService.getAnimoApi().getallPosts()
                .subscribe(new DisposableSingleObserver<List<Posts>>() {
                    @Override
                    public void onSuccess(List<Posts> posts) {
                        for(Posts h:posts) {
                            postsDao.insert(h).subscribe();
                            Log.i("LOAD POSTS",h.getName() +"  "+h.getWho());
                        }
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void loadSpecialty(){
        networkService.getAnimoApi().getallSpecialty()
                .subscribe(new DisposableSingleObserver<List<Specialty>>() {
                    @Override
                    public void onSuccess(List<Specialty> specialties) {
                        for(final Specialty h:specialties) {
                            specialtyDao.insert(h).subscribe();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void loadHospitals(){
        networkService.getAnimoApi().getAllHospitals()
        .subscribe(new DisposableSingleObserver<List<Hospitals>>() {
            @Override
            public void onSuccess(List<Hospitals> hospitals) {
                Log.i("Load_Repos",hospitals.toString());
                for(Hospitals h:hospitals)
                hospitalsDao.insert(h).subscribe();
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }
    private void LoadPlansByUser(long userId){
        networkService.getAnimoApi().getaUserPlans(userId)
                .subscribe(
                        new DisposableSingleObserver<List<Plans>>() {
                            @Override
                            public void onSuccess(List<Plans> plans) {
                                for(Plans p:plans) {
                                    plansDao.insert(p).subscribe();
                                    networkService.getAnimoApi().getObjectPlans(p.getId())
                                            .subscribe(new DisposableSingleObserver<List<ObjectInPlans>>() {
                                                @Override
                                                public void onSuccess(List<ObjectInPlans> objectInPlans) {
                                                    for(ObjectInPlans objpl:objectInPlans){
                                                        objectInPlansDao.insert(objpl).subscribe();
                                                    }
                                                }

                                                @Override
                                                public void onError(Throwable e) {

                                                }
                                            });
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }
    private void LoadPharmacyByRegion(long regionId){
        networkService.getAnimoApi().getPharmacyByRegion(regionId)
                .subscribe(
                        new DisposableSingleObserver<List<Pharmacy>>() {
                            @Override
                            public void onSuccess(List<Pharmacy> pharmacy) {
                                for (Pharmacy p:
                                        pharmacy) {
                                    pharmacyDao.insert(p).subscribeOn(Schedulers.io()).subscribe(
                                            new DisposableCompletableObserver() {
                                                @Override
                                                public void onComplete() {

                                                }

                                                @Override
                                                public void onError(Throwable e) {

                                                }
                                            }
                                    );
                                    Log.d("Pharmacy List " + pharmacy.size(), "City "+p.getCity() + "Name is "+p.getName());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }
    private void LoadDoctorsByRegion(long regionId){
        networkService.getAnimoApi().getDoctorsByRegion(regionId)
                .subscribe(
                        new DisposableSingleObserver<List<Doctors>>() {
                            @Override
                            public void onSuccess(List<Doctors> doctors) {
                                for (Doctors d:
                                        doctors) {
                                    Log.d("Doctors List " + doctors.size(), "City "+d.getCity() + "Name is "+d.getFirst_name());
                                    doctorsDao.insert(d).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<Long>() {
                                        @Override
                                        public void onSuccess(Long aLong) {

                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }

    public void loadAllPharmacyNetwork(){
        networkService.getAnimoApi().getallPharmacyNetworks()
                .subscribe(
                        new DisposableSingleObserver<List<PharmacyNetwork>>() {
                            @Override
                            public void onSuccess(List<PharmacyNetwork> pharmacyNetworks) {
                                for(PharmacyNetwork p:pharmacyNetworks) {
                                    pharmacyNetworkDao.insert(p);
                                }
                                dispose();
                            }

                            @Override
                            public void onError(Throwable e) {
                                dispose();
                            }
                        }
                );
    }

    public void loadAllPharmacyNetworkPreparations(){
        networkService.getAnimoApi().getallPreparationsPharmacy()
                .subscribe(
                        new DisposableSingleObserver<List<PreparationPharmacyList>>() {
                            @Override
                            public void onSuccess(List<PreparationPharmacyList> preparationPharmacyLists) {
                                for(PreparationPharmacyList p: preparationPharmacyLists){
                                    preparationPharmacyListDao.insert(p);
                                }
                                dispose();
                            }

                            @Override
                            public void onError(Throwable e) {
                                dispose();
                            }
                        }
                );
    }

    public void loadAllPreparationsForPharmacy(){
        networkService.getAnimoApi().getaNetworkPreparationsForPharmacies()
                .subscribe(
                        new DisposableSingleObserver<List<PharmacyNetworkPreparations>>() {
                            @Override
                            public void onSuccess(List<PharmacyNetworkPreparations> pharmacyNetworkPreparations) {
                                for(PharmacyNetworkPreparations p:pharmacyNetworkPreparations){
                                    pharmacyNetworkPreparationsDao.insert(p);
                                }
                                dispose();
                            }

                            @Override
                            public void onError(Throwable e) {
                                dispose();
                            }
                        }
                );
    }
}
