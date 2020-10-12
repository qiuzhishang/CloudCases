package com.xd.pojo;

public class DoctorAddr {

    private Long id;
    private Long doctor_id;
    private String doctor_addr_info;
    private int picture_type;

    @Override
    public String toString() {
        return "DoctorAddr{" +
                "id=" + id +
                ", doctor_id=" + doctor_id +
                ", doctor_addr_info='" + doctor_addr_info + '\'' +
                ", picture_type=" + picture_type +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_addr_info() {
        return doctor_addr_info;
    }

    public void setDoctor_addr_info(String doctor_addr_info) {
        this.doctor_addr_info = doctor_addr_info;
    }

    public int getPicture_type() {
        return picture_type;
    }

    public void setPicture_type(int picture_type) {
        this.picture_type = picture_type;
    }
}
