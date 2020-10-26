package com.xd.pojo;

import java.util.List;

public class RequestMessage {

    private int user_type;

    private Sign sign;

    private String token;
    private String phone_num;

    private Report report;
    private Examine examine;
    private Patient patient;
    private OutPatient outPatient;
    private AdmissionNote admissionNote;
    private DiseasePicture diseasePicture;
    private OutPatientRecords outPatientRecords;
    private PatientDiseaseInfo patientDiseaseInfo;

    private List<Long> add_doctor_id;
    private List<Long> remove_doctor_id;

    private Doctor doctor;

    @Override
    public String toString() {
        return "RequestMessage{" +
                "phone_num='" + phone_num + '\'' +
                ", call_type=" + user_type +
                ", diseasePicture=" + diseasePicture +
                ", token='" + token + '\'' +
                ", sign=" + sign +
                ", patient=" + patient +
                ", report=" + report +
                ", patientDiseaseInfo=" + patientDiseaseInfo +
                ", outPatient=" + outPatient +
                ", admissionNote=" + admissionNote +
                ", outPatientRecords=" + outPatientRecords +
                ", examine=" + examine +
                ", add_doctor_id=" + add_doctor_id +
                ", remove_doctor_id=" + remove_doctor_id +
                ", doctor=" + doctor +
                '}';
    }

    public Examine getExamine() {
        return examine;
    }

    public void setExamine(Examine examine) {
        this.examine = examine;
    }



    public List<Long> getRemove_doctor_id() {
        return remove_doctor_id;
    }

    public void setRemove_doctor_id(List<Long> remove_doctor_id) {
        this.remove_doctor_id = remove_doctor_id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Long> getAdd_doctor_id() {
        return add_doctor_id;
    }

    public void setAdd_doctor_id(List<Long> add_doctor_id) {
        this.add_doctor_id = add_doctor_id;
    }



    public OutPatientRecords getOutPatientRecords() {
        return outPatientRecords;
    }

    public void setOutPatientRecords(OutPatientRecords outPatientRecords) {
        this.outPatientRecords = outPatientRecords;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public AdmissionNote getAdmissionNote() {
        return admissionNote;
    }

    public void setAdmissionNote(AdmissionNote admissionNote) {
        this.admissionNote = admissionNote;
    }

    public OutPatient getOutPatient() {
        return outPatient;
    }

    public void setOutPatient(OutPatient outPatient) {
        this.outPatient = outPatient;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public DiseasePicture getDiseasePicture() {
        return diseasePicture;
    }

    public void setDiseasePicture(DiseasePicture diseasePicture) {
        this.diseasePicture = diseasePicture;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public PatientDiseaseInfo getPatientDiseaseInfo() {
        return patientDiseaseInfo;
    }

    public void setPatientDiseaseInfo(PatientDiseaseInfo patientDiseaseInfo) {
        this.patientDiseaseInfo = patientDiseaseInfo;
    }

    // public List<PatientDiseaseInfo> getPatientDiseaseInfoList() {
    //     return patientDiseaseInfoList;
    // }
    //
    // public void setPatientDiseaseInfoList(List<PatientDiseaseInfo> patientDiseaseInfoList) {
    //     this.patientDiseaseInfoList = patientDiseaseInfoList;
    // }

}
