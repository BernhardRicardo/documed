package com.android.documed.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.documed.Class.Patient;
import com.android.documed.Class.RecyclerViewAdapter;
import com.android.documed.R;

import java.util.ArrayList;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ListFragment extends Fragment {


    LinearLayoutManager layoutManager;
    List<Patient> patientList;
    RecyclerViewAdapter adapter;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public ListFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ListFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ListFragment newInstance(String param1, String param2) {
//        ListFragment fragment = new ListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        initData();
        try {
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(requireContext());
            recyclerView.setLayoutManager(layoutManager);
            adapter = new RecyclerViewAdapter(patientList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }catch (
                Exception e
        ){
            e.printStackTrace();
        }
    }

    private void initData() {
        patientList = new ArrayList<>();
        patientList.add(new Patient("Bernhard Ricardo", "Stabil", "02.2"));
        patientList.add(new Patient("Bernhard Ricardo", "Stabil", "02.2"));
        patientList.add(new Patient("Bernhard Ricardo", "Stabil", "02.2"));
        patientList.add(new Patient("Bernhard Ricardo", "Stabil", "02.2"));
        patientList.add(new Patient("Bernhard Ricardo", "Stabil", "02.2"));patientList.add(new Patient("Bernhard Ricardo", "Stabil", "02.2"));
        patientList.add(new Patient("Bernhard Ricardo", "Stabil", "02.2"));
        patientList.add(new Patient("Bernhard Ricardo", "Stabil", "02.2"));
        patientList.add(new Patient("Bernhard Ricardo", "Stabil", "02.2"));

        patientList.add(new Patient("Bernhard Ricardo", "Stabil", "02.2"));
    }

}