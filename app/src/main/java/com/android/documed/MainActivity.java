package com.android.documed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<LanguageItem> mLanguageList;
    private LanguageAdapter mLanguageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();
        Spinner spinnerLanguages = findViewById(R.id.spinner_languages);

        mLanguageAdapter = new LanguageAdapter(this, mLanguageList);
        spinnerLanguages.setAdapter(mLanguageAdapter);

        spinnerLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LanguageItem clickedItem = (LanguageItem) parent.getItemAtPosition(position);
                String clickedLanguageName = clickedItem.getLanguageName();
                Toast.makeText(MainActivity.this, clickedLanguageName + " selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initList(){
        mLanguageList = new ArrayList<>();
        mLanguageList.add(new LanguageItem("English", R.drawable.ic_english));
        mLanguageList.add(new LanguageItem("German", R.drawable.ic_germany));
    }
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long j) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
}