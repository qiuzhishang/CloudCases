package com.xd.service;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xd.mapper.DoctorMapper;
import com.xd.mapper.PatientMapper;
import com.xd.mapper.RegisterMapper;
import com.xd.pojo.*;
import com.xd.utils.ResponseMessage;
import com.xd.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class PatientService {

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    public List<Doctor> selectAllDoctor() {


        List<Doctor> doctors = doctorMapper.selectAllDoctor();

        for (Doctor doctor : doctors)
            doctor.setAddress(doctorMapper.selectDoctorAddrInfo(doctor.getId()));
        
        return doctors;

    }


    //已经选择的医生
    public List<Long> selectedDoctorId(Long id){

        return doctorMapper.selectDoctorId(id, 1);

    }

    //患者完善个人信息
    public ResponseMessage patientInsertInfo(RequestMessage message){

        Patient user = patientMapper.selectPatientByIdNum(message.getPatient().getId_num());
        System.out.println(user);
        if (user != null){
            ResponseMessage response = new ResponseMessage();
            response.setStatus_code(0);//身份证信息已存在
            return response;
        }
        if ((message.getPatient().getSex() != 0 || message.getPatient().getSex() != 1) &&
                message.getPatient().getName()       !=null &&
                message.getPatient().getId_num()     !=null &&
                message.getPatient().getBirthplace() !=null &&
                message.getPatient().getEmerge()     !=null &&
                message.getPatient().getNow_addr()   !=null &&
                message.getPatient().getPostal_addr()!=null &&
                message.getPatient().getRace()       != null) {

            Long id = message.getUserId();
            message.getPatient().setUser_id(id);

            String date = message.getPatient().getBirthday();

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date sDate = null;
            try {
                java.util.Date date3 = sdf2.parse(date);
                sDate = new java.sql.Date(date3.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            patientMapper.insertPatientInfo(message.getPatient().getName(),
                    message.getPatient().getId_num(),
                    message.getPatient().getSex(),
                    message.getPatient().getRace(),
                    message.getPatient().getBirthplace(),
                    message.getPatient().getPostal_addr(),
                    message.getPatient().getNow_addr(),
                    message.getPatient().getPre_addr1(),
                    message.getPatient().getPre_addr2(),
                    message.getPatient().getUser_id(),
                    sDate);

            List<Emerge> emerge = message.getPatient().getEmerge();
            System.out.println(emerge);
            for (Emerge em : emerge) {
                patientMapper.insertEmergeContactInfo(em.getName(),em.getPhone_num(),id);
            }


            ResponseMessage response = new ResponseMessage();
            response.setStatus_code(1);//信息填入成功

            response.setName(message.getPatient().getName());
            response.setSex(message.getPatient().getSex());
            return response;
        }
        else {
            ResponseMessage response = new ResponseMessage();
            response.setStatus_code(2);//必填信息不全
            return response;
        }
    }

    //患者选择医生
    public ResponseMessage PatientSelectDoctor(RequestMessage message) {

        Register register = registerMapper.selectUserByUserId(message.getUserId());

        System.out.println(register);

        if (register == null){
            return null;
        }

        Long patient_id = register.getUser_id();

        System.out.println("patient id ========="+ patient_id);

        List<Long> defectList ;//差集List
        List<Long> collectionList;//交集List

        List<Long> doctors_id = message.getAdd_doctor_id();//获取的要添加的医生id
        System.out.println(doctors_id);

        List<Long> selected_id = doctorMapper.selectedDoctorId(patient_id);//已经存在列表的医生id


        if (selected_id.size() == 0) {
            for (Long doctor_id : doctors_id) {

                try {

                    System.out.println(doctor_id);
                    System.out.println("==============" + patient_id + "==================");

                    doctorMapper.insertPatientAndDoctor(patient_id, doctor_id, 1);
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        } else {

            //获取交集
            collectionList = Tools.receiveCollectionList(selected_id, doctors_id);
            System.out.println("-----------------------------");
            System.out.println(collectionList);
            System.out.println("-----------------------------");

            System.out.println("===================交集===================");
            System.out.println(collectionList);

            for (Long aLong : collectionList) {

                System.out.println("----"+ aLong + "------");

                System.out.println("==============" + patient_id);

                PatientAndDoctor patientAndDoctor = doctorMapper.selectExits(patient_id, aLong);

                doctorMapper.updateFlag(1, patientAndDoctor.getId());

            }


            //获取差集
            defectList = Tools.receiveDefectList(selected_id, collectionList);
            for (Long aLong : defectList) {

                System.out.println("----"+ aLong + "------");

                System.out.println("==============" + patient_id);

                PatientAndDoctor patientAndDoctor = doctorMapper.selectExits(patient_id, aLong);

                doctorMapper.updateFlag(0, patientAndDoctor.getId());

            }

            defectList = Tools.receiveDefectList(doctors_id, collectionList);

            for (Long aLong : defectList) {

                System.out.println("----"+ aLong + "------");

                doctorMapper.insertPatientAndDoctor(patient_id, aLong, 1);

            }
        }

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus_code(1);

        return responseMessage;

    }

    //患者病症信息
    public ResponseMessage PatientDiseaseInfo(RequestMessage message){

        Register user = registerMapper.selectUserByUserId(message.getUserId());
        ResponseMessage response = new ResponseMessage();

        //int insertDiseaseInfo(int disease_type, String disease_info, Long user_id);
        patientMapper.insertDiseaseInfo(message.getPatientDiseaseInfo().getDisease_type(), message.getPatientDiseaseInfo().getDisease_info(),message.getUserId());

        response.setStatus_code(1);
        return response;
    }
}
