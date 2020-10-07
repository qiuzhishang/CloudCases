package com.xd.service;

import com.xd.mapper.TextMapper;
import com.xd.mapper.UserInfoMapper;
import com.xd.pojo.*;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TextService {

    @Autowired
    TextMapper textMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    //门诊病历
    public ResponseMessage OutpatientMedicalRecords(RequestMessage message){


        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();
        String date = message.getOutPatient().getDate();

        //java String 转sql日期
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = null;
        try {
            java.util.Date date3 = sdf2.parse(date);
            sDate = new Date(date3.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //String Date date, String department_treatment, String hospital,                                    String doctor_name, long user_id, String phone_num
        textMapper.insertOutpatient(message.getOutPatient().getDepartment_treatment(),
                message.getOutPatient().getHospital(),
                message.getOutPatient().getDoctor_name(),
                message.getOutPatient().getDisease_info(),
                sDate, user_id);
        ResponseMessage response = new ResponseMessage();
        if (user != null){
            response.setStatus_code(1);
            return response;
        }
        else{
            response.setStatus_code(0);
            return response;
        }

    }

    //查询门诊病历
    public List<OutPatient> SelectOutPatient(RequestMessage message){
        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();

        List<OutPatient> outPatients;
        outPatients = textMapper.selectOutPatient(user_id);

        return outPatients;

    }


    //住院病历
    public ResponseMessage AdmissionNote(RequestMessage message){
        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();
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

        textMapper.insertAdmission(message.getAdmissionNote().getDepartment_treatment(), message.getAdmissionNote().getHospital(),
                message.getAdmissionNote().getDoctor_name(),
                message.getAdmissionNote().getAdmission_info() ,Sdate, Odate,user_id);

        ResponseMessage response = new ResponseMessage();
        response.setStatus_code(1);
        return response;
    }

    //查询住院病历
    public List<AdmissionNote> SelectAdmissionNote(RequestMessage message){

        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();

        List<AdmissionNote> admissionNotes;

        admissionNotes = textMapper.selectAdmissionNote(user_id);

        return admissionNotes;

    }

    //门诊记录
    public ResponseMessage OutpatientRecords(RequestMessage message){
        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();
        message.getOutPatientRecords().setUser_id(user_id);

        textMapper.insertOutPatientRecords(
                message.getOutPatientRecords().getDepartment_treatment(),
                message.getOutPatientRecords().getHospital(),
                message.getOutPatientRecords().getDisease_info(),
                message.getOutPatientRecords().getDoctor_name(),
                message.getOutPatientRecords().getTreat_info(),
                message.getOutPatientRecords().getTreating_info(),
                message.getOutPatientRecords().getTreat_items(),
                message.getOutPatientRecords().getTreat_methods(),
                message.getOutPatientRecords().getDate(),
                message.getOutPatientRecords().getUser_id()
        );

        System.out.println(message.getOutPatientRecords().toString());
//        textMapper.insertOutPatientRecords(message.getOutPatientRecords(),user_id);

        List<OutPatientRecords> outpatientRecords = textMapper.selectOutPatientRecords(user_id);
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
        for (Medicine medicine : medicines) {

            textMapper.insertMedicine(medicine.getMedicine_name(),medicine.getMedicine_name(),medicine.getTime(), max);

        }

        ResponseMessage response = new ResponseMessage();
        response.setStatus_code(1);
        return response;

    }

    //查询门诊记录
    public List<OutPatientRecords> SelectOutpatientRecords(RequestMessage message){
        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();

        List<OutPatientRecords> outPatientRecords;

        outPatientRecords = textMapper.selectOutPatientRecords(user_id);

        for (OutPatientRecords outPatientRecord : outPatientRecords) {
            try {
                Long treat_id = outPatientRecord.getId();

                List<Medicine> medicines = textMapper.selectMedicine(treat_id);
                System.out.println("================"+ treat_id);
                outPatientRecord.setMedicines(medicines);

            }catch (Exception e){
                e.printStackTrace();
            }


        }

        return  outPatientRecords;
    }


    //病理学检查
    public ResponseMessage DiseaseExamine(RequestMessage message){

        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();

        textMapper.insertExamineInfo(message.getExamine().getExamine_info(), user_id);

        ResponseMessage response = new ResponseMessage();
        response.setStatus_code(1);

        return response;
    }

    //查询病理学检查
    public List<Examine> SelectDiseaseExamine(RequestMessage message){
        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();

        List<Examine> examines;
        examines = textMapper.selectExamine(user_id);

        return examines;
    }
}

