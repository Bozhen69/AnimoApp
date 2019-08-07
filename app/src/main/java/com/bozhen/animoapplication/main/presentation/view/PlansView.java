package com.bozhen.animoapplication.main.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.ObjectInPlansDoctors;
import com.bozhen.animoapplication.main.model.room.OutVisitActivity;
import com.bozhen.animoapplication.main.model.room.Pharmacy;
import com.bozhen.animoapplication.main.model.room.Plans;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface PlansView extends MvpView {
    void ShowDoctors(List<ObjectInPlansDoctors> doctors);
    void ShowPharmacy(List<Pharmacy> pharmacy);
    void ShowPlan(Plans plans);
    void ShowActivities(List<OutVisitActivity> outVisitActivity);
}
