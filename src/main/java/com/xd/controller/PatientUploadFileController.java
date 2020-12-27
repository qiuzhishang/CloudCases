package com.xd.controller;

import com.xd.pojo.RequestMessage;
import com.xd.pojo.TextInfo;
import com.xd.service.UploadFileService;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

/*
* 患者上传图片操作
* */

@RestController
@RequestMapping("/UploadFiles")
public class PatientUploadFileController {

    @Autowired
    private UploadFileService uploadFileService;


    @PostMapping(value = "/outPatient")
    public ResponseMessage OutpatientMedicalRecords(@RequestParam(value = "files[]") List<MultipartFile> files,
                                                    @RequestParam(value = "userId") Long userId,
                                                    @RequestParam(value = "date") String date,
                                                    @RequestParam(value = "department") String department,
                                                    @RequestParam(value = "hospital") String hospital,
                                                    @RequestParam(value = "doctor_name") String doctor_name,
                                                    @RequestParam(value = "disease_info") String disease_info){

        TextInfo textInfo = new TextInfo();

        textInfo.setDate(date);
        textInfo.setUserId(userId);
        textInfo.setDepartment(department);
        textInfo.setHospital(hospital);
        textInfo.setDoctor_name(doctor_name);
        textInfo.setDisease_info(disease_info);

        return uploadFileService.outPatientMedicalRecords(files, textInfo);


    }


    //病症图片
    @PostMapping(value = "/DiseasePicture")
    public ResponseMessage DiseasePicture(@RequestParam(value = "files[]") List<MultipartFile> files,
                           @RequestParam(value = "userId") Long userId,
                           @RequestParam(value = "picture_type") int picture_type,
                           @RequestParam(value = "information") String information,
                           @RequestParam(value = "date") String date ) throws ParseException{

        TextInfo info = new TextInfo();

        info.setUserId(userId);
        info.setDate(date);
        info.setPicture_type(picture_type);
        info.setInformation(information);

        return uploadFileService. DiseasePictureUpload(files, info);
    }

    //体检报告
    @PostMapping(value = "/MedicalExaminationReport")
    public ResponseMessage MedicalExaminationReport(@RequestParam(value = "files[]") List<MultipartFile> files,
                                                    @RequestParam(value = "hospital") String hospital,
                                                    @RequestParam(value = "report_info") String report_info,
                                                    @RequestParam(value = "result") String result,
                                                    @RequestParam(value = "userId") Long userId,
                                                    @RequestParam(value = "date") String date) throws ParseException {
            //java String 转sql日期
            TextInfo info = new TextInfo();
            info.setHospital(hospital);
            info.setResult(result);
            info.setReport_info(report_info);
            info.setUserId(userId);
            info.setDate(date);

            ResponseMessage Result = uploadFileService.MedicalExaminationReportUpload(files, info );

            return Result;
    }

    //化验检查
    @PostMapping(value = "/LaboratoryExamination")
    public ResponseMessage LaboratoryExamination(@RequestParam(value = "files[]") List<MultipartFile> files,
                                                    @RequestParam(value = "items") String items,
                                                    @RequestParam(value = "result") String result,
                                                    @RequestParam(value = "userId") Long userId,
                                                    @RequestParam(value = "date") String date) throws ParseException {
        //java String 转sql日期
        TextInfo info = new TextInfo();
        info.setItems(items);
        info.setResult(result);
        info.setUserId(userId);
        info.setDate(date);


        ResponseMessage Result = uploadFileService.LaboratoryExaminationUpload(files, info );

        return Result;
    }
    //影像检查
    @PostMapping(value = "/ImageExamination")
    public ResponseMessage ImageExamination(@RequestParam(value = "files[]") List<MultipartFile> files,
                                                 @RequestParam(value = "items") String items,
                                                 @RequestParam(value = "result") String result,
                                                 @RequestParam(value = "userId") Long userId,
                                                 @RequestParam(value = "date") String date) throws ParseException {
        //java String 转sql日期
        TextInfo info = new TextInfo();
        info.setItems(items);
        info.setResult(result);
        info.setUserId(userId);
        info.setDate(date);


        ResponseMessage Result = uploadFileService.ImageExaminationUpload(files, info );

        return Result;
    }

    //侵入型器械检查结果 InvasiveInstruments
    @PostMapping(value = "/InvasiveInstruments")
    public ResponseMessage InvasiveInstruments(@RequestParam(value = "files[]") List<MultipartFile> files,
                                            @RequestParam(value = "items") String items,
                                            @RequestParam(value = "result") String result,
                                            @RequestParam(value = "userId") Long userId,
                                            @RequestParam(value = "date") String date) throws ParseException {

        TextInfo info = new TextInfo();
        info.setItems(items);
        info.setResult(result);
        info.setUserId(userId);
        info.setDate(date);//java String 转sql日期


        ResponseMessage Result = uploadFileService.InvasiveInstrumentsUpload(files, info );

        return Result;
    }






}






