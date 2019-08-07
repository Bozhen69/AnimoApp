package com.bozhen.animoapplication.main.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.ObjectInPlans;
import com.bozhen.animoapplication.main.model.room.ObjectInPlansDoctors;
import com.bozhen.animoapplication.main.model.room.Pharmacy;
import com.bozhen.animoapplication.main.model.room.Plans;

import java.util.List;
import java.util.Map;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface PlansWorkView extends MvpView {
    void ShowDoctors(List<ObjectInPlansDoctors> objectInPlansDoctors);
    void ShowPharmacy(List<Pharmacy> pharmacy, List<ObjectInPlans> objectInPlans);
    void ShowPlanId(Plans plans);
}
