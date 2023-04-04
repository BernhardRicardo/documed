package com.android.documed.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.documed.Class.ContainerAndGlobal;
import com.android.documed.Class.Patient;
import com.android.documed.Class.SpinnerAdapter;
import com.android.documed.Class.SpinnerItem;
import com.android.documed.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddPatientActivity extends AppCompatActivity {
    
    Button add, birthdate;
    Spinner sex;
    
    String strBirthdate, strSex;
    EditText name, address, phone, insurance;
    DatePickerDialog datePickerDialog;
    
    private ArrayList<String> sexList;
    private ArrayAdapter<String> sexSpinnerAdapter;
    ArrayList<EditText> elements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        
        initSexList();
        
        try{
            name = findViewById(R.id.editTextName);
            address = findViewById(R.id.editTextAddress);
            phone = findViewById(R.id.editPhone);
            insurance = findViewById(R.id.editTextInsurance);
            birthdate = findViewById(R.id.datePicker);
            
            sex = findViewById(R.id.spinnerSex);
            sexSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sexList);
            sexSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sex.setAdapter(sexSpinnerAdapter);

            elements.add(name);
            elements.add(address);
            elements.add(phone);
            elements.add(insurance);


        }catch (Exception e){e.printStackTrace();}

        strBirthdate = getTodaysDate();
        birthdate.setText(strBirthdate);
        birthdate.setTextColor(getResources().getColor(R.color.black));

        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strSex = sexList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        add = (Button) findViewById(R.id.btnAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(!ContainerAndGlobal.isFormFilled(elements)){

                    } else if (ContainerAndGlobal.isDataDuplicate(elements)) {
                        Toast.makeText(AddPatientActivity.this, "Input failed: duplicates", Toast.LENGTH_SHORT).show();
                    } else{
                        String strName = name.getText().toString();
                        String strAddress = address.getText().toString();
                        String strPhone = phone.getText().toString();
                        String strInsurance = insurance.getText().toString();
                        String strBirthdate = birthdate.getText().toString();
                        String inputStrSex = strSex;

                        Patient patient = new Patient(strName, strBirthdate, inputStrSex, "Undiagnosed", strAddress, strPhone, strInsurance, " ");
                        ContainerAndGlobal.addPatient(patient);
                        Toast.makeText(AddPatientActivity.this, " Patient added successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(AddPatientActivity.this, "Invalid Input ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    
    private void initSexList(){
        sexList = new ArrayList<>();
        sexList.add("Male");
        sexList.add("Female");
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month =  month+1;
        int day =  cal.get(Calendar.DAY_OF_MONTH);
        return dateToString(day,month,year);

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                try {
                    month = month+1;
                    String date =  dateToString(day,month,year);
                    birthdate.setText(date);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        try {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day =  cal.get(Calendar.DAY_OF_MONTH);

            int style = AlertDialog.THEME_HOLO_LIGHT;

            datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year,month,day);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String dateToString(int day, int month, int year){
        return year+"-"+month+"-"+day;
    }
}