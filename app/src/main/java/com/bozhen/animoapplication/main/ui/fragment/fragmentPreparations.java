package com.bozhen.animoapplication.main.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.bozhen.animoapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentPreparations extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preparations_network_fragment, container, false);
        List<String> a = new ArrayList<>();
        a.add("да");
        List<String> b = new ArrayList<>();
        b.add("нет");
        ArrayAdapter<String> stringArrayAdapter1 = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,a);
        ArrayAdapter<String> stringArrayAdapter2 = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,b);
        Spinner sp_1_1 = view.findViewById(R.id.spinner1_1);
        sp_1_1.setAdapter(stringArrayAdapter1);
        Spinner sp_1_2 = view.findViewById(R.id.spinner1_2);
        sp_1_2.setAdapter(stringArrayAdapter2);

        Spinner sp_2_1 = view.findViewById(R.id.spinner2_1);
        sp_2_1.setAdapter(stringArrayAdapter1);
        Spinner sp_2_2 = view.findViewById(R.id.spinner2_2);
        sp_2_2.setAdapter(stringArrayAdapter2);

        Spinner sp_3_1 = view.findViewById(R.id.spinner3_1);
        sp_3_1.setAdapter(stringArrayAdapter1);
        Spinner sp_3_2 = view.findViewById(R.id.spinner3_2);
        sp_3_2.setAdapter(stringArrayAdapter2);

        Spinner sp_4_1 = view.findViewById(R.id.spinner4_1);
        sp_4_1.setAdapter(stringArrayAdapter1);
        Spinner sp_4_2 = view.findViewById(R.id.spinner4_2);
        sp_4_2.setAdapter(stringArrayAdapter2);

        Spinner sp_5_1 = view.findViewById(R.id.spinner5_1);
        sp_5_1.setAdapter(stringArrayAdapter1);
        Spinner sp_5_2 = view.findViewById(R.id.spinner5_2);
        sp_5_2.setAdapter(stringArrayAdapter2);

        return view;
    }
}
