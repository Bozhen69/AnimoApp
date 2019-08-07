package com.bozhen.animoapplication.main.ui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bozhen.animoapplication.R;
import com.bozhen.animoapplication.main.model.room.Doctors;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.DoctorsViewHolder> {
    private static final String TAG="DoctorsAdapter";
    private List<Doctors> doctors = new ArrayList<>();
    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public DoctorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctors_item, parent, false);
        return new DoctorsViewHolder(view);
    }

    public void setDoctors(List<Doctors> doctors){
        this.doctors = doctors;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorsViewHolder holder, int position) {
        holder.bind(doctors.get(position));
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    class DoctorsViewHolder extends RecyclerView.ViewHolder{
        private TextView fioTV;
        private TextView doctorInfoTV;
        private long id;
        public DoctorsViewHolder(@NonNull View itemView) {
            super(itemView);
            fioTV = itemView.findViewById(R.id.Doctors_FIO);
            doctorInfoTV = itemView.findViewById(R.id.Doctors_Info);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(id);
                    }
                }
            });
        }

        public void bind(Doctors doctors){
            fioTV.setText(doctors.getSurname()+" "+doctors.getFirst_name()+" "+doctors.getPatronymic());
            StringBuilder stringBuilder = new StringBuilder("");
            stringBuilder.append("Организация:"+ doctors.getHospital_name()+", Город: "+doctors.getCity()+", Адрес организации:"+ doctors.getAddress_hospital() +", Название организации: "+doctors.getHospital_name());
            doctorInfoTV.setText(stringBuilder.toString());
            id = doctors.getId();
        }

    }
    public interface OnItemClickListener {
        void onItemClick(Long id);
    }
}
