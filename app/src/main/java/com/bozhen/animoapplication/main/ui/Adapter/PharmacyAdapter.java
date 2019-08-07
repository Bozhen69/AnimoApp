package com.bozhen.animoapplication.main.ui.Adapter;

import android.app.Application;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bozhen.animoapplication.R;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.Pharmacy;
import com.bozhen.animoapplication.main.presentation.presenter.DoctorsPresenter;
import com.bozhen.animoapplication.main.presentation.presenter.PharmacyPresenter;
import com.bozhen.animoapplication.main.presentation.view.DoctorsView;
import com.bozhen.animoapplication.main.presentation.view.PharmacyView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.PharmacyViewHolder>  {

    private static final String TAG="PharmacyAdapter";
    private List<Pharmacy> pharmacies = new ArrayList<>();


    @NonNull
    @Override
    public PharmacyAdapter.PharmacyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctors_item, parent, false);
        return new PharmacyAdapter.PharmacyViewHolder(view);
    }

    public void setDoctors(List<Pharmacy> pharmacies){
        Log.i(TAG,"set doctors");
        this.pharmacies = pharmacies;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyAdapter.PharmacyViewHolder holder, int position) {
        holder.bind(pharmacies.get(position));
    }

    @Override
    public int getItemCount() {
        return pharmacies.size();
    }

    class PharmacyViewHolder extends RecyclerView.ViewHolder{
        private TextView pharmacyNameTV;
        private TextView pharmacyInfoTV;

        public PharmacyViewHolder(@NonNull View itemView) {
            super(itemView);
            pharmacyNameTV = itemView.findViewById(R.id.Doctors_FIO);
            pharmacyInfoTV = itemView.findViewById(R.id.Doctors_Info);
        }

        public void bind(Pharmacy pharmacy){
            pharmacyNameTV.setText(pharmacy.getName()+", "+pharmacy.getFirst_name()+" "+
                    pharmacy.getSurname()+" "+pharmacy.getPatronymic());
            StringBuilder stringBuilder = new StringBuilder("");
            stringBuilder.append("Регион: "+pharmacy.getId_region()+"Город: "+pharmacy.getCity()+" Адрес: "+pharmacy.getAddress());
            pharmacyInfoTV.setText(stringBuilder.toString());
        }
    }
}
