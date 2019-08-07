package com.bozhen.animoapplication.main.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.bozhen.animoapplication.main.model.room.Pharmacy;

import java.util.List;

public interface PharmacyView extends MvpView {
        void notifyDataChanges(List<Pharmacy> doctors);
}
