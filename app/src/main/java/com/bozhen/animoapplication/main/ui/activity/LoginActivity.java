package com.bozhen.animoapplication.main.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bozhen.animoapplication.main.presentation.view.LoginView;
import com.bozhen.animoapplication.main.presentation.presenter.LoginPresenter;


import com.bozhen.animoapplication.R;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bozhen.animoapplication.main.ui.fragment.fragmentDoctors;
import com.bozhen.animoapplication.main.utils.Settings;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {
    private SharedPreferences mSettings;

    @InjectPresenter
    LoginPresenter mLoginPresenter;

    @ProvidePresenter
    LoginPresenter provideLoginPresenter(){
        return new LoginPresenter(getApplication());
    }

    public static final String TAG = "LoginActivity";

    private Button LoginButton;

    private EditText PhoneInput;

    ProgressBar progressBar;


    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettings = getSharedPreferences(Settings.APP_PREFERENCES, Context.MODE_PRIVATE);
        setContentView(R.layout.activity_login);
        LoginButton = findViewById(R.id.BT_Login);
        PhoneInput = findViewById(R.id.TV_Phone_input);
        progressBar = findViewById(R.id.loadingProgress);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.loginUser(PhoneInput.getText().toString());
            }
        });
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), "Internet problems or incorrect phone",Toast.LENGTH_LONG).show();
        hideLoading();
    }

    @Override
    public void showSuccess(String phone) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(Settings.APP_PREFERENCES_PHONE, phone);
        editor.apply();
        startActivity(MainActivity.newInstance(LoginActivity.this));
        hideLoading();
    }

    @Override
    public void startLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}

