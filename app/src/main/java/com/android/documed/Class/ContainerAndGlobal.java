package com.android.documed.Class;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContainerAndGlobal {

    private static ArrayList<Patient> patientList = new ArrayList<>();
    private static ArrayList<Patient> adminPatientList = new ArrayList<>();
    private static ArrayList<Patient> mrtPatient = new ArrayList<>();
    private static ArrayList<Patient> bloodPatient = new ArrayList<>();
    private static ArrayList<Patient> filteredList = new ArrayList<>();

    enum profession{DOCTOR, LAB_ASSISTANT, ADMINISTRATOR}

    profession userProfession;

    private static boolean firstTime = true;
    private static Patient tmpPatient;
    private static boolean darkmode = false;
    private static Patient clickedPatient;


    public static boolean isFormContainsEmptyElement(ArrayList<EditText> elements){
        boolean empty = false;
        for(EditText e:elements){
            if(e.getText().toString().isEmpty()){
                e.setError("Cannot be blank");
                empty = true;
            }
        }
        return empty;
    }

    public static boolean isDataDuplicate(ArrayList<EditText> elements){
        boolean duplicate = false;
        int insurance = Integer.parseInt(elements.get(3).getText().toString());
        int phone = Integer.parseInt(elements.get(4).getText().toString());
        int zimmerNummer = Integer.parseInt(elements.get(5).getText().toString());
        for(Patient patient:adminPatientList){
            if(patient.getInsuranceNumber().equals(insurance)){
                elements.get(3).setError("Insurance number already existed");
                duplicate = true;
            }
//            if(patient.getRoomNumber().equals(roomNumber)){
//                elements.get(5).setError("Bed is already occupied");
//                duplicate = true;
//            }
        }
        return duplicate;
    }


    public static String loadPatientJson(Context context, String textFileName){
        String strJson;
        StringBuilder buf = new StringBuilder();
        InputStream inputStream;

        try{
            inputStream = context.getAssets().open(textFileName);

            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            while((strJson = in.readLine()) != null){
                buf.append(strJson);
            }
            in.close();
        }catch (Exception e){
            Log.e("TAG", "loadJson: error " + e);
        }

        return buf.toString();
    }

    public static void parsePatientObject(JSONObject patient)throws JSONException{
        Patient tmpPatient = new Patient(
                (String) patient.get("name").toString(),
                (String) patient.get("birthdate").toString(),
                (String) patient.get("sex").toString(),
                (String) patient.get("status").toString(),
                (String) patient.get("address").toString(),
                (String) patient.get("phone").toString(),
                (String) patient.get("insurance").toString(),
                (String) patient.get("room").toString(),
                (String) patient.get("note").toString()
        );
        addPatient(tmpPatient);
    }

    public static void addPatient(Patient patient){
        patientList.add(patient);
        adminPatientList.add(patient);
    }

    public static void addPatientMRT(Patient patient){
        mrtPatient.add(patient);
    }

    public static ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public static ArrayList<Patient> getAdminPatientList() {
        return adminPatientList;
    }

    public static ArrayList<Patient> getMrtPatient() {
        return mrtPatient;
    }

    public static ArrayList<Patient> getBloodPatient() {
        return bloodPatient;
    }

    public static ArrayList<Patient> getFilteredList() {
        return filteredList;
    }

    public static Patient getClickedPatient() {
        return clickedPatient;
    }

    public profession getUserProfession() {return userProfession;}

    public void setUserProfession(profession userProfession) {
        this.userProfession = userProfession;
    }

    public static void setClickedPatient(Patient clickedPatient) {
        ContainerAndGlobal.clickedPatient = clickedPatient;
    }
    public static boolean isFirstTime() {
        return firstTime;
    }

    public static void setFirstTime(boolean firstTime) {
        ContainerAndGlobal.firstTime = firstTime;
    }
}
