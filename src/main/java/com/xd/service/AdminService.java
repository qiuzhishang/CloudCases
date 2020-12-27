package com.xd.service;

import com.xd.mapper.*;
import com.xd.pojo.*;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private PatientUploadTextMapper patientUploadTextMapper;

    public ResponseMessage AdminSign(RequestMessage message){

        Sign signInfo = message.getSign();

        ResponseMessage responseMessage = new ResponseMessage();

        if (signInfo.getPhone_num().equals("admin")&&signInfo.getPass_word().equals("123456XXX")){

            responseMessage.setStatus_code(100);

            //住院病历
            responseMessage.setAdmissionNotes(adminMapper.selectAllAdmission());
            //病理学检查
            responseMessage.setExamines(adminMapper.selectAllExamine());
            //既往病史
            responseMessage.setPatientDiseaseInfoList(adminMapper.selectAllDiseaseInfo());

            //门诊记录
            List<OutPatientRecords> outPatientRecords = adminMapper.selectAllOutPatientRecords();
            for (OutPatientRecords outPatientRecord : outPatientRecords) {
                outPatientRecord.setMedicines(patientUploadTextMapper.selectMedicine(outPatientRecord.getId()));
            }
            responseMessage.setOutPatientRecords(outPatientRecords);



            //病症图片
            List<DiseasePicture> diseasePictures = adminMapper.selectAllDiseasePicture();
            for (DiseasePicture diseasePicture : diseasePictures) {
                diseasePicture.setAddress(adminMapper.selectAllDiseasePictureAddr(diseasePicture.getId()));
            }
            responseMessage.setDiseasePictures(diseasePictures);

            List<Doctor> doctors = adminMapper.selectAllDoctor();
            for (Doctor doctor : doctors) {
                doctor.setAddress(adminMapper.selectAllDoctorAddrInfo(doctor.getId()));
            }
            responseMessage.setDoctors(doctors);

            //影像检查
            List<ImagePicture> imagePictures = adminMapper.selectAllImage();
            for (ImagePicture imagePicture : imagePictures) {
                imagePicture.setAddress(adminMapper.selectAllImageAddr(imagePicture.getId()));
            }
            responseMessage.setImagePictures(imagePictures);

            //侵入式器械
            List<InstrumentPicture> instrumentPictures = adminMapper.selectAllInstrumentPicture();
            for (InstrumentPicture instrumentPicture : instrumentPictures) {
                instrumentPicture.setAddress(adminMapper.selectAllInstrumentAddr(instrumentPicture.getId()));
            }
            responseMessage.setInstrumentPictures(instrumentPictures);

            //化验检查
            List<LaboratoryPicture> laboratoryPictures = adminMapper.SelectAllLaboratoryPicture();
            for (LaboratoryPicture laboratoryPicture : laboratoryPictures) {
                laboratoryPicture.setAddress(adminMapper.selectAllLaboratoryAddr(laboratoryPicture.getId()));
            }
            responseMessage.setLaboratoryPictures(laboratoryPictures);

            //门诊病历
            List<OutPatient> outPatients = adminMapper.selectAllOutPatient();
            for (OutPatient outPatient : outPatients) {
                outPatient.setAddress(adminMapper.selectAllOutPatientAddr(outPatient.getId()));
            }
            responseMessage.setOutPatients(outPatients);

            //体检报告
            List<Report> reports = adminMapper.selectAllReport();
            for (Report report : reports) {
                report.setAddress(adminMapper.selectAllReportAddr(report.getId()));
            }
            responseMessage.setReports(reports);




        }else{

            responseMessage.setStatus_code(200);

        }


        return responseMessage;
    }

    public ResponseMessage Admin(RequestMessage message){

        ResponseMessage responseMessage = new ResponseMessage();

        System.out.println(message);

        //更新admission
        adminMapper.updateAdmission(message.getTable_name(),message.getFlag(),message.getContent(),message.getId());
        // adminMapper.updateAdmission("admission_info",10,"asdada",3L);


        return responseMessage;

    }
}
