package com.xd.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//门诊记录
public class OutPatientRecords {
    private Long id;
    private Date date;//日期
    private String department_treatment;//就诊科室
    private String hospital;//就诊医院
    private String disease_info;//诊断内容
    private String doctor_name;//医生名字
    private String treat_info;//诊断内容
    private String treating_info;//就诊内容
    private String treat_items;//检查项目
    private String treat_methods;//非药物治疗措施
    private List<Medicine> medicines;//用药列表

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date){
        this.date = date;
    }


    public void setDate(String date) {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sDate = null;
        try {
            java.util.Date date3 = sdf2.parse(date);
            sDate = new java.sql.Date(date3.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.date = sDate;
    }

    public String getDepartment_treatment() {
        return department_treatment;
    }

    public void setDepartment_treatment(String department_treatment) {
        this.department_treatment = department_treatment;
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

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getTreat_info() {
        return treat_info;
    }

    public void setTreat_info(String treat_info) {
        this.treat_info = treat_info;
    }

    public String getTreating_info() {
        return treating_info;
    }

    public void setTreating_info(String treating_info) {
        this.treating_info = treating_info;
    }

    public String getTreat_items() {
        return treat_items;
    }

    public void setTreat_items(String treat_items) {
        this.treat_items = treat_items;
    }

    public String getTreat_methods() {
        return treat_methods;
    }

    public void setTreat_methods(String treat_methods) {
        this.treat_methods = treat_methods;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    @Override
    public String toString() {
        return "TreatmentRecords{" +
                "date='" + date + '\'' +
                ", department_treatment='" + department_treatment + '\'' +
                ", hospital='" + hospital + '\'' +
                ", disease_info='" + disease_info + '\'' +
                ", doctor_name='" + doctor_name + '\'' +
                ", treat_info='" + treat_info + '\'' +
                ", treating_info='" + treating_info + '\'' +
                ", treat_items='" + treat_items + '\'' +
                ", treat_methods='" + treat_methods + '\'' +
                ", medicines=" + medicines +
                '}';
    }
}
