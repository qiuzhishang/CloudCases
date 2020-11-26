package com.xd.pojo;

import java.util.List;

public class Doctor {
    private Long id;
    private String name;
    private String id_num;
    private String hospital;
    private String department;
    private int flag;

    private String specialty;
    private String personal_info;
    private String social_work;
    private List<DoctorAddr> address;
    private String addr;
    private Long user_id;

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", id_num='" + id_num + '\'' +
                ", hospital='" + hospital + '\'' +
                ", department='" + department + '\'' +
                ", flag=" + flag +
                ", specialty='" + specialty + '\'' +
                ", personal_info='" + personal_info + '\'' +
                ", social_work='" + social_work + '\'' +
                ", address=" + address +
                ", addr='" + addr + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPersonal_info() {
        return personal_info;
    }

    public void setPersonal_info(String personal_info) {
        this.personal_info = personal_info;
    }

    public String getSocial_work() {
        return social_work;
    }

    public void setSocial_work(String social_work) {
        this.social_work = social_work;
    }

    public List<DoctorAddr> getAddress() {
        return address;
    }

    public void setAddress(List<DoctorAddr> address) {
        this.address = address;
    }
}
