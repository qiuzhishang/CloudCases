package com.xd.service;

import com.xd.mapper.TextMapper;
import com.xd.mapper.UploadFileMapper;
import com.xd.mapper.UserInfoMapper;
import com.xd.pojo.*;
import com.xd.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    GenerateToken generateToken = new GenerateToken();
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

    public ResponseMessage userSign(Sign sign, String token) {
        ResponseMessage message = new ResponseMessage();
        Register user = userInfoMapper.selectUserByPhoneNum(sign.getPhone_num());

        if (user == null) {
            System.out.println("userSign    1");
            message.setStatus_code(1);//账号不存在
            return message;
        }

        Patient patientInfo = userInfoMapper.selectPatientByUserId(user.getId());
        if (patientInfo == null) {
            System.out.println("userSign    5");
            message.setStatus_code(4);//信息为空
            return message;
        }
        System.out.println(patientInfo);

        if (sign.getPass_word() == null) {
            System.out.println("userSign    2");
            if (token.equals(user.getToken())){
                System.out.println("-------------");
                System.out.println("userSign    4");
                int sex = patientInfo.getSex();
                String name = patientInfo.getName();

                message.setStatus_code(0);
                message.setName(name);
                message.setSex(sex);
                message.setUser_type(user.getUser_type());

                return message;

            }else{
                message.setStatus_code(3);//token异常
                return message;
            }

        } else {
            if (!user.getPass_word().equals(sign.getPass_word())) {
                System.out.println("userSign    3");
                message.setStatus_code(2);//密码错误
                return message;
            }


            System.out.println("------------------------------");
            if (UserType.Patient.getValue() == user.getUser_type()) {

                System.out.println("userSign    4");
                String new_token = generateToken.generateToken(UUID.randomUUID().toString());

                int sex = patientInfo.getSex();
                String name = patientInfo.getName();

                userInfoMapper.updateToken(new_token, sign.getPhone_num());

                message.setToken(new_token);
                message.setStatus_code(0);
                message.setName(name);
                message.setSex(sex);

                return message;

            } else if (UserType.Doctor.getValue() == user.getUser_type()) {
                System.out.println("6");
                // TODO
                return null;
            }
            System.out.println("userSign    7");
        }
        System.out.println("userSign    8");
        return null;
    }

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
                message.getPatient().getName()!=null &&
                message.getPatient().getId_num()!=null &&
                message.getPatient().getBirthplace()!=null &&
                message.getPatient().getEmerge()!=null &&
                message.getPatient().getNow_addr()!=null &&
                message.getPatient().getPostal_addr()!=null &&
                message.getPatient().getRace() != null) {
            Register err = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
            Long id = err.getId();
            message.getPatient().setUser_id(id);
            userInfoMapper.insertPatientInfo(message.getPatient());

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
//------------------------------------------------------------------------------

    public String doctorInsertInfo(Doctor doctor){
        Doctor user = userInfoMapper.selectDoctorById(doctor.getId_num());
        if (user != null)
            return "{\"status_code\":0}";
        userInfoMapper.insertDoctorInfo(doctor);
        return "{\"status_code\":1}";
    }
//------------------------------------------------------------------------------

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
        ResponseMessage response = new ResponseMessage();
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
