package com.bozhen.animoapplication.main.ui.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bozhen.animoapplication.R;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.ObjectInPlansDoctors;
import com.bozhen.animoapplication.main.model.room.OutVisitActivity;
import com.bozhen.animoapplication.main.model.room.Pharmacy;
import com.bozhen.animoapplication.main.model.room.Plans;
import com.bozhen.animoapplication.main.presentation.presenter.PlansPresenter;
import com.bozhen.animoapplication.main.presentation.view.PlansView;
import com.bozhen.animoapplication.main.ui.Adapter.DoctorsAdapter;
import com.bozhen.animoapplication.main.ui.Adapter.DoctorsObjectPlanAdapter;
import com.bozhen.animoapplication.main.ui.Adapter.PharmacyAdapter;
import com.bozhen.animoapplication.main.ui.Adapter.PlansDoctorAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class fragmentPlans extends MvpAppCompatFragment implements PlansView {
    public static String TAG = fragmentPlans.class.getCanonicalName();
    private RecyclerView DoctorsRecyclerView;
    private DoctorsObjectPlanAdapter doctorsAdapter;
    private RecyclerView pharmacyRecylcerView;
    private PharmacyAdapter pharmacyAdapter;
    private int mYear, mMonth, mDay;
    private long plan_id;
    private Button findButt;
    private Spinner activitiesSp;
    private EditText commentET;
    private TextView datePlan;
    private ArrayAdapter<OutVisitActivity> activitiesAdapter;
    private Button addDoctorBtn;
    private Button addPharmacyBtn;
    private fragmentDoctors.OnFragmentInteractionListener mListener;


    public static fragmentPlans newInstance() {
        return new fragmentPlans();
    }

    @InjectPresenter
    PlansPresenter plansPresenter;

    @ProvidePresenter
    PlansPresenter provideDoctorsPresenter(){
        return new PlansPresenter(getActivity().getApplication());
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
        View view = inflater.inflate(R.layout.fragment_plans,container,false);
        DoctorsRecyclerView = view.findViewById(R.id.doctors_list);
        pharmacyRecylcerView = view.findViewById(R.id.pharmacy_list);
        DoctorsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pharmacyRecylcerView.setLayoutManager(new LinearLayoutManager(getContext()));
        doctorsAdapter = new DoctorsObjectPlanAdapter();

        activitiesSp = view.findViewById(R.id.activity_chose);
        commentET = view.findViewById(R.id.plan_comment);




        DoctorsRecyclerView.setAdapter(doctorsAdapter);
        doctorsAdapter.setOnItemClickListener(new DoctorsObjectPlanAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Long id) {
                Log.i("NewInstansDetail",String.valueOf(id));
               //Правильный функционал
                mListener.onFragmentInteraction(fragmentDetailObjectInPlan.newInstance(id),fragmentDetailObjectInPlan.class.getCanonicalName()+String.valueOf(id));
                //mListener.onFragmentInteraction(new fragmentPreparations(),fragmentPreparations.class.getCanonicalName());
            }
            @Override
            public void onItemDelete(Long id) {
                plansPresenter.deleteObjectInPlan(id);
            }
        });
        pharmacyAdapter = new PharmacyAdapter();
        pharmacyRecylcerView.setAdapter(pharmacyAdapter);
        findButt = view.findViewById(R.id.find);
        datePlan = view.findViewById(R.id.date);
        datePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDatePicker();
            }
        });
        findButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plansPresenter.loadPlans(datePlan.getText().toString());
                pharmacyAdapter.setDoctors(new ArrayList<Pharmacy>());
                doctorsAdapter.setDoctors(new ArrayList<ObjectInPlansDoctors>());
            }
        });
        addDoctorBtn = view.findViewById(R.id.add_doctor_for_plan_button);
        addDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(fragmentDoctors.newInstance(plan_id),fragmentDoctors.class.getCanonicalName()+plan_id);
            }
        });
        return view;
    }


    @Override
    public void ShowDoctors(List<ObjectInPlansDoctors> objectInPlansDoctors) {
        doctorsAdapter.setDoctors(objectInPlansDoctors);
    }

    @Override
    public void ShowPharmacy(List<Pharmacy> pharmacy) {
        Log.i("Plans_Fragment pharmacy",String.valueOf(pharmacy.size()));
        pharmacyAdapter.setDoctors(pharmacy);
    }
    private void callDatePicker() {
        // получаем текущую дату
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        // инициализируем диалог выбора даты текущими значениями
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(0);
                        cal.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        datePlan.setText(dateFormat.format(cal.getTime()));
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void ShowPlan(Plans plans) {
        plan_id = plans.getId();
        commentET.setText(plans.getNote());
        for(int i=0;i<activitiesAdapter.getCount();i++){
            if(activitiesAdapter.getItem(i).getId()==plans.getActivity_id())
                activitiesSp.setSelection(i);
            break;
        }
    }

    @Override
    public void ShowActivities(List<OutVisitActivity> outVisitActivity) {
        Log.i("ShowActivities",String.valueOf(outVisitActivity.size()));
        activitiesAdapter = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,outVisitActivity);
        activitiesSp.setAdapter(activitiesAdapter);
    }
}
