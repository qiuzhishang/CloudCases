package com.xd.controller;

import com.xd.pojo.*;
import com.xd.service.TextService;
import com.xd.service.UploadFileService;
import com.xd.service.UserService;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Select")
@SuppressWarnings("all")
public class SelectResultController {
    @Autowired
    TextService textService;

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/AllPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage Picture(@RequestBody RequestMessage message) {

        ResponseMessage response = userService.selectAllPicture(message);


        return response;
    }

    //查询医生的文本信息

    //查询医生的所有图片信息



    //查询体检报告图片
    @RequestMapping(value = "/ReportPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage selectReportPicture(@RequestBody RequestMessage message) {

        List<TextInfo> info = userService.selectReportPicture(message);

        ResponseMessage response = new ResponseMessage();
        response.setTextInfo(info);

        return response;
    }

    //病症图片
    @RequestMapping(value = "/DiseasePicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

        public ResponseMessage selectDiseasePicture(@RequestBody RequestMessage message) {

            List<DiseasePicture> diseasePictures = userService.selectDiseasePicture(message);

            ResponseMessage response = new ResponseMessage();
            response.setDiseasePictures(diseasePictures);

            return response;
        }

    //查找化验检查
    @RequestMapping(value = "/LaboratoryPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public ResponseMessage LaboratoryPicture(@RequestBody RequestMessage message) {

        List<LaboratoryPicture> laboratoryPictures = userService.selectLaboratoryPicture(message);

        ResponseMessage response = new ResponseMessage();
        response.setLaboratoryPictures(laboratoryPictures);

        return response;
    }

    //查找影像检查
    @RequestMapping(value = "/ImagePicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public ResponseMessage ImagePicture(@RequestBody RequestMessage message) {

        List<ImagePicture> imagePictures = userService.selectImagePicture(message);

        ResponseMessage response = new ResponseMessage();
        response.setImagePictures(imagePictures);

        return response;
    }

    //侵入型器械检查

    @RequestMapping(value = "/InstrumentPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public ResponseMessage InstrumentPicture(@RequestBody RequestMessage message) {

        List<InstrumentPicture> instrumentPictures = userService.selectInstrumentPicture(message);

        ResponseMessage response = new ResponseMessage();
        response.setInstrumentPictures(instrumentPictures);

        return response;
    }

    //查询住院病例
    @RequestMapping(value = "/admission", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage SeclectAdmissionNote(@RequestBody RequestMessage message){

        List<AdmissionNote> admissionNotes = textService.SelectAdmissionNote(message);

        ResponseMessage response = new ResponseMessage();
        response.setAdmissionNotes(admissionNotes);

        return response;
    }

    //查询病理学检查
    @RequestMapping(value = "/examine", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage SeclectExamine(@RequestBody RequestMessage message){

        List<Examine> examines = textService.SelectDiseaseExamine(message);

        ResponseMessage response = new ResponseMessage();
        response.setExamines(examines);

        return response;
    }

    //查询门诊信息
    @RequestMapping(value = "/outPatient", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage SeclectOutPatient(@RequestBody RequestMessage message) {

        List<OutPatient> outPatients = textService.SelectOutPatient(message);

        ResponseMessage response = new ResponseMessage();

        response.setOutPatients(outPatients);

        return response;

    }

    //查询门诊记录
    @RequestMapping(value = "/outPatientRecords", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage SeclectOutPatientRecords(@RequestBody RequestMessage message) {

        List<OutPatientRecords> outPatientRecords = textService.SelectOutpatientRecords(message);

        ResponseMessage response = new ResponseMessage();

        response.setOutPatientRecords(outPatientRecords);

        return response;


    }


    //查询所有文本信息
    @RequestMapping(value = "/allTextInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage SeclectAllTextInfo(@RequestBody RequestMessage message){

        List<AdmissionNote> admissionNotes = textService.SelectAdmissionNote(message);
        List<Examine> examines = textService.SelectDiseaseExamine(message);
        List<OutPatient> outPatients = textService.SelectOutPatient(message);
        List<OutPatientRecords> outPatientRecords = textService.SelectOutpatientRecords(message);

        ResponseMessage response = new ResponseMessage();

        response.setAdmissionNotes(admissionNotes);
        response.setExamines(examines);
        response.setOutPatients(outPatients);
        response.setOutPatientRecords(outPatientRecords);

        return response;
    }

    //查询所有信息
    @RequestMapping(value = "/allInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage SeclectAllInfo(@RequestBody RequestMessage message){

        List<AdmissionNote> admissionNotes = textService.SelectAdmissionNote(message);
        List<Examine> examines = textService.SelectDiseaseExamine(message);
        List<OutPatient> outPatients = textService.SelectOutPatient(message);
        List<OutPatientRecords> outPatientRecords = textService.SelectOutpatientRecords(message);
        List<TextInfo> textInfos = userService.selectReportPicture(message);
        List<DiseasePicture> diseasePictures = userService.selectDiseasePicture(message);
        List<LaboratoryPicture> laboratoryPictures = userService.selectLaboratoryPicture(message);
        List<ImagePicture> imagePictures = userService.selectImagePicture(message);
        List<InstrumentPicture> instrumentPictures = userService.selectInstrumentPicture(message);

        ResponseMessage response = new ResponseMessage();

        response.setAdmissionNotes(admissionNotes);
        response.setExamines(examines);
        response.setOutPatients(outPatients);
        response.setOutPatientRecords(outPatientRecords);
        response.setTextInfo(textInfos);
        response.setDiseasePictures(diseasePictures);
        response.setLaboratoryPictures(laboratoryPictures);
        response.setImagePictures(imagePictures);
        response.setInstrumentPictures(instrumentPictures);

        return response;
    }


}
