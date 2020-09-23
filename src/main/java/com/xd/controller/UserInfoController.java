package com.xd.controller;

import com.xd.pojo.PatientDiseaseInfo;
import com.xd.pojo.RequestMessage;
import com.xd.service.DoctorService;
import com.xd.utils.ResponseMessage;

import com.xd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("all")
@RestController
public class UserInfoController {
    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    //医生注册信息
    @RequestMapping(value = "/Doctor",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage Doctor(@RequestBody RequestMessage message){

        ResponseMessage responseMessage = doctorService.doctorInsertInfo(message);

        return responseMessage;
    }

    //选择医生
    @RequestMapping(value = "/selectAllDoctor",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage SelectDoctor(@RequestBody RequestMessage message){

        ResponseMessage responseMessage = new ResponseMessage();

        responseMessage.setDoctors(doctorService.selectAllDoctor());
        responseMessage.setStatus_code(1);
        return responseMessage;
    }

    //医生患者关系确定
    @RequestMapping(value = "/selectPAD",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage PatientAndDoctor(@RequestBody RequestMessage message){

        ResponseMessage responseMessage = doctorService.PatientSelectDoctor(message);

        return responseMessage;
    }

    //医生查看患者信息
    @RequestMapping(value = "/selectDAP",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage DoctorSeclectPatient(@RequestBody RequestMessage message){

        ResponseMessage responseMessage = doctorService.DoctorSelectPatient(message);

        return responseMessage;
    }

    //病人注册信息
    @RequestMapping(value = "/patient", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage Patient(@RequestBody RequestMessage message){

        System.out.println(message.toString());
        ResponseMessage response = userService.patientInsertInfo(message);
        System.out.println(response);

        return response;
    }


    //病人上传既往病史
    @RequestMapping(value = "/disease", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage Config2(@RequestBody RequestMessage message){
        String phone_num = message.getPhone_num();

        String token = message.getToken();
        PatientDiseaseInfo info = message.getPatientDiseaseInfo();

        ResponseMessage result = userService.PatientDiseaseInfo(info, phone_num, token);

        return result;
    }



}
