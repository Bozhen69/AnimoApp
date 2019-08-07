package com.bozhen.animoapplication.main.ui.fragment;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bozhen.animoapplication.R;
import com.bozhen.animoapplication.main.model.room.Doctors;
import com.bozhen.animoapplication.main.model.room.Hospitals;
import com.bozhen.animoapplication.main.model.room.Posts;
import com.bozhen.animoapplication.main.model.room.Regions;
import com.bozhen.animoapplication.main.model.room.Specialty;
import com.bozhen.animoapplication.main.presentation.presenter.AddDoctorsPresenter;
import com.bozhen.animoapplication.main.presentation.view.DoctorsAddEditView;

import java.util.Calendar;
import java.util.List;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class fragmentAddEditDoctors extends MvpAppCompatFragment implements DoctorsAddEditView {

    @InjectPresenter
    AddDoctorsPresenter doctorsPresenter;

    @ProvidePresenter
    AddDoctorsPresenter provideDoctorsPresenter(){
        return new AddDoctorsPresenter(getActivity().getApplication());
    }

    private static final String BUNDLE_CONTENT = "doctor_id";

    private Doctors doctor;
    private EditText firstnameET;
    private  EditText surnameET;
    private EditText patronymicET;
    private EditText name_organizationET;
    private  EditText cityET;
    private   EditText addressET;
    private   EditText emailET;
    private   EditText phoneET;
    private   EditText notesET;
    private   EditText birthdayET;
    private Spinner orgranizationS;
    private Spinner regionS;
    private Spinner spezializationET;
    private Spinner position;
    private Spinner ol;
    private int mYear, mMonth, mDay;
    private Button save_button;

    public static fragmentAddEditDoctors newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong(BUNDLE_CONTENT,id);
        fragmentAddEditDoctors fragment = new fragmentAddEditDoctors();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(BUNDLE_CONTENT)) {
             doctorsPresenter.loadInfoForDoctorsView(getArguments().getLong(BUNDLE_CONTENT));
        }
        else{
            doctorsPresenter.loadInfoForDoctorsView();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_doctor,container,false);
        firstnameET = view.findViewById(R.id.first_name);
        surnameET = view.findViewById(R.id.surname);
        patronymicET = view.findViewById(R.id.patronymic);
        name_organizationET = view.findViewById(R.id.name_organization);
        cityET = view.findViewById(R.id.city);
        addressET = view.findViewById(R.id.address);
        emailET = view.findViewById(R.id.email);
        phoneET = view.findViewById(R.id.phone);
        notesET = view.findViewById(R.id.notes);
        birthdayET = view.findViewById(R.id.birthday_ET);
        orgranizationS = view.findViewById(R.id.organization);
        regionS = view.findViewById(R.id.region);
        spezializationET = view.findViewById(R.id.specialization);
        position = view.findViewById(R.id.position);
        ol = view.findViewById(R.id.ol);
        save_button = view.findViewById(R.id.save_btn);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_data();
            }
        });
        birthdayET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDatePicker();
            }
        });
        return view;
    }

    @Override
    public void showDoctorsInfo(Doctors doctors) {
        Log.i("Set_Doctor_View",doctors.getFirst_name()+doctors.getId());
        doctor = doctors;
        firstnameET.setText(doctors.getFirst_name());
        surnameET.setText(doctors.getSurname());
        patronymicET.setText(doctors.getPatronymic());
        name_organizationET.setText(doctors.getHospital_name());
        cityET.setText(doctors.getCity());
        addressET.setText(doctors.getAddress_hospital());
        emailET.setText(doctors.getEmail());
        phoneET.setText(doctors.getPhone());
        notesET.setText(doctors.getNote());
        birthdayET.setText(doctors.getBithday());
    }

    @Override
    public void showHospitals(List<Hospitals> hospitals,int selected) {
        Log.i("fragment_add",new Integer(hospitals.size()).toString());
        ArrayAdapter<Hospitals> arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,hospitals);
        orgranizationS.setAdapter(arrayAdapter);
        orgranizationS.setSelection(selected);
    }

    @Override
    public void showSpecialtrys(List<Specialty> specialties,int selected) {
        ArrayAdapter<Specialty> arrayAdapter = new ArrayAdapter<Specialty>(getContext(),R.layout.support_simple_spinner_dropdown_item,specialties);
        spezializationET.setAdapter(arrayAdapter);
        spezializationET.setSelection(selected);
    }

    @Override
    public void showRegions(List<Regions> regions,int selected) {
        ArrayAdapter<Regions> arrayAdapter = new ArrayAdapter<Regions>(getContext(),R.layout.support_simple_spinner_dropdown_item,regions);
        regionS.setAdapter(arrayAdapter);
        regionS.setSelection(selected);
    }

    @Override
    public void showPosts(List<Posts> posts,int selected) {
        ArrayAdapter<Posts> arrayAdapter = new ArrayAdapter<Posts>(getContext(),R.layout.support_simple_spinner_dropdown_item,posts);
        position.setAdapter(arrayAdapter);
        position.setSelection(selected);
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
                        String editTextDateParam = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        birthdayET.setText(editTextDateParam);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void load_data(){
        Doctors doctors = new Doctors();
        doctors.setHospital_name(name_organizationET.getText().toString());
        doctors.setEmail(emailET.getText().toString());
        doctors.setFirst_name(firstnameET.getText().toString());
        doctors.setHospital_name(name_organizationET.getText().toString());
        doctors.setUpdated(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
        doctors.setId_spec(((Specialty)spezializationET.getSelectedItem()).getId());
        doctors.setAddress_hospital(addressET.getText().toString());
        doctors.setSurname(surnameET.getText().toString());
        doctors.setPhone(phoneET.getText().toString());
        doctors.setId_region(((Regions)regionS.getSelectedItem()).getId());
        doctors.setPatronymic(patronymicET.getText().toString());
        doctors.setNote(notesET.getText().toString());
        doctors.setCity(cityET.getText().toString());
        if(doctor!=null) {
            doctors.setCreated(doctor.getCreated());
            doctors.setId(doctor.getId());
            doctors.setRating(doctor.getRating());
            doctors.setActive(doctor.getActive());
            doctors.setOl(doctor.getOl());
        }
        doctors.setId_hospital(((Hospitals)orgranizationS.getSelectedItem()).getId());
        doctorsPresenter.insertOrUpdateDoctor(doctors);
    }
}
