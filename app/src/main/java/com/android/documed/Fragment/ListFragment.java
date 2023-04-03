package com.android.documed.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

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
    }

    @Override
    public void onItemClick(int position) {
        ContainerAndGlobal.setClickedPatient(ContainerAndGlobal.getPatientList().get(position));
        startActivity(new Intent(getActivity(), PatientDataActivity.class));
    }
}