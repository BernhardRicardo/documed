package com.android.documed.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.documed.Class.ContainerAndGlobal;
import com.android.documed.Class.Patient;
import com.android.documed.R;

import java.util.List;

public class PatientDataActivity extends AppCompatActivity {

    Patient patient;
    boolean hasMRT = false;
    boolean hasBloodTest = false;
    List<String> statusSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data);

        patient = ContainerAndGlobal.getClickedPatient();

        TextView name = findViewById(R.id.tvPatientName);
        TextView room = findViewById(R.id.tvRoomNumber);
        TextView bithdate = findViewById(R.id.infoBirthDate);
        TextView sex = findViewById(R.id.infoSex);
        TextView status = findViewById(R.id.infoStatus);
        TextView address = findViewById(R.id.infoAddress);
        TextView phone = findViewById(R.id.infoPhone);
        TextView insurance = findViewById(R.id.infoInsuranceNumber);

        name.setText(patient.getName());
        room.setText("Room " + patient.getRoomNumber());
        bithdate.setText(":  " + patient.getBirthdate());
        sex.setText(":  " + patient.getSex());
        address.setText(":  " +  patient.getAddress());
        status.setText(":  " + patient.getStatus());
        phone.setText(":  " + patient.getPhone());
        insurance.setText(":  " + patient.getInsuranceNumber());
    }
}