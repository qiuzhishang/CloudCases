package com.xd.utils;

import com.xd.pojo.Patient;

import java.security.PrivateKey;
import java.util.List;

public class DoctorMessage {
    private String phone_num;
    private Patient patient;

    @Override
    public String toString() {
        return "DoctorMessage{" +
                "phone_num='" + phone_num + '\'' +
                ", patient=" + patient +
                '}';
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
