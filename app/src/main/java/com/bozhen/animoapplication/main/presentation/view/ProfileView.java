package com.bozhen.animoapplication.main.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.bozhen.animoapplication.main.model.room.RegionsWithUsers;

public interface ProfileView extends MvpView {
    void ShowUserInfo(RegionsWithUsers regionsWithUsers);
}
