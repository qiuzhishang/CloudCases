package com.xd.controller;

import com.xd.pojo.*;
import com.xd.service.PatientService;
import com.xd.service.TextService;
import com.xd.service.UploadFileService;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Select")

public class PatientSelectResultController {
    @Autowired
    TextService textService;

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    private PatientService patientService;


    @RequestMapping(value = "/AllPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage Picture(@RequestBody RequestMessage message) {

        return patientService.selectAllPicture(message);

    }

    //查询医生的文本信息

    //查询医生的所有图片信息


    //查询体检报告图片
    @RequestMapping(value = "/ReportPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage selectReportPicture(@RequestBody RequestMessage message) {

        List<Report> reports = patientService.selectReportPicture(message);

        ResponseMessage response = new ResponseMessage();
        response.setReports(reports);

        return response;
    }

    //病症图片
    @RequestMapping(value = "/DiseasePicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage selectDiseasePicture(@RequestBody RequestMessage message) {

        List<DiseasePicture> diseasePictures = patientService.selectDiseasePicture(message);

        ResponseMessage response = new ResponseMessage();
        response.setDiseasePictures(diseasePictures);

        return response;
    }

    //查找化验检查
    @RequestMapping(value = "/LaboratoryPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage LaboratoryPicture(@RequestBody RequestMessage message) {

        List<LaboratoryPicture> laboratoryPictures = patientService.selectLaboratoryPicture(message);

        ResponseMessage response = new ResponseMessage();
        response.setLaboratoryPictures(laboratoryPictures);

        return response;
    }

    //查找影像检查
    @RequestMapping(value = "/ImagePicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage ImagePicture(@RequestBody RequestMessage message) {

        List<ImagePicture> imagePictures = patientService.selectImagePicture(message);

        ResponseMessage response = new ResponseMessage();
        response.setImagePictures(imagePictures);

        return response;
    }

    //侵入型器械检查
    @RequestMapping(value = "/InstrumentPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage InstrumentPicture(@RequestBody RequestMessage message) {

        ResponseMessage response = new ResponseMessage();

        response.setInstrumentPictures(patientService.selectInstrumentPicture(message));

        return response;
    }

    //查询住院病例
    @RequestMapping(value = "/admission", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage selectAdmissionNote(@RequestBody RequestMessage message){

        ResponseMessage response = new ResponseMessage();

        response.setAdmissionNotes(textService.selectAdmissionNote(message));

        return response;
    }

    //查询病理学检查
    @RequestMapping(value = "/examine", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage selectExamine(@RequestBody RequestMessage message){

        ResponseMessage response = new ResponseMessage();

        response.setExamines(textService.selectDiseaseExamine(message));

        return response;
    }

    //查询门诊信息
    @RequestMapping(value = "/outPatient", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage selectOutPatient(@RequestBody RequestMessage message) {

        ResponseMessage response = new ResponseMessage();

        response.setOutPatients(textService.selectOutPatient(message));

        return response;

    }

    //查询门诊记录
    @RequestMapping(value = "/outPatientRecords", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage selectOutPatientRecords(@RequestBody RequestMessage message) {

        List<OutPatientRecords> outPatientRecords = textService.selectOutPatientRecords(message);

        ResponseMessage response = new ResponseMessage();

        response.setOutPatientRecords(outPatientRecords);

        return response;


    }


    //查询所有文本信息
    @RequestMapping(value = "/allTextInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage selectAllTextInfo(@RequestBody RequestMessage message){

        List<AdmissionNote> admissionNotes = textService.selectAdmissionNote(message);
        List<Examine> examines = textService.selectDiseaseExamine(message);
        List<OutPatient> outPatients = textService.selectOutPatient(message);
        List<OutPatientRecords> outPatientRecords = textService.selectOutPatientRecords(message);

        ResponseMessage response = new ResponseMessage();

        response.setAdmissionNotes(admissionNotes);
        response.setExamines(examines);
        response.setOutPatients(outPatients);
        response.setOutPatientRecords(outPatientRecords);

        return response;
    }

    //查询所有信息
    @RequestMapping(value = "/allInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage selectAllInfo(@RequestBody RequestMessage message){

        List<AdmissionNote> admissionNotes = textService.selectAdmissionNote(message);
        List<Examine> examines = textService.selectDiseaseExamine(message);
        List<OutPatient> outPatients = textService.selectOutPatient(message);
        List<OutPatientRecords> outPatientRecords = textService.selectOutPatientRecords(message);
        List<Report> reports = patientService.selectReportPicture(message);
        List<DiseasePicture> diseasePictures = patientService.selectDiseasePicture(message);
        List<LaboratoryPicture> laboratoryPictures = patientService.selectLaboratoryPicture(message);
        List<ImagePicture> imagePictures = patientService.selectImagePicture(message);
        List<InstrumentPicture> instrumentPictures = patientService.selectInstrumentPicture(message);

        ResponseMessage response = new ResponseMessage();

        response.setAdmissionNotes(admissionNotes);
        response.setExamines(examines);
        response.setOutPatients(outPatients);
        response.setOutPatientRecords(outPatientRecords);
        response.setReports(reports);
        response.setDiseasePictures(diseasePictures);
        response.setLaboratoryPictures(laboratoryPictures);
        response.setImagePictures(imagePictures);
        response.setInstrumentPictures(instrumentPictures);

        return response;
    }


}
