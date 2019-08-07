package com.bozhen.animoapplication.main.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bozhen.animoapplication.R;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.ObjectInPlans;
import com.bozhen.animoapplication.main.model.room.ObjectInPlansDoctors;
import com.bozhen.animoapplication.main.model.room.Pharmacy;
import com.bozhen.animoapplication.main.model.room.Plans;
import com.bozhen.animoapplication.main.presentation.presenter.PlansPresenter;
import com.bozhen.animoapplication.main.presentation.presenter.PlansWorkPresenter;
import com.bozhen.animoapplication.main.presentation.view.PlansWorkView;
import com.bozhen.animoapplication.main.ui.Adapter.DoctorsAdapter;
import com.bozhen.animoapplication.main.ui.Adapter.PharmacyAdapter;
import com.bozhen.animoapplication.main.ui.Adapter.PlansDoctorAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class fragmentWorkWithPlans extends MvpAppCompatFragment implements PlansWorkView, PlansDoctorAdapter.OnItemClickListener {

    public static String TAG = fragmentWorkWithPlans.class.getCanonicalName();


    private RecyclerView DoctorsRecyclerView;
    private PlansDoctorAdapter doctorsAdapter;
    private RecyclerView pharmacyRecylcerView;
    private PharmacyAdapter pharmacyAdapter;
    private long plan_id;
    private fragmentDoctors.OnFragmentInteractionListener mListener;

    public static fragmentWorkWithPlans newInstance() {
        return new fragmentWorkWithPlans();
    }

    @InjectPresenter
    PlansWorkPresenter plansWorkPresenter;

    @ProvidePresenter
    PlansWorkPresenter providePlansWorkPresenter() {
        return new PlansWorkPresenter(getActivity().getApplication());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (fragmentDoctors.OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container, false);
        DoctorsRecyclerView = view.findViewById(R.id.doctors_list);
        pharmacyRecylcerView = view.findViewById(R.id.pharmacy_list);
        DoctorsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pharmacyRecylcerView.setLayoutManager(new LinearLayoutManager(getContext()));
        doctorsAdapter = new PlansDoctorAdapter();
        doctorsAdapter.setListener(this);
        DoctorsRecyclerView.setAdapter(doctorsAdapter);
        pharmacyAdapter = new PharmacyAdapter();
        pharmacyRecylcerView.setAdapter(pharmacyAdapter);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        plansWorkPresenter.loadPlans(dateFormat.format(new Date()));
        return view;
    }

    @Override
    public void ShowDoctors(List<ObjectInPlansDoctors> objectInPlansDoctors) {
        doctorsAdapter.setDoctors(objectInPlansDoctors);
    }

    @Override
    public void ShowPharmacy(List<Pharmacy> pharmacy, List<ObjectInPlans> objectInPlans) {

    }

    @Override
    public void ShowPlanId(Plans plans) {
        plan_id = plans.getId();
    }

    @Override
    public void onItemClick(Long id) {
        mListener.onFragmentInteraction(fragmentAddEditDoctors.newInstance(id), fragmentAddEditDoctors.class.getCanonicalName() + id.toString());
    }

    @Override
    public void onBtnClick(Long id) {
        plansWorkPresenter.startObjecInPlan(id,getGPS());
    }

    @SuppressLint("MissingPermission")

    private String getGPS() {
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);

        Location l = null;
        for (int i = providers.size() - 1; i >= 0; i--) {
            l = lm.getLastKnownLocation(providers.get(i));
            if (l != null) break;
        }

        double[] gps = new double[2];
        if (l != null) {
            gps[0] = l.getLatitude();
            gps[1] = l.getLongitude();
        }
        return String.valueOf(gps[0])+","+String.valueOf(gps[1]);
    }
}
