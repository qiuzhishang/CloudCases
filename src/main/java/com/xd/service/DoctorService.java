package com.xd.service;


import com.xd.mapper.DoctorMapper;
import com.xd.mapper.DoctorSelectPatientTextInfo;
import com.xd.mapper.PatientUploadTextMapper;
import com.xd.pojo.*;
import com.xd.utils.AddressMethod;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private DoctorSelectPatientTextInfo doctorSelectPatientTextInfo;

    @Autowired
    private PatientUploadTextMapper patientUploadTextMapper;

    //医生上传个人图片信息
    public ResponseMessage DoctorInfo(List<MultipartFile> files, Doctor doctor, TextInfo info, List<Long> types){

        /*
         * 先插入个人信息，然后插入图片地址
         * doctor_info, doctor_addr_info*/

        Long user_id = info.getUserId();

        doctor.setUser_id(user_id);

        Doctor doc = doctorMapper.selectDoctorByUserId(user_id);

        String fileName;


        if (doc != null){
            System.out.println("=============" + files.size());

            Long doctor_id = doc.getId();

            int count = 0;
            int flag = 1;



            for (MultipartFile file : files) {

                fileName = file.getOriginalFilename();
                fileName = fileName.split("\\.")[0] + System.currentTimeMillis() + "."
                        + fileName.split("\\.")[1];
                File dest = new File(AddressMethod.GeneratorAddress(user_id, fileName));
                //存入数据库的路径path

                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    file.transferTo(dest);
                    String file_addr = AddressMethod.GeneratorAddressOut(user_id, fileName);

                    System.out.println(count);
                    //加一个地址select操作
                    DoctorAddr doctorAddr = doctorMapper.selectDoctorAddr(doctor_id, types.get(count));

                    if (doctorAddr == null){
                        doctorMapper.insertDoctorAddr(file_addr, types.get(count), doctor_id);
                        count++;
                    }

                    doctorMapper.updateDoctorAddr(file_addr, types.get(count), doctor_id);
                    count++;

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("上传失败" );
                    flag = 0;
                }
                System.out.println("上传成功");
            }
            ResponseMessage response = new ResponseMessage();
            if (flag == 1) {

                response.setStatus_code(1);
                response.setDoctor(doc);

                return response;
            } else {
                response.setStatus_code(0);
                return response;
            }
        }

        doctorMapper.insertDoctorInfo(doctor);

        doc = doctorMapper.selectDoctorByUserId(user_id);

        Long doctor_id = doc.getId();

        System.out.println(files.size());

        int count = 0;
        int flag = 1;

        for (MultipartFile file : files) {

            fileName = file.getOriginalFilename();

            fileName = fileName.split("\\.")[0] + System.currentTimeMillis() + "."
                    + fileName.split("\\.")[1];
            File dest = new File(AddressMethod.GeneratorAddress(user_id, fileName));
            //存入数据库的路径path

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                String file_addr = AddressMethod.GeneratorAddressOut(user_id, fileName);

                doctorMapper.insertDoctorAddr(file_addr, types.get(count++), doctor_id);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传失败" );
                flag = 0;
            }
            System.out.println("上传成功");
        }
        ResponseMessage response = new ResponseMessage();
        if (flag == 1) {

            response.setStatus_code(1);
            response.setDoctor(doc);
            return response;
        } else {
            response.setStatus_code(0);

            return response;
        }

    }

    //医生查看个人信息
    public ResponseMessage DoctorPersonalInfo(RequestMessage message){

        ResponseMessage responseMessage = new ResponseMessage();

        Doctor doctor = doctorMapper.selectDoctorInfo(message.getUserId());

        doctor.setAddress(doctorMapper.selectDoctorAddrInfo(doctor.getId()));

        responseMessage.setStatus_code(1);
        responseMessage.setDoctor(doctor);

        return responseMessage;
    }


    //医生查看跟自己已经关联的患者的个人资料信息（模糊查询）
    public ResponseMessage DoctorWatchPatient(RequestMessage message) {

        ResponseMessage responseMessage = new ResponseMessage();
        List<Patient> patients;

        if (message.getWatchPatientsInfo()==null)
             patients = doctorMapper.selectPatients(message.getUserId(),
                    null,
                    null,
                    null);
        else

            //Long doctor_id,  String name, String id_num, String phone_num
             patients = doctorMapper.selectPatients(message.getUserId(),
                    message.getWatchPatientsInfo().getName(),
                    message.getWatchPatientsInfo().getId_num(),
                    message.getWatchPatientsInfo().getPhone_num());

        responseMessage.setPatients(patients);
        responseMessage.setStatus_code(1);

        return responseMessage;


    }


    //医生查看跟自己已经关联的患者的其他病症信息
    public ResponseMessage WatchPatientHospitalInfo(RequestMessage message){

        Long patientId = message.getPatient().getUser_id();

        List<AdmissionNote> admissionNotes = doctorSelectPatientTextInfo.doctorSelectAdmissionNote(patientId);
        List<OutPatient> outPatients = doctorSelectPatientTextInfo.doctorSelectOutPatient(patientId);
        List<OutPatientRecords> outPatientRecords = doctorSelectPatientTextInfo.doctorSelectOutPatientRecords(patientId);
        for (OutPatientRecords outPatientRecord : outPatientRecords) {
            outPatientRecord.setMedicines(patientUploadTextMapper.selectMedicine(outPatientRecord.getId()));
        }
        List<Examine> examines = doctorSelectPatientTextInfo.doctorSelectExamine(patientId);
        List<DiseasePicture> diseasePictures = doctorSelectPatientTextInfo.doctorSelectDiseasePicture(patientId);
        List<Report> reports = doctorSelectPatientTextInfo.doctorSelectMedicalExaminationReportId(patientId);
        List<LaboratoryPicture> laboratoryPictures = doctorSelectPatientTextInfo.doctorSelectLaboratoryExaminationId(patientId);
        List<ImagePicture> imagePictures = doctorSelectPatientTextInfo.doctorSelectImageExaminationId(patientId);
        List<InstrumentPicture> instrumentPictures = doctorSelectPatientTextInfo.doctorSelectInvasiveInstrumentsId(patientId);
        List<PatientDiseaseInfo> patientDiseaseInfos = doctorSelectPatientTextInfo.doctorSelectPatientDiseaseInfo(patientId);

        ResponseMessage responseMessage = new ResponseMessage();

        responseMessage.setAdmissionNotes(admissionNotes);
        responseMessage.setOutPatients(outPatients);
        responseMessage.setOutPatientRecords(outPatientRecords);
        responseMessage.setExamines(examines);
        responseMessage.setDiseasePictures(diseasePictures);
        responseMessage.setReports(reports);
        responseMessage.setLaboratoryPictures(laboratoryPictures);
        responseMessage.setImagePictures(imagePictures);
        responseMessage.setInstrumentPictures(instrumentPictures);
        responseMessage.setPatientDiseaseInfoList(patientDiseaseInfos);

        return responseMessage;
    }

    //包含图片的病症的详细信息
    public ResponseMessage DoctorWatchPatientSomeInfo(RequestMessage message){

        ResponseMessage responseMessage = new ResponseMessage();

        //Report
        if (message.getReport() != null){

            Report report = doctorMapper.selectReport(message.getReport().getId());

            report.setAddress(doctorMapper.selectPatientReport(message.getReport().getId()));;
            List<Report> reports = new ArrayList<>();
            reports.add(report);
            responseMessage.setReports(reports);

        }

        //Laboratory
        if (message.getLaboratoryPicture() != null ){

            LaboratoryPicture laboratoryPicture = doctorMapper.selectLaboratoryInfo(message.getLaboratoryPicture().getId());

            laboratoryPicture.setAddress(doctorMapper.selectLaboratoryAddrInfo(message.getLaboratoryPicture().getId()));

            List<LaboratoryPicture> laboratoryPictures = new ArrayList<>();
            laboratoryPictures.add(laboratoryPicture);

            responseMessage.setLaboratoryPictures(laboratoryPictures);
        }

        //instrument
        if (message.getInstrumentPicture() != null){

            InstrumentPicture instrumentPicture = doctorMapper.selectInstrumentInfo(message.getInstrumentPicture().getId());
            instrumentPicture.setAddress(doctorMapper.selectInstrumentAddrInfo(message.getInstrumentPicture().getId()));

            List<InstrumentPicture> instrumentPictures = new ArrayList<>();

            instrumentPictures.add(instrumentPicture);

            responseMessage.setInstrumentPictures(instrumentPictures);

        }

        //Image
        if (message.getImagePicture() != null){

            ImagePicture imagePicture = doctorMapper.selectImageInfo(message.getImagePicture().getId());
            imagePicture.setAddress(doctorMapper.selectImageAddrInfo(message.getImagePicture().getId() ));

            List<ImagePicture> imagePictures = new ArrayList<>();

            imagePictures.add(imagePicture);

            responseMessage.setImagePictures(imagePictures);
        }

        //DiseasePicture
        if (message.getDiseasePicture() != null){

            DiseasePicture diseasePicture = doctorMapper.selectDiseasePictureInfo(message.getDiseasePicture().getId());
            diseasePicture.setAddress(doctorMapper.selectDiseasePictureAddrInfo(message.getDiseasePicture().getId()));

            List<DiseasePicture> diseasePictures = new ArrayList<>();

            diseasePictures.add(diseasePicture);

            responseMessage.setDiseasePictures(diseasePictures);
        }

        //outpatient
        if (message.getOutPatient() != null){

            OutPatient outPatient = doctorMapper.selectOutPatientInfo(message.getOutPatient().getId());
            outPatient.setAddress(doctorMapper.selectOutPatientAddrInfo(message.getOutPatient().getId()));

            List<OutPatient> outPatients = new ArrayList<>();

            outPatients.add(outPatient);

            responseMessage.setOutPatients(outPatients);
        }

        return responseMessage;

    }
}