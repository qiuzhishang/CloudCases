package com.xd.pojo;

//住院病历
public class AdmissionNote {
    private String s_date;//开始日期
    private String o_date;//结束日期
    private String department_treatment;//就诊科室
    private String hospital;//医院名称
    private String doctor_name;//医生姓名
    private String admission_info;//住院病历内容

    public String getS_date() {
        return s_date;
    }

    public void setS_date(String s_date) {
        this.s_date = s_date;
    }

    public String getO_date() {
        return o_date;
    }

    public void setO_date(String o_date) {
        this.o_date = o_date;
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

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getAdmission_info() {
        return admission_info;
    }

    public void setAdmission_info(String admission_info) {
        this.admission_info = admission_info;
    }

    @Override
    public String toString() {
        return "AdmissionNote{" +
                "s_date='" + s_date + '\'' +
                ", o_date='" + o_date + '\'' +
                ", department='" + department_treatment + '\'' +
                ", hospital='" + hospital + '\'' +
                ", doctor_name='" + doctor_name + '\'' +
                ", admission_info='" + admission_info + '\'' +
                '}';
    }
}
