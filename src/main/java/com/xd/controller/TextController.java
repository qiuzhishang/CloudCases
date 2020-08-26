package com.xd.controller;

import com.xd.mapper.UserInfoMapper;
import com.xd.pojo.Register;
import com.xd.pojo.RequestMessage;
import com.xd.service.TextService;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
//门诊病历;住院病历;门诊记录;病理学检查
@SuppressWarnings("all")
@RestController
public class TextController {

    @Autowired
    private TextService textService;

    //门诊病历
    @RequestMapping(value = "/outpatient", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage OutpatientMedicalRecords(@RequestBody RequestMessage message){
        ResponseMessage response = textService.OutpatientMedicalRecords(message);
        return response;
    }

    //住院病历
    @RequestMapping(value = "/admission", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage AdmissionNote(@RequestBody RequestMessage message){
        ResponseMessage response = textService.AdmissionNote(message);
        return response;
    }

    //门诊记录
    @RequestMapping(value = "/outpatientRecords", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage OutPatientRecords(@RequestBody RequestMessage message){
        System.out.println(message);
        ResponseMessage response = textService.OutpatientRecords(message);
        return response;
    }

    //病理学检查
    @RequestMapping(value = "/diseaseExamine", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage DiseaseExamine(@RequestBody RequestMessage message){
        System.out.println(message);
        ResponseMessage response = textService.DiseaseExamine(message);
        return response;
    }

}
