package com.bozhen.animoapplication.main.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.Hospitals;
import com.bozhen.animoapplication.main.model.room.Posts;
import com.bozhen.animoapplication.main.model.room.Regions;
import com.bozhen.animoapplication.main.model.room.Specialty;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface DoctorsAddEditView extends MvpView {
    void showDoctorsInfo(Doctors doctors);
    void showHospitals(List<Hospitals> hospitals, int selected);
    void showSpecialtrys(List<Specialty> specialties,int selected);
    void showPosts(List<Posts> posts,int selected);
    void showRegions(List<Regions> regions,int selected);
}
