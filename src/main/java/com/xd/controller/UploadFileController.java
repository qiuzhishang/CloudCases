package com.xd.controller;

import ch.qos.logback.core.db.dialect.DBUtil;
import com.xd.mapper.UserInfoMapper;
import com.xd.pojo.TextInfo;
import com.xd.service.UploadFileService;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("/UploadFiles")
public class UploadFileController {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UploadFileService uploadFileService;


//    @PostMapping(value = "/upload")
//    public String fileUpload(@RequestParam(value = "file") MultipartFile file,
//                             @RequestParam(value = "phone_num") String phone_num,
//                             @RequestParam(value = "picture_type") int picture_type,
//                             @RequestParam(value = "date") String date){
//
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//        java.sql.Date sDate = null;
//        try {
//            java.util.Date date3 = sdf2.parse(date);
//            sDate = new java.sql.Date(date3.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        String result = uploadFileService.fileUpload(file, phone_num, picture_type, sDate);
//        return result;
//
//    }
    //病症图片
    @PostMapping(value = "/DiseasePicture")
    public ResponseMessage fileUploads(@RequestParam(value = "files") MultipartFile[] files,
                           @RequestParam(value = "phone_num") String phone_num,
                           @RequestParam(value = "picture_type") int picture_type,
                           @RequestParam(value = "date") String date ) throws ParseException{

        TextInfo info = new TextInfo();

        info.setPhone_num(phone_num);
        info.setDate(date);
        info.setPicture_type(picture_type);

        ResponseMessage result = uploadFileService.filesUpload(files, info);
        return result;
    }

    //体检记录
    @PostMapping(value = "/MedicalExaminationReport")
    public ResponseMessage MedicalExaminationReport(@RequestParam(value = "files") MultipartFile[] files,
                                                    @RequestParam(value = "hospital") String hospital,
                                                    @RequestParam(value = "report_info") String report_info,
                                                    @RequestParam(value = "result") String result,
                                                    @RequestParam(value = "phone_num") String phone_num,
                                                    @RequestParam(value = "date") String date) throws ParseException {
            //java String 转sql日期
            TextInfo info = new TextInfo();
            info.setHospital(hospital);
            info.setResult(result);
            info.setReport_info(report_info);
            info.setPhone_num(phone_num);
            info.setDate(date);


            ResponseMessage Result = uploadFileService.MedicalExaminationReportUpload(files, info );

            return Result;
    }

    //化验检查
    @PostMapping(value = "/LaboratoryExamination")
    public ResponseMessage LaboratoryExamination(@RequestParam(value = "files") MultipartFile[] files,
                                                    @RequestParam(value = "items") String items,
                                                    @RequestParam(value = "result") String result,
                                                    @RequestParam(value = "phone_num") String phone_num,
                                                    @RequestParam(value = "date") String date) throws ParseException {
        //java String 转sql日期
        TextInfo info = new TextInfo();
        info.setItems(items);
        info.setResult(result);
        info.setPhone_num(phone_num);
        info.setDate(date);


        ResponseMessage Result = uploadFileService.LaboratoryExaminationUpload(files, info );

        return Result;
    }
    //影像检查
    @PostMapping(value = "/ImageExamination")
    public ResponseMessage ImageExamination(@RequestParam(value = "files") MultipartFile[] files,
                                                 @RequestParam(value = "items") String items,
                                                 @RequestParam(value = "result") String result,
                                                 @RequestParam(value = "phone_num") String phone_num,
                                                 @RequestParam(value = "date") String date) throws ParseException {
        //java String 转sql日期
        TextInfo info = new TextInfo();
        info.setItems(items);
        info.setResult(result);
        info.setPhone_num(phone_num);
        info.setDate(date);


        ResponseMessage Result = uploadFileService.ImageExaminationUpload(files, info );

        return Result;
    }

    //侵入型器械检查结果 InvasiveInstruments
    @PostMapping(value = "/InvasiveInstruments")
    public ResponseMessage InvasiveInstruments(@RequestParam(value = "files") MultipartFile[] files,
                                            @RequestParam(value = "items") String items,
                                            @RequestParam(value = "result") String result,
                                            @RequestParam(value = "phone_num") String phone_num,
                                            @RequestParam(value = "date") String date) throws ParseException {
        //java String 转sql日期
        TextInfo info = new TextInfo();
        info.setItems(items);
        info.setResult(result);
        info.setPhone_num(phone_num);
        info.setDate(date);


        ResponseMessage Result = uploadFileService.InvasiveInstrumentsUpload(files, info );

        return Result;
    }



    //门诊病历
//    @PostMapping(value = "/OutpatientMedicalRecords")
//    public ResponseMessage OutpatientMedicalRecords(@RequestParam(value = "files") MultipartFile[] files,
//                                           @RequestParam(value = "date") String date,
//                                           @RequestParam(value = "department_treatment") String department_treatment,
//                                           @RequestParam(value = "hospital") String hospital,
//                                           @RequestParam(value = "doctor_name") String doctor_name,
//                                           @RequestParam(value = "phone_num") String phone_num){
//
//        //java String 转sql日期
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        java.sql.Date sDate = null;
//        try {
//            java.util.Date date3 = sdf.parse(date);
//            sDate = new java.sql.Date(date3.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        ResponseMessage result = uploadFileService.OutpatientMedicalRecords(files, sDate, department_treatment, hospital, doctor_name, phone_num );
//
//
//        return result;
//    }


}






