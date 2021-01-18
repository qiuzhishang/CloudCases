package com.xd.controller;

import com.xd.mapper.PatientMapper;
import com.xd.pojo.RequestMessage;
import com.xd.service.PatientService;
import com.xd.utils.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientMapper patientMapper;


    //病人完善个人信息
    @RequestMapping(value = "/patient", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage Patient(@RequestBody RequestMessage message){

        System.out.println(message.toString());
        ResponseMessage response = patientService.patientInsertInfo(message);
        System.out.println(response);

        return response;
    }



    //查到所有的医生
    @RequestMapping(value = "/selectAllDoctor",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage SelectDoctor(@RequestBody RequestMessage message){

        ResponseMessage responseMessage = new ResponseMessage();

        responseMessage.setDoctors(patientService.selectAllDoctor(message));
        responseMessage.setSelected_doctor_id(patientMapper.selectedDoctorId(message.getUserId()));

        responseMessage.setStatus_code(1);

        return responseMessage;
    }

    //医生患者关系确定
    @RequestMapping(value = "/selectPAD",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage PatientAndDoctor(@RequestBody RequestMessage message){

        return patientService.PatientSelectDoctor(message);

    }



}
