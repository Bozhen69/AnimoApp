package com.bozhen.animoapplication.main.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.bozhen.animoapplication.main.model.room.Doctors;

import java.util.List;

public interface DoctorsView extends MvpView {
    void notifyDataChanges(List<Doctors> doctors);
    void ShowDoctorsAddInPlansInfo(String message);
}
