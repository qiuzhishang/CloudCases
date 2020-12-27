package com.xd.service;

import com.xd.mapper.PatientUploadTextMapper;
import com.xd.pojo.*;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TextService {

    @Autowired
    PatientUploadTextMapper patientUploadTextMapper;

    // //门诊病历
    // public ResponseMessage outPatientMedicalRecords(RequestMessage message){
    //
    //     Long user_id = message.getUserId();
    //     String date = message.getOutPatient().getDate();
    //
    //     //java String 转sql日期
    //     SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    //     Date sDate = null;
    //     try {
    //         java.util.Date date3 = sdf2.parse(date);
    //         sDate = new Date(date3.getTime());
    //     } catch (ParseException e) {
    //         e.printStackTrace();
    //     }
    //     //String Date date, String department_treatment, String hospital,                                    String doctor_name, long user_id, String phone_num
    //     patientUploadTextMapper.insertOutpatient(message.getOutPatient().getDepartment(),
    //             message.getOutPatient().getHospital(),
    //             message.getOutPatient().getDoctor_name(),
    //             message.getOutPatient().getDisease_info(),
    //             sDate, user_id);
    //
    //     ResponseMessage response = new ResponseMessage();
    //
    //     response.setStatus_code(1);
    //
    //     return response;
    // }




    //住院病历
    public ResponseMessage admissionNote(RequestMessage message){

        Long user_id = message.getUserId();
        String date1 = message.getAdmissionNote().getS_date();
        String date2 = message.getAdmissionNote().getO_date();
        //java String 转sql日期
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date Sdate = null;
        Date Odate = null;
        //起始日期
        try {
            java.util.Date date3 = sdf2.parse(date1);
            Sdate = new Date(date3.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //java String 转sql日期
        try {
            java.util.Date date3 = sdf2.parse(date2);
            Odate = new Date(date3.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patientUploadTextMapper.insertAdmission(message.getAdmissionNote().getDepartment_treatment(), message.getAdmissionNote().getHospital(),
                message.getAdmissionNote().getDoctor_name(),
                message.getAdmissionNote().getAdmission_info() ,Sdate, Odate,user_id);

        ResponseMessage response = new ResponseMessage();
        response.setStatus_code(1);
        return response;
    }

    //查询住院病历
    public List<AdmissionNote> selectAdmissionNote(RequestMessage message){

        Long user_id = message.getUserId();

        return patientUploadTextMapper.selectAdmissionNote(user_id);

    }

    //门诊记录
    public ResponseMessage outPatientRecords(RequestMessage message){

        Long user_id = message.getUserId();

        message.getOutPatientRecords().setUser_id(user_id);

        String date = message.getOutPatientRecords().getDate();

        //java String 转sql日期
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = null;
        try {
            java.util.Date date3 = sdf2.parse(date);
            sDate = new Date(date3.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patientUploadTextMapper.insertOutPatientRecords(
                message.getOutPatientRecords().getDepartment_treatment(),
                message.getOutPatientRecords().getHospital(),
                message.getOutPatientRecords().getDisease_info(),
                message.getOutPatientRecords().getDoctor_name(),
                message.getOutPatientRecords().getTreat_info(),
                message.getOutPatientRecords().getTreating_info(),
                message.getOutPatientRecords().getTreat_items(),
                message.getOutPatientRecords().getTreat_methods(),
                sDate,
                message.getOutPatientRecords().getUser_id()
        );

        System.out.println(message.getOutPatientRecords().toString());
//        textMapper.insertOutPatientRecords(message.getOutPatientRecords(),user_id);

        List<OutPatientRecords> outpatientRecords = patientUploadTextMapper.selectOutPatientRecords(user_id);
        Long max = 0L;
        for (OutPatientRecords outpatientRecord : outpatientRecords) {
            if(max < outpatientRecord.getId())
                max = outpatientRecord.getId();
        }
        List<Medicine> medicines = message.getOutPatientRecords().getMedicines();
        if (medicines == null){
            ResponseMessage response = new ResponseMessage();
            response.setStatus_code(1);
            return response;
        }
        for (Medicine medicine : medicines)

            patientUploadTextMapper.insertMedicine(medicine.getMedicine_name(),medicine.getMedicine_name(),medicine.getTime(), max);


        ResponseMessage response = new ResponseMessage();
        response.setStatus_code(1);
        return response;

    }

    //查询门诊记录
    public List<OutPatientRecords> selectOutPatientRecords(RequestMessage message){

        Long user_id = message.getUserId();

        List<OutPatientRecords> outPatientRecords;

        outPatientRecords = patientUploadTextMapper.selectOutPatientRecords(user_id);

        for (OutPatientRecords outPatientRecord : outPatientRecords) {
            try {
                Long treat_id = outPatientRecord.getId();

                List<Medicine> medicines = patientUploadTextMapper.selectMedicine(treat_id);
                System.out.println("================"+ treat_id);
                outPatientRecord.setMedicines(medicines);

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return  outPatientRecords;
    }


    //病理学检查
    public ResponseMessage diseaseExamine(RequestMessage message){

        Long user_id = message.getUserId();

        patientUploadTextMapper.insertExamineInfo(message.getExamine().getExamine_info(), user_id);

        ResponseMessage response = new ResponseMessage();
        response.setStatus_code(1);

        return response;
    }

    //查询病理学检查
    public List<Examine> selectDiseaseExamine(RequestMessage message){

        Long user_id = message.getUserId();

        return patientUploadTextMapper.selectExamine(user_id);
    }
}

