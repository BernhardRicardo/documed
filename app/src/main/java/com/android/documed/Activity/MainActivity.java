package com.android.documed.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.documed.Class.ContainerAndGlobal;
import com.android.documed.R;
import com.android.documed.Class.SpinnerAdapter;
import com.android.documed.Class.SpinnerItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<SpinnerItem> mLanguageList;
    private ArrayList<SpinnerItem> mProfessionList;
    private SpinnerAdapter mLanguageSpinnerAdapter;
    private SpinnerAdapter mProfessionSpinnerAdapter;
    private Button button;
    SharedPreferences sp;
    String professionStr, languageStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.nextButton);

        if(ContainerAndGlobal.isFirstTime()){
            ContainerAndGlobal.setFirstTime(false);

            //Read JSON
            if(ContainerAndGlobal.getAdminPatientList().size() == 0 && ContainerAndGlobal.getPatientList().size() == 0){
                try{
                    String jsonString = ContainerAndGlobal.loadPatientJson(this, "patientsJSON.json");
                    JSONArray jsonarray = new JSONArray(jsonString);
                    for(int i = 0; i < jsonarray.length(); i++){
                        JSONObject json_inside = jsonarray.getJSONObject(i);
                        ContainerAndGlobal.parsePatientObject(json_inside);
                    }
                }catch (Exception e){e.printStackTrace();}
            }
        }

        initLanguageList();
        initProfessionList();
        button.setEnabled(false);

        Spinner spinnerLanguages = findViewById(R.id.spinner_languages);
        mLanguageSpinnerAdapter = new SpinnerAdapter(this, mLanguageList);
        spinnerLanguages.setAdapter(mLanguageSpinnerAdapter);

        Spinner spinnerProfession = findViewById(R.id.spinner_profession);
        mProfessionSpinnerAdapter = new SpinnerAdapter(this, mProfessionList);
        spinnerProfession.setAdapter(mProfessionSpinnerAdapter);

        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professionStr = spinnerProfession.getSelectedItem().toString();
                languageStr = spinnerLanguages.getSelectedItem().toString();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("profession", professionStr);
                editor.putString("language", languageStr);
                openHomeActivity();
            }
        });



        spinnerLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem clickedItem = (SpinnerItem) parent.getItemAtPosition(position);
                String clickedLanguageName = clickedItem.getName();
                if(clickedLanguageName.equals("English")){
                    ContainerAndGlobal.setUserLanguage(ContainerAndGlobal.language.ENGLISH);
                }else if(clickedLanguageName.equals("German")) {
                    ContainerAndGlobal.setUserLanguage(ContainerAndGlobal.language.GERMAN);
                }else{
                    ContainerAndGlobal.setUserLanguage(ContainerAndGlobal.language.NONE);
                }
                if(ContainerAndGlobal.getUserProfession() != ContainerAndGlobal.profession.NONE){button.setEnabled(true);}
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
                if(clickedProfessionName.equals("Doctor")){
                    ContainerAndGlobal.setUserProfession(ContainerAndGlobal.profession.DOCTOR);
                }else if(clickedProfessionName.equals("Laboratory Assistant")) {
                    ContainerAndGlobal.setUserProfession(ContainerAndGlobal.profession.LAB_ASSISTANT);
                }else if(clickedProfessionName.equals("Administrator")) {
                    ContainerAndGlobal.setUserProfession(ContainerAndGlobal.profession.ADMINISTRATOR);
                }else{
                    ContainerAndGlobal.setUserProfession(ContainerAndGlobal.profession.NONE);
                }
                if(ContainerAndGlobal.getUserLanguage() != ContainerAndGlobal.language.NONE){button.setEnabled(true);}
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