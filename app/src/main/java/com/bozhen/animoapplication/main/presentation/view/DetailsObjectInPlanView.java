package com.bozhen.animoapplication.main.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bozhen.animoapplication.main.model.room.ObjectInPlans;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface DetailsObjectInPlanView extends MvpView {
    void showInformation(ObjectInPlans objectInPlans);
}
