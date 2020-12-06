package com.xd.pojo;

import java.util.List;

//门诊病历
public class OutPatient {
    private long id;
    private String date;//日期
    private String department;//就诊科室
    private String hospital;//就诊医院
    private String disease_info;//病历内容
    private String doctor_name;//医生名字

    private Long user_id;
    private int flag;

    private List<String> address;

    @Override
    public String toString() {
        return "OutPatient{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", department_treatment='" + department + '\'' +
                ", hospital='" + hospital + '\'' +
                ", disease_info='" + disease_info + '\'' +
                ", doctor_name='" + doctor_name + '\'' +
                ", user_id=" + user_id +
                ", flag=" + flag +
                ", address=" + address +
                '}';
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDisease_info() {
        return disease_info;
    }

    public void setDisease_info(String disease_info) {
        this.disease_info = disease_info;
    }


}
