package com.xd.service;

import com.xd.mapper.*;
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
    RegisterMapper registerMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UploadFileMapper uploadFileMapper;

    @Autowired
    TextMapper textMapper;

    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    PatientMapper patientMapper;


    // public Long translateToId(String num){
    //
    //     RegisterService user = userInfoMapper.selectUserByPhoneNum(num);
    //     System.out.println("------------------" + user);
    //
    //     return user.getId();
    // }








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

        Long user_id = message.getUserId();

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

        Long user_id = message.getUserId();

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

        Long user_id = message.getUserId();

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

        Long user_id = message.getUserId();


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

        Long user_id = message.getUserId();


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
