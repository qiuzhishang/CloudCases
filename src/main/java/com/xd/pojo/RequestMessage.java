package com.xd.pojo;

import java.util.List;

public class RequestMessage {

    private int user_type;

    private int call_type;

    private Long id;
    private String table_name;
    private int flag;
    private String content;

    private Sign sign;
    private Register register;

    private String token;
    private String phone_num;
    private Long userId;

    private Report report;
    private Examine examine;
    private Patient patient;
    private OutPatient outPatient;
    private AdmissionNote admissionNote;
    private DiseasePicture diseasePicture;
    private OutPatientRecords outPatientRecords;
    private PatientDiseaseInfo patientDiseaseInfo;

    private LaboratoryPicture laboratoryPicture;
    private InstrumentPicture instrumentPicture;
    private ImagePicture imagePicture;

    private List<Long> add_doctor_id;
    private List<Long> remove_doctor_id;

    private Doctor doctor;

    private WatchPatientsInfo watchPatientsInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "user_type=" + user_type +
                ", call_type=" + call_type +
                ", id=" + id +
                ", table_name='" + table_name + '\'' +
                ", flag=" + flag +
                ", content='" + content + '\'' +
                ", sign=" + sign +
                ", register=" + register +
                ", token='" + token + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", userId=" + userId +
                ", report=" + report +
                ", examine=" + examine +
                ", patient=" + patient +
                ", outPatient=" + outPatient +
                ", admissionNote=" + admissionNote +
                ", diseasePicture=" + diseasePicture +
                ", outPatientRecords=" + outPatientRecords +
                ", patientDiseaseInfo=" + patientDiseaseInfo +
                ", laboratoryPicture=" + laboratoryPicture +
                ", instrumentPicture=" + instrumentPicture +
                ", imagePicture=" + imagePicture +
                ", add_doctor_id=" + add_doctor_id +
                ", remove_doctor_id=" + remove_doctor_id +
                ", doctor=" + doctor +
                ", watchPatientsInfo=" + watchPatientsInfo +
                '}';
    }

    public int getCall_type() {
        return call_type;
    }

    public void setCall_type(int call_type) {
        this.call_type = call_type;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public ImagePicture getImagePicture() {
        return imagePicture;
    }

    public void setImagePicture(ImagePicture imagePicture) {
        this.imagePicture = imagePicture;
    }


    public InstrumentPicture getInstrumentPicture() {
        return instrumentPicture;
    }

    public void setInstrumentPicture(InstrumentPicture instrumentPicture) {
        this.instrumentPicture = instrumentPicture;
    }

    public LaboratoryPicture getLaboratoryPicture() {
        return laboratoryPicture;
    }

    public void setLaboratoryPicture(LaboratoryPicture laboratoryPicture) {
        this.laboratoryPicture = laboratoryPicture;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public WatchPatientsInfo getWatchPatientsInfo() {
        return watchPatientsInfo;
    }

    public void setWatchPatientsInfo(WatchPatientsInfo watchPatientsInfo) {
        this.watchPatientsInfo = watchPatientsInfo;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
