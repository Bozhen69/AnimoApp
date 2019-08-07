package com.bozhen.animoapplication.main.presentation.presenter;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bozhen.animoapplication.main.model.repository.DoctorsRepository;
import com.bozhen.animoapplication.main.model.repository.PharmacyRepository;
import com.bozhen.animoapplication.main.model.repository.PlansRepository;
import com.bozhen.animoapplication.main.model.repository.UsersRepository;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.ObjectInPlans;
import com.bozhen.animoapplication.main.model.room.ObjectInPlansDoctors;
import com.bozhen.animoapplication.main.model.room.Pharmacy;
import com.bozhen.animoapplication.main.model.room.Plans;
import com.bozhen.animoapplication.main.model.room.Users;
import com.bozhen.animoapplication.main.presentation.view.PlansWorkView;
import com.bozhen.animoapplication.main.utils.Settings;

import java.util.List;


import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class PlansWorkPresenter extends MvpPresenter<PlansWorkView> {
    PharmacyRepository pharmacyRepository;
    DoctorsRepository doctorsRepository;
    PlansRepository plansRepository;
    private UsersRepository usersRepository;
    private SharedPreferences mSettings;
    private static final String TAG = "Plans_WORK_Presenter";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PlansWorkPresenter(Application application) {
        pharmacyRepository = PharmacyRepository.newInstance(application);
        doctorsRepository = DoctorsRepository.newInstance(application);
        plansRepository = PlansRepository.getInstance(application);
        usersRepository = UsersRepository.getInstance(application);
        mSettings = application.getSharedPreferences(Settings.APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void loadPlans(final String date){
        Log.i(TAG,date);
        compositeDisposable.clear();
        usersRepository.getUserByPhoneDb(mSettings.getString(Settings.APP_PREFERENCES_PHONE,""))
                .subscribe(new DisposableMaybeObserver<Users>() {
                    @Override
                    public void onSuccess(final Users users) {
                        dispose();
                        plansRepository.getallUserPlans(users.getId(),date)
                                .subscribe(
                                        new MaybeObserver<Plans>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {
                                                compositeDisposable.add(d);
                                            }

                                            @Override
                                            public void onSuccess(Plans plans) {
                                                showObjectInPlans(plans.getId());
                                            }

                                            @Override
                                            public void onError(Throwable e) {

                                            }

                                            @Override
                                            public void onComplete() {
                                                plansRepository.insertOrUpdatePlan(new Plans(0,date,1,users.getId(),"",""))
                                                        .subscribe(new SingleObserver<Long>() {
                                                            @Override
                                                            public void onSubscribe(Disposable d) {
                                                                compositeDisposable.add(d);
                                                            }

                                                            @Override
                                                            public void onSuccess(Long id) {
                                                                plansRepository.getPlanById(id)
                                                                        .observeOn(AndroidSchedulers.mainThread())
                                                                        .subscribe(
                                                                                new DisposableMaybeObserver<Plans>() {
                                                                                    @Override
                                                                                    public void onSuccess(Plans plans) {
                                                                                        getViewState().ShowPlanId(plans);
                                                                                    }

                                                                                    @Override
                                                                                    public void onError(Throwable e) {
                                                                                        dispose();
                                                                                    }

                                                                                    @Override
                                                                                    public void onComplete() {
                                                                                        dispose();
                                                                                    }
                                                                                }
                                                                        );
                                                            }

                                                            @Override
                                                            public void onError(Throwable e) {

                                                            }
                                                        });
                                            }
                                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        dispose();
                    }
                });
    }

    public void startObjecInPlan(long id,String location){
        plansRepository.NewWorkState(id,location);
    }

    public void showObjectInPlans(long id){
        compositeDisposable.add(plansRepository.getDoctorsAll(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<ObjectInPlansDoctors>>() {
                            @Override
                            public void accept(List<ObjectInPlansDoctors> objectInPlansDoctors) throws Exception {
                                getViewState().ShowDoctors(objectInPlansDoctors);
                            }
                        }
                        ,
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.i(TAG,throwable.getMessage());
                            }
                        }
                )
        );
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }
}
