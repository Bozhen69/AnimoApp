package com.bozhen.animoapplication.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bozhen.animoapplication.R;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.Specialty;
import com.bozhen.animoapplication.main.presentation.presenter.DoctorsPresenter;
import com.bozhen.animoapplication.main.presentation.view.DoctorsView;
import com.bozhen.animoapplication.main.ui.Adapter.DoctorsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class fragmentDoctors extends MvpAppCompatFragment implements DoctorsView {

    private OnFragmentInteractionListener mListener;
    private long plan_id=-1;
    @InjectPresenter
    DoctorsPresenter doctorsPresenter;

    @ProvidePresenter
    DoctorsPresenter provideDoctorsPresenter(){
        return new DoctorsPresenter(getActivity().getApplication());
    }

    public static final String TAG = "fragmentDoctors";

    private static final String START_DATA = "Start data for fragment";

    private RecyclerView DoctorsRecyclerView;
    private DoctorsAdapter doctorsAdapter;
    private FloatingActionButton fab;
    public static fragmentDoctors newInstance() {
        return new fragmentDoctors();
    }

    public static fragmentDoctors newInstance(long id){
        fragmentDoctors fragment = new fragmentDoctors();
        Bundle bundle = new Bundle();
        bundle.putLong(START_DATA,id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Fragment link,String tag);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctors_fragment, container, false);
        DoctorsRecyclerView = view.findViewById(R.id.doctors_list);
        DoctorsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fab = view.findViewById(R.id.fab_add_doctor);
        List<String> strings1 = new ArrayList<>();
        strings1.add("Все");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,strings1);
        Spinner spinner1 = view.findViewById(R.id.organizationSp);
        Spinner spinner2 = view.findViewById(R.id.specializationSp);
        spinner1.setAdapter(arrayAdapter);
        spinner2.setAdapter(arrayAdapter);
        if(getArguments() == null) {
            Log.i(TAG,"Bundle havent nothing");
            fab.show();
            fab.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.onFragmentInteraction(new fragmentAddEditDoctors(), fragmentAddEditDoctors.class.getCanonicalName());
                        }
                    }
            );
        }else
            plan_id = getArguments().getLong(START_DATA);
        doctorsAdapter = new DoctorsAdapter();
        if(plan_id==-1) {
            doctorsAdapter.setOnItemClickListener(
                    new DoctorsAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Long id) {
                            mListener.onFragmentInteraction(fragmentAddEditDoctors.newInstance(id), fragmentAddEditDoctors.class.getCanonicalName() + id.toString());
                        }
                    }
            );
        }
        else {
            doctorsAdapter.setOnItemClickListener(
                    new DoctorsAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Long id) {
                            doctorsPresenter.addDoctorInPlan(plan_id,id);
                        }
                    }
            );
        }
        DoctorsRecyclerView.setAdapter(doctorsAdapter);
        return view;
    }



    @Override
    public void notifyDataChanges(List<Doctors> doctors) {
        doctorsAdapter.setDoctors(doctors);
    }

    @Override
    public void ShowDoctorsAddInPlansInfo(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
}
