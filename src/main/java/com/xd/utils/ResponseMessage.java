package com.xd.utils;

import com.xd.pojo.*;

import java.util.List;

public class ResponseMessage {

    private int status_code;
    private String token;
    private String name;
    private int sex;
    private List<PatientDiseaseInfo> patientDiseaseInfoList;
    private Patient patient;
    private List<TextInfo> textInfo;
    private List<DiseasePicture> diseasePictures;
    private int user_type;
    private List<LaboratoryPicture> laboratoryPictures;
    private List<ImagePicture> imagePictures;
    private List<InstrumentPicture> instrumentPictures;
    private List<Doctor> doctors;
    private Doctor doctor;

    private List<AdmissionNote> admissionNotes;
    private List<Examine> examines;
    private List<OutPatient> outPatients;
    private List<OutPatientRecords> outPatientRecords;

    private List<DoctorMessage> doctorMessages;

    private List<Long> selected_doctor_id;

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "status_code=" + status_code +
                ", token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", patientDiseaseInfoList=" + patientDiseaseInfoList +
                ", patient=" + patient +
                ", textInfo=" + textInfo +
                ", diseasePictures=" + diseasePictures +
                ", user_type=" + user_type +
                ", laboratoryPictures=" + laboratoryPictures +
                ", imagePictures=" + imagePictures +
                ", instrumentPictures=" + instrumentPictures +
                ", doctors=" + doctors +
                ", doctor=" + doctor +
                ", admissionNotes=" + admissionNotes +
                ", examines=" + examines +
                ", outPatients=" + outPatients +
                ", outPatientRecords=" + outPatientRecords +
                ", doctorMessages=" + doctorMessages +
                ", selected_doctor_id=" + selected_doctor_id +
                '}';
    }

    public List<Long> getSelected_doctor_id() {
        return selected_doctor_id;
    }

    public void setSelected_doctor_id(List<Long> selected_doctor_id) {
        this.selected_doctor_id = selected_doctor_id;
    }

    public List<DoctorMessage> getDoctorMessages() {
        return doctorMessages;
    }

    public void setDoctorMessages(List<DoctorMessage> doctorMessages) {
        this.doctorMessages = doctorMessages;
    }

    public List<AdmissionNote> getAdmissionNotes() {
        return admissionNotes;
    }

    public void setAdmissionNotes(List<AdmissionNote> admissionNotes) {
        this.admissionNotes = admissionNotes;
    }

    public List<Examine> getExamines() {
        return examines;
    }

    public void setExamines(List<Examine> examines) {
        this.examines = examines;
    }

    public List<OutPatient> getOutPatients() {
        return outPatients;
    }

    public void setOutPatients(List<OutPatient> outPatients) {
        this.outPatients = outPatients;
    }

    public List<OutPatientRecords> getOutPatientRecords() {
        return outPatientRecords;
    }

    public void setOutPatientRecords(List<OutPatientRecords> outPatientRecords) {
        this.outPatientRecords = outPatientRecords;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<InstrumentPicture> getInstrumentPictures() {
        return instrumentPictures;
    }

    public void setInstrumentPictures(List<InstrumentPicture> instrumentPictures) {
        this.instrumentPictures = instrumentPictures;
    }

    public List<ImagePicture> getImagePictures() {
        return imagePictures;
    }

    public void setImagePictures(List<ImagePicture> imagePictures) {
        this.imagePictures = imagePictures;
    }

    public List<LaboratoryPicture> getLaboratoryPictures() {
        return laboratoryPictures;
    }

    public void setLaboratoryPictures(List<LaboratoryPicture> laboratoryPictures) {
        this.laboratoryPictures = laboratoryPictures;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public List<DiseasePicture> getDiseasePictures() {
        return diseasePictures;
    }

    public void setDiseasePictures(List<DiseasePicture> diseasePictures) {
        this.diseasePictures = diseasePictures;
    }

    public List<TextInfo> getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(List<TextInfo> textInfo) {
        this.textInfo = textInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public List<PatientDiseaseInfo> getPatientDiseaseInfoList() {
        return patientDiseaseInfoList;
    }

    public void setPatientDiseaseInfoList(List<PatientDiseaseInfo> patientDiseaseInfoList) {
        this.patientDiseaseInfoList = patientDiseaseInfoList;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

}
