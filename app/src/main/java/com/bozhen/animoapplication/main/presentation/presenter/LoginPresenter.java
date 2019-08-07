package com.bozhen.animoapplication.main.presentation.presenter;

import android.app.Application;
import android.util.Log;

import com.bozhen.animoapplication.main.model.repository.LoadingRepository;
import com.bozhen.animoapplication.main.model.repository.UsersRepository;
import com.bozhen.animoapplication.main.model.room.Users;
import com.bozhen.animoapplication.main.presentation.view.LoginView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {
    public static final String TAG = "Login_PRESENTER";

    private UsersRepository usersRepository;
    private LoadingRepository loadingRepository;

    public LoginPresenter(Application application) {
        this.usersRepository = UsersRepository.getInstance(application);
        this.loadingRepository = LoadingRepository.getInstance(application);
    }
    public void loginUser(final String phone){
        getViewState().startLoading();
        usersRepository.getUserByPhoneNw(phone)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Users>()
                {
                    @Override
                    public void onSuccess(Users users) {
                        usersRepository.insertOrUpdateUser(users).subscribe(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Log.i(TAG,"add user success");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG,"add user error");
                            }
                        });
                        getViewState().showSuccess(users.getPhone());
                        loadingRepository.loadUserDataById(users.getId());
                    }
                    @Override
                    public void onError(Throwable e) {
                        usersRepository.getUserByPhoneDb(phone).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableMaybeObserver<Users>() {
                            @Override
                            public void onSuccess(Users users) {
                                getViewState().showSuccess(users.getPhone());
                            }

                            @Override
                            public void onError(Throwable e) {
                                getViewState().showError("");
                            }

                            @Override
                            public void onComplete() {
                                getViewState().showError("");
                            }
                        });
                    }
                });
    }
}
