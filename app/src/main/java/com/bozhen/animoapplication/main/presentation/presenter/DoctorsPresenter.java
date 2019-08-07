package com.bozhen.animoapplication.main.presentation.presenter;

import android.app.Application;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bozhen.animoapplication.main.model.repository.DoctorsRepository;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.presentation.view.DoctorsView;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

@InjectViewState
public class DoctorsPresenter extends MvpPresenter<DoctorsView> {
    private static final String TAG = "Doctors_Presenter";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private DoctorsRepository doctorsRepository;
    public DoctorsPresenter(Application application) {
        doctorsRepository = DoctorsRepository.newInstance(application);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Log.i(TAG, "Load doctors first attach");
        loadDoctors();
    }

    public void addDoctorInPlan(long plan_id,long doctor_id){
        getViewState().ShowDoctorsAddInPlansInfo(doctorsRepository.addDoctorInPlan(plan_id,doctor_id));
    }

    private void loadDoctors(){
        compositeDisposable.add(doctorsRepository.getAllDoctors().observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<Doctors>>() {
                            @Override
                            public void accept(List<Doctors> doctors) throws Exception {
                                    getViewState().notifyDataChanges(doctors);
                                    for(Doctors doc:doctors){
                                        Log.i("Name_Doc",doc.getFirst_name()+" "+doc.getSurname()+"  id:"+doc.getId());
                                    }
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e(TAG,throwable.getMessage());
                            }
                        }
                ));
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        Log.i(TAG, "Destroy and clear");
    }
}
