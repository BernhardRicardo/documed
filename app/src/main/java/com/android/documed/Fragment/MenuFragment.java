package com.android.documed.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.documed.Activity.MainActivity;
import com.android.documed.R;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link MenuFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class MenuFragment extends Fragment {
    Button btnLogout;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public MenuFragment(SharedPreferences sp, SharedPreferences.Editor editor) {
        this.editor = editor;
        this.sp = sp;
        this.editor = this.sp.edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        btnLogout = (Button) view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}