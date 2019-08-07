package com.bozhen.animoapplication.main.ui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bozhen.animoapplication.R;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.ObjectInPlansDoctors;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorsObjectPlanAdapter extends RecyclerView.Adapter<DoctorsObjectPlanAdapter.DoctorsObjectPlanViewHolder> {
    private static final String TAG="DoctorsAdapter";
    private List<ObjectInPlansDoctors> doctors = new ArrayList<>();
    private DoctorsObjectPlanAdapter.OnItemClickListener listener;
    public void setOnItemClickListener(DoctorsObjectPlanAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public DoctorsObjectPlanAdapter.DoctorsObjectPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plan_item_doctor, parent, false);
        return new DoctorsObjectPlanAdapter.DoctorsObjectPlanViewHolder(view);
    }

    public void setDoctors(List<ObjectInPlansDoctors> doctors){
        this.doctors = doctors;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorsObjectPlanAdapter.DoctorsObjectPlanViewHolder holder, int position) {
        holder.bind(doctors.get(position));
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    class DoctorsObjectPlanViewHolder extends RecyclerView.ViewHolder{
        private TextView fioTV;
        private TextView doctorInfoTV;
        private ImageButton btnDelete;
        private long id;
        public DoctorsObjectPlanViewHolder(@NonNull View itemView) {
            super(itemView);
            fioTV = itemView.findViewById(R.id.Doctors_FIO);
            doctorInfoTV = itemView.findViewById(R.id.Doctors_Info);
            btnDelete = itemView.findViewById(R.id.dele_object_in_plan_btn);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(id);
                    }
                }
            });
            btnDelete.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onItemDelete(id);
                        }
                    }
            );
        }

        public void bind(ObjectInPlansDoctors doctors){
            fioTV.setText(doctors.getDoctors().getSurname()+" "+doctors.getDoctors().getFirst_name()+" "+doctors.getDoctors().getPatronymic());
            StringBuilder stringBuilder = new StringBuilder("");
            stringBuilder.append("Организация:"+ doctors.getDoctors().getHospital_name()+", Город: "+doctors.getDoctors().getCity()+", Адрес организации:"+ doctors.getDoctors().getAddress_hospital() +", Название организации: "+doctors.getDoctors().getHospital_name());
            doctorInfoTV.setText(stringBuilder.toString());
            id = doctors.getObjectInPlans().getId();
        }

    }
    public interface OnItemClickListener {
        void onItemClick(Long id);
        void onItemDelete(Long id);
    }
}
