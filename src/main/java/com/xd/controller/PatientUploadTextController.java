package com.xd.controller;

import com.xd.pojo.RequestMessage;
import com.xd.service.PatientService;
import com.xd.service.TextService;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//门诊病历;住院病历;门诊记录;病理学检查
@RestController
public class PatientUploadTextController {

    @Autowired
    private TextService textService;

    @Autowired
    private PatientService patientService;



    //住院病历
    @RequestMapping(value = "/admission", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage AdmissionNote(@RequestBody RequestMessage message){

        return textService.admissionNote(message);

    }


    //病人上传既往病史
    @RequestMapping(value = "/disease", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage PatientsDisease(@RequestBody RequestMessage message){

        ResponseMessage result = patientService.PatientDiseaseInfo(message);

        return result;
    }

    //门诊记录
    @RequestMapping(value = "/outpatientRecords", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage OutPatientRecords(@RequestBody RequestMessage message){
        System.out.println(message);
        ResponseMessage response = textService.outPatientRecords(message);
        return response;
    }

    //病理学检查
    @RequestMapping(value = "/diseaseExamine", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage DiseaseExamine(@RequestBody RequestMessage message){
        System.out.println(message);
        ResponseMessage response = textService.diseaseExamine(message);

        System.out.println(response);
        return response;
    }

}
