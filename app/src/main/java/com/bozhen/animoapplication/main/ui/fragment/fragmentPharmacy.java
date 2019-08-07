package com.bozhen.animoapplication.main.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bozhen.animoapplication.R;
import com.bozhen.animoapplication.main.model.room.Pharmacy;
import com.bozhen.animoapplication.main.presentation.presenter.PharmacyPresenter;
import com.bozhen.animoapplication.main.presentation.view.PharmacyView;
import com.bozhen.animoapplication.main.ui.Adapter.PharmacyAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class fragmentPharmacy extends MvpAppCompatFragment implements PharmacyView {

    @InjectPresenter
    PharmacyPresenter pharmacyPresenter;

    @ProvidePresenter
    PharmacyPresenter providePharmacyPresenter(){
        return new PharmacyPresenter(getActivity().getApplication());
    }


    public static final String TAG = "fragmentPharmacy";
    private RecyclerView pharmacyRecylcerView;
    PharmacyAdapter pharmacyAdapter;

    public static fragmentPharmacy newInstance() {
        return new fragmentPharmacy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pharmacy_fragment, container, false);
        pharmacyRecylcerView = view.findViewById(R.id.pharmacy_list);
        pharmacyRecylcerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pharmacyAdapter = new PharmacyAdapter();
        pharmacyRecylcerView.setAdapter(pharmacyAdapter);
        return view;
    }

    @Override
    public void notifyDataChanges(List<Pharmacy> doctors) {
        pharmacyAdapter.setDoctors(doctors);
    }
}
