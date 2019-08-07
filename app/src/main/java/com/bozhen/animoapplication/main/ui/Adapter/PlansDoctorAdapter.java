package com.bozhen.animoapplication.main.ui.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bozhen.animoapplication.R;
import com.bozhen.animoapplication.main.model.room.ObjectInPlansDoctors;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlansDoctorAdapter extends RecyclerView.Adapter<PlansDoctorAdapter.PlansDoctorViewHolder> {
    private static final String TAG="PharmacyAdapter";
    private List<ObjectInPlansDoctors> objectInPlansDoctors = new ArrayList<>();
    private PlansDoctorAdapter.OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlansDoctorAdapter.PlansDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plan_item, parent, false);
        return new PlansDoctorAdapter.PlansDoctorViewHolder(view);
    }


    public void setDoctors(List<ObjectInPlansDoctors> objectInPlansDoctors){
        Log.i(TAG,"set doctors");
        this.objectInPlansDoctors = objectInPlansDoctors;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull PlansDoctorViewHolder holder, int position) {
        holder.bind(objectInPlansDoctors.get(position));
    }

    @Override
    public int getItemCount() {
        return objectInPlansDoctors.size();
    }

    class PlansDoctorViewHolder extends RecyclerView.ViewHolder{
        private TextView generalTV;
        private TextView infoTV;
        private Button startBTN;
        private long doctor_id;
        private long objectInplan_id;
        private ImageView imageStatus;
        public PlansDoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            generalTV = itemView.findViewById(R.id.PlanGeneral);
            infoTV = itemView.findViewById(R.id.PlanInfo);
            startBTN = itemView.findViewById(R.id.start_end_btn);
            imageStatus = itemView.findViewById(R.id.progress_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(doctor_id);
                    }
                }
            });
            startBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onBtnClick(objectInplan_id);
                    }
                }
            });
        }

        public void bind(ObjectInPlansDoctors objectInPlansDoctors){
            generalTV.setText(objectInPlansDoctors.doctors.getSurname()+" "+objectInPlansDoctors.doctors.getFirst_name()+" "+objectInPlansDoctors.doctors.getPatronymic());
            StringBuilder stringBuilder = new StringBuilder("");
            stringBuilder.append("Организация:"+ objectInPlansDoctors.doctors.getHospital_name()+", Город: "+objectInPlansDoctors.doctors.getCity()+", Адрес организации:"+ objectInPlansDoctors.doctors.getAddress_hospital() +", Название организации: "+objectInPlansDoctors.doctors.getHospital_name());
            infoTV.setText(stringBuilder.toString());
            if(objectInPlansDoctors.objectInPlans.getPlan_time_start()==null || objectInPlansDoctors.objectInPlans.getPlan_time_start().equals("")){
                Log.i("Set_image","Set начать");
                startBTN.setText("Начать");
                startBTN.setVisibility(View.VISIBLE);
                imageStatus.setVisibility(View.GONE);
            }
            else if(objectInPlansDoctors.objectInPlans.getPlan_time_end()==null || objectInPlansDoctors.objectInPlans.getPlan_time_end().equals("")){
                Log.i("Set_image","Set закончить");
                startBTN.setText("Закончить");
                startBTN.setVisibility(View.VISIBLE);
                imageStatus.setVisibility(View.GONE);
            }
            else {
                Log.i("Set_image","Set image");
                startBTN.setVisibility(View.GONE);
                imageStatus.setVisibility(View.VISIBLE);
            }
            doctor_id = objectInPlansDoctors.doctors.getId();
            objectInplan_id = objectInPlansDoctors.objectInPlans.getId();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Long id);
        void onBtnClick(Long id);
    }
}
