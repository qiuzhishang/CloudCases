package com.xd.service;


import com.xd.mapper.DoctorMapper;
import com.xd.mapper.UserInfoMapper;
import com.xd.pojo.Doctor;
import com.xd.pojo.PatientAndDoctor;
import com.xd.pojo.Register;
import com.xd.pojo.RequestMessage;
import com.xd.utils.DoctorMessage;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DoctorService {

    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    public List<Doctor> selectAllDoctor() {
        List<Doctor> doctors = doctorMapper.selectAllDoctor();

        return doctors;

    }

    //医生信息
    public ResponseMessage doctorInsertInfo(RequestMessage message) {
        Register users = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Doctor doctor = message.getDoctor();

        Doctor user = doctorMapper.selectDoctorByIdNum(message.getDoctor().getId_num());
        ResponseMessage responseMessage = new ResponseMessage();
        if (user != null) {
            responseMessage.setStatus_code(0);
            return responseMessage;
        }

        doctor.setUser_id(users.getId());
        doctorMapper.insertDoctorInfo(doctor);

        responseMessage.setDoctor(doctor);
        responseMessage.setStatus_code(1);
        return responseMessage;
    }


    public ResponseMessage PatientSelectDoctor(RequestMessage message) {

        Register register = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());

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
            collectionList = receiveCollectionList(selected_id, doctors_id);
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
            defectList = receiveDefectList(selected_id, collectionList);
            for (Long aLong : defectList) {

                System.out.println("----"+ aLong + "------");

                System.out.println("==============" + patient_id);

                PatientAndDoctor patientAndDoctor = doctorMapper.selectExits(patient_id, aLong);

                doctorMapper.updateFlag(0, patientAndDoctor.getId());

            }

            defectList = receiveDefectList(doctors_id, collectionList);

            for (Long aLong : defectList) {

                System.out.println("----"+ aLong + "------");

                doctorMapper.insertPatientAndDoctor(patient_id, aLong, 1);

            }


        }

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus_code(1);

        return responseMessage;

    }

    public ResponseMessage DoctorSelectPatient(RequestMessage message) {

        Register register = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long doctor_user_id = register.getId();

        Doctor doctor = doctorMapper.selectDoctorByUserId(doctor_user_id);

        System.out.println("==================doctor id" + doctor.getId());

        List<Long> patient = doctorMapper.selectPatient(doctor.getId(), 1);

        System.out.println("=======================" + patient);

        ResponseMessage responseMessage = new ResponseMessage();

        if (patient.size() == 0) {
            responseMessage.setStatus_code(0);//没有人选择

            return responseMessage;
        }

        List<DoctorMessage> doctorMessages = new ArrayList<>();

        for (int i = 0; i < patient.size(); i++) {

            Register user = userInfoMapper.selectPhoneNum(patient.get(i));
            DoctorMessage doctorMessage = new DoctorMessage();

            doctorMessage.setPhone_num(user.getPhone_num());
            doctorMessage.setPatient(userInfoMapper.selectPatientByUserId(user.getId()));

            doctorMessages.add(doctorMessage);

        }

        responseMessage.setStatus_code(1);
        responseMessage.setDoctorMessages(doctorMessages);

        return responseMessage;
    }

    //获取交集
    public static List<Long> receiveCollectionList(List<Long> firstArrayList, List<Long> secondArrayList) {
        List<Long> resultList = new ArrayList<Long>();
        LinkedList<Long> result = new LinkedList<Long>(firstArrayList);// 大集合用linkedlist
        HashSet<Long> othHash = new HashSet<Long>(secondArrayList);// 小集合用hashset
        Iterator<Long> iter = result.iterator();// 采用Iterator迭代器进行数据的操作
        while(iter.hasNext()) {
            if(!othHash.contains(iter.next())) {
                iter.remove();
            }
        }
        resultList = new ArrayList<Long>(result);
        return resultList;
    }

    //获取差集
    public static List<Long> receiveDefectList(List<Long> firstArrayList, List<Long> secondArrayList) {
        List<Long> resultList = new ArrayList<Long>();
        LinkedList<Long> result = new LinkedList<Long>(firstArrayList);// 大集合用linkedlist
        HashSet<Long> othHash = new HashSet<Long>(secondArrayList);// 小集合用hashset
        Iterator<Long> iter = result.iterator();// 采用Iterator迭代器进行数据的操作
        while (iter.hasNext()) {
            if (othHash.contains(iter.next())) {
                iter.remove();
            }
        }
        resultList = new ArrayList<Long>(result);
        return resultList;

    }

}