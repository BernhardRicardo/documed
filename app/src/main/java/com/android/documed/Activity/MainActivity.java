package com.android.documed.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.documed.R;
import com.android.documed.Class.SpinnerAdapter;
import com.android.documed.Class.SpinnerItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<SpinnerItem> mLanguageList;
    private ArrayList<SpinnerItem> mProfessionList;
    private SpinnerAdapter mLanguageSpinnerAdapter;
    private SpinnerAdapter mProfessionSpinnerAdapter;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.nextButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });

        initLanguageList();
        initProfessionList();

        Spinner spinnerLanguages = findViewById(R.id.spinner_languages);
        mLanguageSpinnerAdapter = new SpinnerAdapter(this, mLanguageList);
        spinnerLanguages.setAdapter(mLanguageSpinnerAdapter);

        Spinner spinnerProfession = findViewById(R.id.spinner_profession);
        mProfessionSpinnerAdapter = new SpinnerAdapter(this, mProfessionList);
        spinnerProfession.setAdapter(mProfessionSpinnerAdapter);

        spinnerLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem clickedItem = (SpinnerItem) parent.getItemAtPosition(position);
                String clickedLanguageName = clickedItem.getName();
                Toast.makeText(MainActivity.this, clickedLanguageName + " selected", Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerProfession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem clickedItem = (SpinnerItem) parent.getItemAtPosition(position);
                String clickedProfessionName = clickedItem.getName();
                Toast.makeText(MainActivity.this, clickedProfessionName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initLanguageList(){
        mLanguageList = new ArrayList<>();
        mLanguageList.add(new SpinnerItem("Select language", R.drawable.ic_language));
        mLanguageList.add(new SpinnerItem("English", R.drawable.ic_english));
        mLanguageList.add(new SpinnerItem("German", R.drawable.ic_germany));
    }

    private void initProfessionList(){
        mProfessionList = new ArrayList<>();
        mProfessionList.add(new SpinnerItem("Select Profession", R.drawable.ic_person));
        mProfessionList.add(new SpinnerItem("Doctor", R.drawable.ic_doctor));
        mProfessionList.add(new SpinnerItem("Laboratory Assistant", R.drawable.ic_lab_assistant));
        mProfessionList.add(new SpinnerItem("Administrator", R.drawable.ic_admin));
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}