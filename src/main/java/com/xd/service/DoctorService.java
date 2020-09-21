package com.xd.service;


import com.xd.mapper.DoctorMapper;
import com.xd.mapper.UserInfoMapper;
import com.xd.pojo.Doctor;
import com.xd.pojo.PatientAndDoctor;
import com.xd.pojo.Register;
import com.xd.pojo.RequestMessage;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    public List<Doctor> selectAllDoctor(){
        List<Doctor> doctors = doctorMapper.selectAllDoctor();

        return doctors;

    }

    //医生信息
    public ResponseMessage doctorInsertInfo(RequestMessage message){
        Register users = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Doctor doctor = message.getDoctor();

        Doctor user = doctorMapper.selectDoctorById(message.getDoctor().getId_num());
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

    public ResponseMessage PatientAndDoctor(RequestMessage message){

        Register register = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long patient_id = register.getId();


        List<Long> doctors = message.getAdd_doctor_id();
        List<Long> doctors2 = message.getRemove_doctor_id();

        for (Long doctor : doctors) {

            PatientAndDoctor patientAndDoctor = null;
            patientAndDoctor.setPatient_id(patient_id);
            patientAndDoctor.setDoctor_id(doctor);
            patientAndDoctor.setFlag(1);

            PatientAndDoctor patientAndDoctor1 = doctorMapper.selectExits(patient_id, doctor);
            if (patientAndDoctor1 == null)
                doctorMapper.insertPatientAndDoctor(patientAndDoctor);
            else
                doctorMapper.updateFlag(1, patientAndDoctor1.getId());

        }

//        PatientAndDoctor patientAndDoctor = doctorMapper.selectExits(patient_id, doctor);

        for (Long doctor : doctors2) {
            PatientAndDoctor patientAndDoctor = doctorMapper.selectExits(patient_id, doctor);
            if (patientAndDoctor != null)
                doctorMapper.updateFlag(0, patientAndDoctor.getId());
        }



        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus_code(1);
        return responseMessage;

    }
}
