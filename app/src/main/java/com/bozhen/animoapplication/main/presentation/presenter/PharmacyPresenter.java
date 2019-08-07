package com.bozhen.animoapplication.main.presentation.presenter;

import android.app.Application;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bozhen.animoapplication.main.model.repository.PharmacyRepository;
import com.bozhen.animoapplication.main.model.room.Pharmacy;
import com.bozhen.animoapplication.main.presentation.view.PharmacyView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

@InjectViewState
public class PharmacyPresenter extends MvpPresenter<PharmacyView> {
    private static final String TAG = "Pharmacy_Presenter";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PharmacyRepository pharmacyRepository;

    public PharmacyPresenter(Application application) {
        pharmacyRepository = PharmacyRepository.newInstance(application);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Log.i(TAG, "attach ");
        loadPharmacies();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "clear");
    }

    private void loadPharmacies(){
        compositeDisposable.add(pharmacyRepository.getAllPharmacy().observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<Pharmacy>>() {
                            @Override
                            public void accept(List<Pharmacy> pharmacies) throws Exception {
                                getViewState().notifyDataChanges(pharmacies);
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
}
