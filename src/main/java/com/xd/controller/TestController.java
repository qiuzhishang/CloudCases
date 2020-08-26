package com.xd.controller;

import com.xd.mapper.UserInfoMapper;
import com.xd.pojo.PatientDiseaseInfo;
import com.xd.pojo.Register;
import com.xd.pojo.RequestMessage;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("all")
@RestController
public class TestController {
    @Autowired
    UserInfoMapper userInfoMapper;

    @RequestMapping(value = "/disease/info")
    public ResponseMessage Config(@RequestBody RequestMessage message){
        Register user = userInfoMapper.selectUserByPhoneNum(message.getPhone_num());
        List<PatientDiseaseInfo> result = userInfoMapper.selectDiseaseInfo(user.getId());

        System.out.println(result);

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setPatientDiseaseInfoList(result);

        return responseMessage;
    }

    @RequestMapping(value =  "/test/files")
    public ResponseMessage uploadFiles(@RequestBody RequestMessage message) {


        ResponseMessage result = new ResponseMessage();
        return result;
    }

    @RequestMapping(value =  "/selectPicture")
    public ResponseMessage returnPicture(@RequestBody RequestMessage message) {


        ResponseMessage result = new ResponseMessage();
        return result;
    }
}
