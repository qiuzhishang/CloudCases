package com.xd.controller;

import com.xd.pojo.PatientDiseaseInfo;
import com.xd.pojo.RequestMessage;
import com.xd.service.DoctorService;
import com.xd.service.PatientService;
import com.xd.utils.ResponseMessage;

import com.xd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("all")
@RestController

public class PatientController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;


    //病人完善个人信息
    @RequestMapping(value = "/patient", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage Patient(@RequestBody RequestMessage message){

        System.out.println(message.toString());
        ResponseMessage response = patientService.patientInsertInfo(message);
        System.out.println(response);

        return response;
    }


    //病人上传既往病史
    @RequestMapping(value = "/disease", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage Config2(@RequestBody RequestMessage message){

        ResponseMessage result = patientService.PatientDiseaseInfo(message);

        return result;
    }

    //查到所有的医生
    @RequestMapping(value = "/selectAllDoctor",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage SelectDoctor(@RequestBody RequestMessage message){

        ResponseMessage responseMessage = new ResponseMessage();

        patientService.selectAllDoctor();


        responseMessage.setStatus_code(1);

        return responseMessage;
    }

    //医生患者关系确定
    @RequestMapping(value = "/selectPAD",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage PatientAndDoctor(@RequestBody RequestMessage message){

        ResponseMessage responseMessage = doctorService.DoctorPersonalInfo(message);

        return responseMessage;


    }





}
