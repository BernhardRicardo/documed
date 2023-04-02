package com.android.documed.Class;

public class Patient {

    private String textViewPatientName;
    private String textViewPatientStatus;
    private String textViewRoomNumber;

    public Patient(String textViewPatientName, String textViewPatientStatus, String textViewRoomNumber){
        this.textViewPatientName = textViewPatientName;
        this.textViewPatientStatus = textViewPatientStatus;
        this.textViewRoomNumber = textViewRoomNumber;
    }

    public String getTextViewPatientName() {
        return textViewPatientName;
    }

    public String getTextViewPatientStatus() {
        return textViewPatientStatus;
    }

    public String getTextViewRoomNumber() {
        return textViewRoomNumber;
    }
}
