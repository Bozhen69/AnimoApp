package com.bozhen.animoapplication.main.presentation.presenter;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bozhen.animoapplication.main.model.repository.UsersRepository;
import com.bozhen.animoapplication.main.model.room.Regions;
import com.bozhen.animoapplication.main.model.room.RegionsWithUsers;
import com.bozhen.animoapplication.main.model.room.Users;
import com.bozhen.animoapplication.main.presentation.view.ProfileView;
import com.bozhen.animoapplication.main.utils.Settings;

import java.util.List;

import androidx.annotation.MainThread;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView> {
    private static final String TAG = "Profile_Presenter";
    private UsersRepository usersRepository;
    private SharedPreferences mSettings;

    public ProfilePresenter(Application application){
        this.usersRepository = UsersRepository.getInstance(application);
        this.mSettings = application.getSharedPreferences(Settings.APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    protected void onFirstViewAttach() {
        Log.i(TAG," Load profile first attach");;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Destroy and clear");;
    }

    public void loadUserInfo(){

        usersRepository.getregionsWithUsers(mSettings.getString(Settings.APP_PREFERENCES_PHONE,""))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new DisposableObserver<RegionsWithUsers>() {
                            @Override
                            public void onNext(RegionsWithUsers regionsWithUsers) {
                                getViewState().ShowUserInfo(regionsWithUsers);
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
