package com.xd.service;

import com.xd.mapper.DoctorMapper;
import com.xd.mapper.TextMapper;
import com.xd.mapper.UploadFileMapper;
import com.xd.mapper.UserInfoMapper;
import com.xd.pojo.*;
import com.xd.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("all")
@Service
public class UserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UploadFileMapper uploadFileMapper;

    @Autowired
    TextMapper textMapper;

    @Autowired
    DoctorMapper doctorMapper;


    GenerateToken generateToken = new GenerateToken();


    public Long translateToId(String num){

        Register user = userInfoMapper.selectUserByPhoneNum(num);
        System.out.println("------------------" + user);

        return user.getId();
    }

    //查询医生信息
    public ResponseMessage DoctorInfos(RequestMessage message){

        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();

        Doctor doctor = doctorMapper.selectDoctorByUserId(user_id);
        Long doctor_id = doctor.getId();

        ResponseMessage responseMessage = new ResponseMessage();


        DoctorResult doctorResult = new DoctorResult();

        responseMessage.setStatus_code(1);
        doctorResult.setDoctorAddrs(doctorMapper.selectDoctorAddrInfo(doctor_id));
        doctorResult.setDoctor(doctor);

        responseMessage.setDoctorResult(doctorResult);

        return responseMessage;
    }

    //用户注册
    public String userRegister(Register register){
        register.setTime();

        Register user = userInfoMapper.selectUserByPhoneNum(register.getPhone_num());
        if (user != null){
            System.out.println(user+"===========user");
            String result = "{\"status_code\": 0}";//注册失败
            return result;
        }
        String token = generateToken.generateToken(UUID.randomUUID().toString());
        register.setToken(token);
        int err =  userInfoMapper.insertUserInfo(register);
        user = userInfoMapper.selectUserByPhoneNum(register.getPhone_num());
        System.out.println(user);
        register.setUser_id(user.getId());
        System.out.println(user.getId());
        userInfoMapper.updateUserIdByPhone(user.getId(),register.getPhone_num());
        System.out.println(err);
        String result = "{\"status_code\": 1}";//注册成功
        return result;
    }

    //登录
    public ResponseMessage userSign(Sign sign, String token) {

        ResponseMessage reponse = new ResponseMessage();
        Register user = userInfoMapper.selectUserByPhoneNum(sign.getPhone_num());

        if (user == null) {
            System.out.println("userSign    1");
            reponse.setStatus_code(1);//账号不存在
            return reponse;
        }

        System.out.println(user.getId());

        Patient patientInfo = userInfoMapper.selectPatientByUserId(user.getId());
        Doctor doctorInfo = doctorMapper.selectDoctorByUserId(user.getId());

        System.out.println(patientInfo);
        System.out.println(doctorInfo);

        System.out.println("user_type : " + user.getUser_type());

        //患者登录
        if (UserType.Patient.getValue() == user.getUser_type()){
            if (patientInfo == null) {
                System.out.println("userSign    5");
                reponse.setStatus_code(4);//信息为空
                return reponse;
            }

            System.out.println(patientInfo);

            if (sign.getPass_word() == null){
                System.out.println("userSign    2");
                if (token.equals(user.getToken())){

                    System.out.println(" patient Sign    4");
                    String new_token = generateToken.generateToken(UUID.randomUUID().toString());

                    int sex = patientInfo.getSex();
                    String name = patientInfo.getName();

                    userInfoMapper.updateToken(new_token, user.getId());

                    reponse.setToken(new_token);
                    reponse.setStatus_code(0);
                    reponse.setName(name);
                    reponse.setSex(sex);
                    reponse.setUser_type(user.getUser_type());

                    return reponse;

                }else {
                    reponse.setStatus_code(3);//token异常
                    return reponse;
                }

            }else {

                if (!user.getPass_word().equals(sign.getPass_word())) {
                    System.out.println("userSign    3");
                    reponse.setStatus_code(2);//密码错误
                    return reponse;
                }else {

                    System.out.println("userSign    4");
                    String new_token = generateToken.generateToken(UUID.randomUUID().toString());

                    int sex = patientInfo.getSex();
                    String name = patientInfo.getName();

                    userInfoMapper.updateToken(new_token, user.getId());

                    reponse.setToken(new_token);
                    reponse.setStatus_code(0);
                    reponse.setName(name);
                    reponse.setSex(sex);
                    reponse.setUser_type(user.getUser_type());

                    return reponse;
                }
            }


        }else if(UserType.Doctor.getValue() == user.getUser_type()){//医生登录

            System.out.println("==============================");

            if (doctorInfo == null) {
                System.out.println("userSign    5");
                reponse.setStatus_code(4);//信息为空
                return reponse;
            }

            System.out.println(doctorInfo);

            if (sign.getPass_word() == null){
                System.out.println("userSign    2");
                if (token.equals(user.getToken())){

                    System.out.println(" doctor Sign    4");
                    String new_token = generateToken.generateToken(UUID.randomUUID().toString());

                    String name = doctorInfo.getName();

                    userInfoMapper.updateToken(new_token, user.getId());

                    reponse.setToken(new_token);
                    reponse.setStatus_code(0);
                    reponse.setName(doctorInfo.getName());
                    reponse.setUser_type(user.getUser_type());

                    return reponse;

                }else {
                    reponse.setStatus_code(3);//token异常
                    return reponse;
                }

            }else {

                if (!user.getPass_word().equals(sign.getPass_word())) {
                    System.out.println("userSign    3");
                    reponse.setStatus_code(2);//密码错误
                    return reponse;
                }else {

                    System.out.println("doctor    4");
                    String new_token = generateToken.generateToken(UUID.randomUUID().toString());

                    String name = doctorInfo.getName();

                    userInfoMapper.updateToken(new_token, user.getId());

                    reponse.setToken(new_token);
                    reponse.setStatus_code(0);
                    reponse.setName(doctorInfo.getName());
                    reponse.setUser_type(user.getUser_type());

                    return reponse;
                }
            }


        }


        System.out.println("userSign    8");
        return null;
    }

    //患者输入个人信息
    public ResponseMessage patientInsertInfo(RequestMessage message){
        System.out.println(message);
        Patient user = userInfoMapper.selectPatientByIdNum(message.getPatient().getId_num());
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
            Register err = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
            Long id = err.getId();
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

            userInfoMapper.insertPatientInfo(message.getPatient().getName(),
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
                userInfoMapper.insertEmergeContactInfo(em.getName(),em.getPhone_num(),id);
            }

            String token = generateToken.generateToken(UUID.randomUUID().toString());

            System.out.println(token);

            ResponseMessage response = new ResponseMessage();
            response.setStatus_code(1);//信息填入成功
            response.setToken(token);
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

    //患者病症信息
    public ResponseMessage PatientDiseaseInfo(PatientDiseaseInfo patientDiseaseInfo, String phone_num, String token){
        Register user = userInfoMapper.selectToken(phone_num);
        ResponseMessage response = new ResponseMessage();
        if (user.getToken().equals(token)){
            userInfoMapper.insertDiseaseInfo(patientDiseaseInfo.getDisease_type(), patientDiseaseInfo.getDisease_info(), user.getId());

            response.setStatus_code(1);
            return response;
        }
        response.setStatus_code(0);
        return response;
    }

    //查找所有图片
    public ResponseMessage selectAllPicture(RequestMessage message){
        ResponseMessage response = new ResponseMessage();

        List<TextInfo> textInfos = selectReportPicture(message);
        List<DiseasePicture> diseasePictures = selectDiseasePicture(message);
        List<LaboratoryPicture> laboratoryPictures = selectLaboratoryPicture(message);
        List<ImagePicture> imagePictures = selectImagePicture(message);
        List<InstrumentPicture> instrumentPictures = selectInstrumentPicture(message);

        response.setTextInfo(textInfos);
        response.setDiseasePictures(diseasePictures);
        response.setLaboratoryPictures(laboratoryPictures);
        response.setImagePictures(imagePictures);
        response.setInstrumentPictures(instrumentPictures);


        return response;

    }

    //查找体检报告
    public List<TextInfo> selectReportPicture(RequestMessage message){
        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();

        List<TextInfo> textInfos;
        textInfos = uploadFileMapper.selectMedicalExaminationReportId(user_id);

        for (TextInfo textInfo : textInfos) {
            System.out.println(textInfo.getId());
            List<String> address = uploadFileMapper.selectAddress(textInfo.getId());
            System.out.println(address);
            textInfo.setAddress(address);
        }

        return textInfos;
    }
    //查找病历照片
    public List<DiseasePicture> selectDiseasePicture(RequestMessage message){
        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();

        List<DiseasePicture> diseasePictures;
        diseasePictures = uploadFileMapper.selectDiseasePicture(user_id);
        for (DiseasePicture diseasePicture : diseasePictures) {
            System.out.println(diseasePicture.getId());
            List<String> address = uploadFileMapper.selectDiseasePictureAddr(diseasePicture.getId());
            System.out.println(address);
            diseasePicture.setAddress(address);
        }

        return diseasePictures;

    }

    //查找化验检查
    public List<LaboratoryPicture> selectLaboratoryPicture(RequestMessage message){
        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();

        List<LaboratoryPicture> laboratoryPictures;
        laboratoryPictures = uploadFileMapper.selectLaboratoryExaminationId(user_id);
        ResponseMessage response = new ResponseMessage();
        for (LaboratoryPicture laboratoryPicture : laboratoryPictures) {
            System.out.println(laboratoryPicture.getId());
            List<String> address = uploadFileMapper.selectLaboratoryAddress(laboratoryPicture.getId());
            System.out.println(address);
            laboratoryPicture.setAddress(address);
        }

        return laboratoryPictures;
    }

    //查找影像检查
    public List<ImagePicture> selectImagePicture(RequestMessage message){
        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();

        List<ImagePicture> imagePictures;
        imagePictures = uploadFileMapper.selectImageExaminationId(user_id);
        ResponseMessage response = new ResponseMessage();
        for (ImagePicture imagePicture : imagePictures) {
            System.out.println(imagePicture.getId());
            List<String> address = uploadFileMapper.selectImageExaminationAddress(imagePicture.getId());
            System.out.println(address);
            imagePicture.setAddress(address);
        }

        return imagePictures;
    }

    //侵入型器械检查
    public List<InstrumentPicture> selectInstrumentPicture(RequestMessage message){
        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        Long user_id = user.getId();

        List<InstrumentPicture> instrumentPictures;
        instrumentPictures = uploadFileMapper.selectInvasiveInstrumentsId(user_id);
        ResponseMessage response = new ResponseMessage();
        for (InstrumentPicture instrumentPicture : instrumentPictures) {
            System.out.println(instrumentPicture.getId());
            List<String> address = uploadFileMapper.selectInvasiveInstrumentsAddress(instrumentPicture.getId());
            System.out.println(address);
            instrumentPicture.setAddress(address);
        }

        return instrumentPictures;
    }


}
