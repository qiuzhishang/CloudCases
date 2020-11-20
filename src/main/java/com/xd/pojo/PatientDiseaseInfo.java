package com.xd.pojo;


public class PatientDiseaseInfo {
    private int id;
    private int disease_type;
    private String disease_info;
    private int flag;

    @Override
    public String toString() {
        return "PatientDiseaseInfo{" +
                "id=" + id +
                ", disease_type=" + disease_type +
                ", disease_info='" + disease_info + '\'' +
                ", flag=" + flag +
                '}';
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getDisease_type() {
        return disease_type;
    }

    public void setDisease_type(int disease_type) {
        this.disease_type = disease_type;
    }

    public String getDisease_info() {
        return disease_info;
    }

    public void setDisease_info(String disease_info) {
        this.disease_info = disease_info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
