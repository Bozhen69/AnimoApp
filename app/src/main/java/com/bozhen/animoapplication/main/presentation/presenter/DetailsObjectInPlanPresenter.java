package com.bozhen.animoapplication.main.presentation.presenter;

import android.app.Application;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bozhen.animoapplication.main.model.repository.PlansRepository;
import com.bozhen.animoapplication.main.model.room.ObjectInPlans;
import com.bozhen.animoapplication.main.presentation.view.DetailsObjectInPlanView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DetailsObjectInPlanPresenter extends MvpPresenter<DetailsObjectInPlanView> {
    private PlansRepository plansRepository;
    public DetailsObjectInPlanPresenter(Application application) {
        plansRepository = PlansRepository.getInstance(application);
    }
    public void showInfo(long id){
        plansRepository.getObjectInPlan(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new DisposableMaybeObserver<ObjectInPlans>() {
                            @Override
                            public void onSuccess(ObjectInPlans objectInPlans) {
                                    getViewState().showInformation(objectInPlans);
                                    dispose();
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
}
