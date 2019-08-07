package com.bozhen.animoapplication.main.presentation.presenter;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bozhen.animoapplication.main.model.repository.DoctorsRepository;
import com.bozhen.animoapplication.main.model.repository.UsersRepository;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.Hospitals;
import com.bozhen.animoapplication.main.model.room.Posts;
import com.bozhen.animoapplication.main.model.room.Regions;
import com.bozhen.animoapplication.main.model.room.Specialty;
import com.bozhen.animoapplication.main.model.room.Users;
import com.bozhen.animoapplication.main.presentation.view.DoctorsAddEditView;
import com.bozhen.animoapplication.main.utils.Settings;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;


@InjectViewState
public class AddDoctorsPresenter extends MvpPresenter<DoctorsAddEditView> {
    private static final String TAG = "ADD_Doctors_Presenter";
    private SharedPreferences mSettings;
    private DoctorsRepository doctorsRepository;
    private UsersRepository usersRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AddDoctorsPresenter(Application application) {
       doctorsRepository = DoctorsRepository.newInstance(application);
        this.usersRepository = UsersRepository.getInstance(application);
        this.mSettings = application.getSharedPreferences(Settings.APP_PREFERENCES, Context.MODE_PRIVATE);
        Log.i("ADD_Doctor_Presenter","initiate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("ADD_Doctor_Presenter","destroy");
    }

    private void loadRegions(final long id){
        Log.i("ADD_Doctor_Presenter"," load regions");
        compositeDisposable.add(usersRepository.getUserByPhoneDb(mSettings.getString(Settings.APP_PREFERENCES_PHONE,""))
                .subscribe(
                        new Consumer<Users>() {
                            @Override
                            public void accept(Users users) throws Exception {
                                usersRepository.getUserRegions(users.getId())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        new DisposableMaybeObserver<List<Regions>>() {
                                            @Override
                                            public void onSuccess(List<Regions> regions) {
                                                if (id != -1) {
                                                    for (int i = 0; i < regions.size(); i++) {
                                                        if (regions.get(i).getId() == id) {
                                                            getViewState().showRegions(regions, i);
                                                            break;
                                                        }
                                                    }
                                                }
                                                else
                                                    getViewState().showRegions(regions, 0);

                                            }

                                            @Override
                                            public void onError(Throwable e) {

                                            }

                                            @Override
                                            public void onComplete() {

                                            }
                                        }
                                );
                            }
                        }
                        ,
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        }
                ));
    }

    private void loadPosts(final long id){
        Log.i("ADD_Doctor_Presenter","load posts");
        compositeDisposable.add(doctorsRepository.getAllPosts().observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<Posts>>() {
                            @Override
                            public void accept(List<Posts> posts) throws Exception {
                                if (id != -1) {
                                    for (int i = 0; i < posts.size(); i++) {
                                        if (posts.get(i).getId() == id) {
                                            getViewState().showPosts(posts, i);
                                            break;
                                        }
                                    }
                                }
                                else
                                    getViewState().showPosts(posts, 0);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        }
                )
        );
    }

    public void loadInfoForDoctorsView(long id){
        loadDoctorInfo(id);
    }

    public void loadInfoForDoctorsView(){
        loadHospitals(-1);
        loadPosts(-1);
        loadRegions(-1);
        loadSpecialty(-1);
    }

    private void loadHospitals(final long id){
        Log.i("ADD_Doctor_Presenter","load hospitals");
        compositeDisposable.add(doctorsRepository.getAllHospitals().observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                new Consumer<List<Hospitals>>() {
                    @Override
                    public void accept(List<Hospitals> hospitals) throws Exception {
                        Log.i("ADD_Doctor_Presenter",hospitals.toArray().toString() +" id "+id);
                        if(id!=-1){
                        for(int i=0;i<hospitals.size();i++){
                            if(hospitals.get(i).getId()==id) {
                                getViewState().showHospitals(hospitals, i);
                                break;
                            }
                        }
                    }
                    else
                            getViewState().showHospitals(hospitals, 0);

                }},
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }
        ));
    }

    private void loadSpecialty(final long id){
        Log.i("ADD_Doctor_Presenter","load specialty");
        compositeDisposable.add(doctorsRepository.getAllSpecialty().observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<Specialty>>() {
                            @Override
                            public void accept(List<Specialty> specialties) throws Exception {
                                if(id!=-1) {
                                    for (int i = 0; i < specialties.size(); i++) {
                                        if (specialties.get(i).getId() == id) {
                                            getViewState().showSpecialtrys(specialties, i);
                                            break;
                                        }
                                    }
                                }
                                else
                                    getViewState().showSpecialtrys(specialties, 0);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        }
                )
        );
    }

    private void loadDoctorInfo(long id){
        Log.i("ADD_Doctor_Presenter","doctors info");
        compositeDisposable.add(doctorsRepository.getDoctor(id).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<Doctors>() {
                            @Override
                            public void accept(Doctors doctors) throws Exception {
                                Log.i("ADD_Doctor_Presenter",String.valueOf(doctors.getId()));
                                getViewState().showDoctorsInfo(doctors);
                                loadHospitals(doctors.getId_hospital());
                                loadPosts(doctors.getId_post());
                                loadRegions(doctors.getId_region());
                                loadSpecialty(doctors.getId_spec());

                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        }
                ));
    }

    public void insertOrUpdateDoctor(Doctors doctors){
        doctorsRepository.insertOrUpdate(doctors).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<Long>() {
            @Override
            public void onSuccess(Long aLong) {
                loadInfoForDoctorsView(aLong);
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
