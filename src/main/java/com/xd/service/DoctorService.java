package com.xd.service;


import com.xd.mapper.DoctorMapper;
import com.xd.mapper.DoctorSelectPatientTextInfo;
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

    //医生上传个人图片信息
    public ResponseMessage DoctorInfo(List<MultipartFile> files, Doctor doctor, TextInfo info, List<Long> types){

        /*
         * 先插入个人信息，然后插入图片地址
         * doctor_info, doctor_addr_info*/

        Long user_id = info.getUserId();

        doctor.setUser_id(user_id);

        Doctor doc = doctorMapper.selectDoctorByUserId(user_id);
        Doctor doctor1 = doctorMapper.selectDoctorByIdNum(doctor.getId_num());

        String fileName;


        if (doc != null || doctor1 != null){
            System.out.println(files.size());

            Long doctor_id = doc.getId();
            ResponseMessage responseMessage = new ResponseMessage();

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
                return response;
            } else {
                response.setStatus_code(0);
                return response;
            }
        }

        doctorMapper.insertDoctorInfo(doctor);

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


                doctorMapper.insertDoctorAddr(file_addr, types.get(count), doctor_id);

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

        //Long doctor_id,  String name, String id_num, String phone_num
        List<Patient> patients = doctorMapper.selectPatients(message.getUserId(),
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
        List<Examine> examines = doctorSelectPatientTextInfo.doctorSelectExamine(patientId);
        List<DiseasePicture> diseasePictures = doctorSelectPatientTextInfo.doctorSelectDiseasePicture(patientId);
        List<Report> reports = doctorSelectPatientTextInfo.doctorSelectMedicalExaminationReportId(patientId);
        List<LaboratoryPicture> laboratoryPictures = doctorSelectPatientTextInfo.doctorSelectLaboratoryExaminationId(patientId);
        List<ImagePicture> imagePictures = doctorSelectPatientTextInfo.doctorSelectImageExaminationId(patientId);
        List<InstrumentPicture> instrumentPictures = doctorSelectPatientTextInfo.doctorSelectInvasiveInstrumentsId(patientId);

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

        return responseMessage;
    }
}