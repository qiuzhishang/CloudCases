package com.xd.service;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xd.mapper.DoctorMapper;
import com.xd.mapper.PatientMapper;
import com.xd.mapper.RegisterMapper;
import com.xd.pojo.Doctor;
import com.xd.pojo.Patient;
import com.xd.pojo.Register;
import com.xd.pojo.Sign;
import com.xd.utils.GenerateToken;
import com.xd.utils.ResponseMessage;
import com.xd.utils.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SignService {

    @Autowired
    RegisterMapper registerMapper;

    @Autowired
    PatientMapper patientMapper;

    @Autowired
    DoctorMapper doctorMapper;

    GenerateToken generateToken = new GenerateToken();



    //登录
    public ResponseMessage  userSign(Sign sign, String token) {

        ResponseMessage responseMessage = new ResponseMessage();
        Register user = registerMapper.selectUserByPhoneNum(sign.getPhone_num(), sign.getUser_type());

        if (user == null) {
            System.out.println("userSign    1");
            responseMessage.setStatus_code(1);//账号不存在
            return responseMessage;
        }

        System.out.println(user.getId());

        Patient patientInfo = patientMapper.selectPatientByUserId(user.getId());
        Doctor doctorInfo = doctorMapper.selectDoctorByUserId(user.getId());

        System.out.println(patientInfo);
        System.out.println(doctorInfo);

        System.out.println("user_type : " + user.getUser_type());

        //患者登录
        if (UserType.Patient.getValue() == user.getUser_type()){
            if (patientInfo == null) {
                System.out.println("userSign    5");
                responseMessage.setStatus_code(4);//个人信息为空
                responseMessage.setUserId(user.getId());
                return responseMessage;
            }

            System.out.println(patientInfo);

            if (sign.getPass_word() == null){
                System.out.println("userSign    2");
                if (token.equals(user.getToken())){

                    System.out.println(" patient Sign    4");

                    String new_token = generateToken.generateToken(UUID.randomUUID().toString());

                    int sex = patientInfo.getSex();
                    String name = patientInfo.getName();

                    registerMapper.updateToken(new_token, user.getId());

                    responseMessage.setToken(new_token);
                    responseMessage.setStatus_code(0);
                    responseMessage.setName(name);
                    responseMessage.setSex(sex);
                    responseMessage.setUser_type(user.getUser_type());
                    responseMessage.setUserId(user.getId());

                    return responseMessage;

                }else {
                    responseMessage.setStatus_code(3);//token异常
                    return responseMessage;
                }

            }else {

                if (!user.getPass_word().equals(sign.getPass_word())) {
                    System.out.println("userSign    3");
                    responseMessage.setStatus_code(2);//密码错误
                    return responseMessage;
                }else {

                    System.out.println("userSign    4");
                    String new_token = generateToken.generateToken(UUID.randomUUID().toString());

                    int sex = patientInfo.getSex();
                    String name = patientInfo.getName();

                    registerMapper.updateToken(new_token, user.getId());

                    responseMessage.setToken(new_token);
                    responseMessage.setStatus_code(0);
                    responseMessage.setName(name);
                    responseMessage.setSex(sex);
                    responseMessage.setUser_type(user.getUser_type());
                    responseMessage.setUserId(user.getId());

                    return responseMessage;
                }
            }


        }else if(UserType.Doctor.getValue() == user.getUser_type()){//医生登录

            System.out.println("==============================");

            if (doctorInfo == null) {
                System.out.println("userSign    5");
                responseMessage.setStatus_code(4);//个人信息为空
                responseMessage.setUserId(user.getId());

                return responseMessage;
            }

            System.out.println(doctorInfo);

            if (sign.getPass_word() == null){
                System.out.println("userSign    2");
                if (token.equals(user.getToken())){

                    System.out.println(" doctor Sign    4");
                    String new_token = generateToken.generateToken(UUID.randomUUID().toString());

                    String name = doctorInfo.getName();

                    registerMapper.updateToken(new_token, user.getId());

                    responseMessage.setToken(new_token);
                    responseMessage.setStatus_code(0);
                    responseMessage.setName(doctorInfo.getName());
                    responseMessage.setUser_type(user.getUser_type());
                    responseMessage.setUserId(user.getId());

                    return responseMessage;

                }else {
                    responseMessage.setStatus_code(3);//token异常
                    return responseMessage;
                }

            }else {

                if (!user.getPass_word().equals(sign.getPass_word())) {
                    System.out.println("userSign    3");
                    responseMessage.setStatus_code(2);//密码错误
                }else {

                    System.out.println("doctor    4");
                    String new_token = generateToken.generateToken(UUID.randomUUID().toString());

                    String name = doctorInfo.getName();

                    registerMapper.updateToken(new_token, user.getId());

                    responseMessage.setToken(new_token);
                    responseMessage.setStatus_code(0);
                    responseMessage.setName(name);
                    responseMessage.setUser_type(user.getUser_type());
                    responseMessage.setUserId(user.getId());

                }
                return responseMessage;
            }

        }


        System.out.println("userSign    8");
        return null;
    }
}
