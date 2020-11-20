package com.xd.controller;

import com.xd.mapper.PatientMapper;
import com.xd.mapper.RegisterMapper;
import com.xd.pojo.PatientDiseaseInfo;
import com.xd.pojo.Register;
import com.xd.pojo.RequestMessage;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("all")
@RestController

//测试
public class TestController {
    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private PatientMapper patientMapper;

    @RequestMapping(value = "/disease/info")
    public ResponseMessage Config(@RequestBody RequestMessage message){
        Register user = registerMapper.selectUserByPhoneNum(message.getPhone_num(),message.getUser_type());
        List<PatientDiseaseInfo> result = patientMapper.selectDiseaseInfo(user.getId());

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
