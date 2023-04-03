package com.android.documed.Class;

public class Patient {

    private String name;
    private String status;
    private String roomNumber;
    private String sex;
    private String address;
    private String insuranceNumber;
    private String birthdate;
    private String phone;
    private String note;
    private BloodValue bloodValue;

    public Patient(String name, String status, String roomNumber) {
        this.name = name;
        this.status = status;
        this.roomNumber = roomNumber;
    }

    public Patient(String name, String birthdate, String sex, String status, String address, String phone, String insuranceNumber, String roomNumber, String note) {
        this.name = name;
        this.birthdate = birthdate;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
        this.insuranceNumber = insuranceNumber;
        this.roomNumber = roomNumber;
        this.note = note;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getSex() {return sex;}

    public String getAddress() {return address;}

    public String getInsuranceNumber() {return insuranceNumber;}

    public String getBirthdate() {return birthdate;}

    public String getPhone() {return phone;}
    public String getNote() {return note;}
    public BloodValue getBloodValueClass() {
        return bloodValue;
    }

    public void setBloodValue(BloodValue bloodValueClass) {
        this.bloodValue = bloodValueClass;
    }
}