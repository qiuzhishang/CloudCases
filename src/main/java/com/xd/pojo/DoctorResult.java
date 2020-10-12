package com.xd.pojo;

import java.util.List;

public class DoctorResult {

    private Doctor doctor;

    @Override
    public String toString() {
        return "DoctorResult{" +
                "doctor=" + doctor +
                ", doctorAddrs=" + doctorAddrs +
                '}';
    }

    public List<DoctorAddr> getDoctorAddrs() {
        return doctorAddrs;
    }

    public void setDoctorAddrs(List<DoctorAddr> doctorAddrs) {
        this.doctorAddrs = doctorAddrs;
    }

    private List<DoctorAddr> doctorAddrs;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

}
