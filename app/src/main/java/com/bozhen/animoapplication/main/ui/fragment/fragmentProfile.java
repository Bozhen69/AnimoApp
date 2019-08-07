package com.bozhen.animoapplication.main.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bozhen.animoapplication.R;
import com.bozhen.animoapplication.main.model.room.RegionsWithUsers;
import com.bozhen.animoapplication.main.presentation.presenter.LoginPresenter;
import com.bozhen.animoapplication.main.presentation.presenter.ProfilePresenter;
import com.bozhen.animoapplication.main.presentation.view.ProfileView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class fragmentProfile extends MvpAppCompatFragment implements ProfileView {

    @InjectPresenter
    ProfilePresenter mProfilePresenter;

    @ProvidePresenter
    ProfilePresenter provideProfilePresenter(){
        return new ProfilePresenter(getActivity().getApplication());
    }

    public static final String TAG = "fragmentProfile";
    private TextView mFioTV;
    private TextView mRegionTV;
    private TextView mEmailTv;
    private TextView mDateTv;
    private Button mLeaveBT;
    public static fragmentProfile newInstance() {
        return new fragmentProfile();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.profile_fragment, container, false);
       mFioTV = view.findViewById(R.id.user_name);
       mRegionTV = view.findViewById(R.id.user_region);
       mEmailTv = view.findViewById(R.id.user_email);
       mDateTv = view.findViewById(R.id.user_date);
       mProfilePresenter.loadUserInfo();
       return view;
    }

    @Override
    public void ShowUserInfo(RegionsWithUsers regionsWithUsers) {
        mFioTV.setText(regionsWithUsers.getSurname()+" "+regionsWithUsers.getFirst_name()+" "+regionsWithUsers.getPatronymic());
        mRegionTV.setText(regionsWithUsers.getNames());
        String email = regionsWithUsers.getEmail();
        if(email.equals(""))
        mEmailTv.setText("За пользователем не закреплен email");
        else
            mEmailTv.setText(email);
        mDateTv.setText(regionsWithUsers.getDate());
    }
}
