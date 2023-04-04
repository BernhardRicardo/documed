package com.android.documed.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.documed.Activity.AddPatientActivity;
import com.android.documed.Activity.HomeActivity;
import com.android.documed.Activity.MainActivity;
import com.android.documed.Activity.PatientDataActivity;
import com.android.documed.Class.ContainerAndGlobal;
import com.android.documed.Class.Patient;
import com.android.documed.Class.RecyclerViewAdapter;
import com.android.documed.Class.RecyclerViewInterface;
import com.android.documed.R;

import java.util.ArrayList;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ListFragment extends Fragment implements RecyclerViewInterface {

    LinearLayoutManager layoutManager;
    RecyclerViewAdapter adapter;
    SharedPreferences sp;

    private Button btnAdd;

    public ListFragment(SharedPreferences sp) {
        this.sp = sp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        btnAdd = (Button) view.findViewById(R.id.btnAddPatient);
        if(sp.getString("profession", "") == "Administrator"){
            btnAdd.setVisibility(View.VISIBLE);
        }else {
            btnAdd.setVisibility(View.GONE);
        }

        try {
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(requireContext());
            recyclerView.setLayoutManager(layoutManager);
            adapter = new RecyclerViewAdapter(ContainerAndGlobal.getPatientList(), this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }catch (
                Exception e
        ){
            e.printStackTrace();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddPatientActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        ContainerAndGlobal.setClickedPatient(ContainerAndGlobal.getPatientList().get(position));
        startActivity(new Intent(getActivity(), PatientDataActivity.class));
    }
}