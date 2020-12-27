package com.xd.service;

import com.xd.mapper.DoctorMapper;
import com.xd.mapper.PatientUploadFileMapper;
import com.xd.pojo.*;
import com.xd.utils.AddressMethod;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.List;

@SuppressWarnings("all")
@Service
public class UploadFileService {
    @Autowired
    private PatientUploadFileMapper patientUploadFileMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    //病症图片
    public ResponseMessage DiseasePictureUpload(List<MultipartFile> files, TextInfo info) {

        int flag = 1;

        Long user_id = info.getUserId();

        System.out.println(files.size());

        info.setUser_id(user_id);
        patientUploadFileMapper.insertPictureInfo(info);

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                System.out.println("file is empty");
            }
            String fileName = file.getOriginalFilename();
            fileName = fileName.split("\\.")[0] + System.currentTimeMillis() + "."
                    + fileName.split("\\.")[1];
            File dest = new File(AddressMethod.GeneratorAddress(user_id, fileName));
            //存入数据库的路径path

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                String file_addr = AddressMethod.GeneratorAddressOut(user_id, fileName);


                List<DiseasePicture> diseasePictures = patientUploadFileMapper.selectDiseasePicture(user_id);
                Long max = 0L;
                for (DiseasePicture diseasePicture : diseasePictures) {
                    if (max < diseasePicture.getId())
                        max = diseasePicture.getId();
                }
                patientUploadFileMapper.insertPictureAddrInfo(file_addr, max);


            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传失败" );
                flag = 0;
            }
            System.out.println("上传成功");

        }
        ResponseMessage response = new ResponseMessage();
        if (flag == 1) {

            response.setStatus_code(1);
            return response;
        } else {
            response.setStatus_code(0);
            return response;
        }
    }

    /**
     * 2020.8.19
     */
    //体检报告
    public ResponseMessage MedicalExaminationReportUpload(List<MultipartFile> files, TextInfo info) {

        int flag = 1;
        System.out.println(info);

        Long user_id = info.getUserId();

        info.setUser_id(user_id);

        patientUploadFileMapper.insertMedicalExaminationReport(info);

        List<Report> IdReceive= patientUploadFileMapper.selectMedicalExaminationReportId(user_id);
        Long report_id = 0L;
        for (Report textInfo : IdReceive) {
            if (report_id < textInfo.getId()){
                report_id = textInfo.getId();
            }
        }
        System.out.println("===========操作系统是:"+System.getProperties().getProperty("os.name"));
        System.out.println(files.size());
        for (MultipartFile file : files){

            if (file.isEmpty()) {
                System.out.println("file is empty");
            }
            String fileName = file.getOriginalFilename();

            fileName = fileName.split("\\.")[0] + System.currentTimeMillis() + "."
                    + fileName.split("\\.")[1];
            File dest = new File(AddressMethod.GeneratorAddress(user_id, fileName));
            //存入数据库的路径path
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                info.setUser_id(user_id);
                file.transferTo(dest);
                String file_addr = AddressMethod.GeneratorAddressOut(user_id, fileName);

                patientUploadFileMapper.insertMedicalExaminationReportAddr(file_addr, report_id);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传失败" );
                flag = 0;
            }
            System.out.println("上传成功" );

        }


        ResponseMessage response = new ResponseMessage();
        if (flag == 1) {
            response.setStatus_code(1);
            return response;
        } else {
            response.setStatus_code(0);
            return response;
        }

    }

    //化验检查
    public ResponseMessage LaboratoryExaminationUpload(List<MultipartFile> files, TextInfo info){
        int flag = 1;

        Long user_id = info.getUserId();
        info.setUser_id(user_id);
        patientUploadFileMapper.insertLaboratoryExamination(info);
        List<LaboratoryPicture> IdReceive= patientUploadFileMapper.selectLaboratoryExaminationId(user_id);
        Long laboratory_id = 0L;
        for (LaboratoryPicture laboratoryPicture : IdReceive) {
            if (laboratory_id < laboratoryPicture.getId()){
                laboratory_id = laboratoryPicture.getId();
            }
        }
        System.out.println("===========操作系统是:"+System.getProperties().getProperty("os.name"));
        System.out.println(files.size());
        for (MultipartFile file : files){

            if (file.isEmpty()) {
                System.out.println("file is empty");
            }
            String fileName = file.getOriginalFilename();

            fileName = fileName.split("\\.")[0] + System.currentTimeMillis() + "."
                    + fileName.split("\\.")[1];
            File dest = new File(AddressMethod.GeneratorAddress(user_id, fileName));
            //存入数据库的路径path
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                info.setUser_id(user_id);
                file.transferTo(dest);
                String file_addr = AddressMethod.GeneratorAddressOut(user_id, fileName);

                patientUploadFileMapper.insertLaboratoryExaminationAddr(file_addr, laboratory_id);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传失败");
                flag = 0;
            }
            System.out.println("上传成功" );

        }


        ResponseMessage response = new ResponseMessage();
        if (flag == 1) {
            response.setStatus_code(1);
            return response;
        } else {
            response.setStatus_code(0);
            return response;
        }
    }

    //影像检查
    public ResponseMessage ImageExaminationUpload(List<MultipartFile> files, TextInfo info){
        int flag = 1;

        Long user_id = info.getUserId();

        info.setUser_id(user_id);
        patientUploadFileMapper.insertImageExamination(info);
        List<ImagePicture> IdReceive= patientUploadFileMapper.selectImageExaminationId(user_id);
        Long image_id = 0L;
        for (ImagePicture imagePicture : IdReceive) {
            if (image_id < imagePicture.getId()){
                image_id = imagePicture.getId();
            }
        }
        System.out.println("===========操作系统是:"+System.getProperties().getProperty("os.name"));
        System.out.println(files.size());
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();

            fileName = fileName.split("\\.")[0] + System.currentTimeMillis() + "."
                    + fileName.split("\\.")[1];
            File dest = new File(AddressMethod.GeneratorAddress(user_id, fileName));
            //存入数据库的路径path
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                info.setUser_id(user_id);
                file.transferTo(dest);
                String file_addr = AddressMethod.GeneratorAddressOut(user_id, fileName);
                System.out.println(file_addr);

                patientUploadFileMapper.insertImageExaminationAddr(file_addr, image_id);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传失败" );
                flag = 0;
            }
            System.out.println("上传成功");
        }


        ResponseMessage response = new ResponseMessage();
        if (flag == 1) {
            response.setStatus_code(1);
            return response;
        } else {
            response.setStatus_code(0);
            return response;
        }
    }

    //侵入型器械检查
    public ResponseMessage InvasiveInstrumentsUpload(List<MultipartFile> files, TextInfo info){
        int flag = 1;

        Long user_id = info.getUserId();
        info.setUser_id(user_id);

        patientUploadFileMapper.insertInvasiveInstruments(info);
        List<InstrumentPicture> IdReceive= patientUploadFileMapper.selectInvasiveInstrumentsId(user_id);
        Long instrument_id = 0L;
        for (InstrumentPicture imagePicture : IdReceive) {
            if (instrument_id < imagePicture.getId()){
                instrument_id = imagePicture.getId();
            }
        }
        System.out.println("===========操作系统是:"+System.getProperties().getProperty("os.name"));
        System.out.println(files.size());
        for (MultipartFile file : files) {


            if (file.isEmpty()) {
                System.out.println("file is empty");
            }
            String fileName = file.getOriginalFilename();

            fileName = fileName.split("\\.")[0] + System.currentTimeMillis() + "."
                    + fileName.split("\\.")[1];
            File dest = new File(AddressMethod.GeneratorAddress(user_id, fileName));
            //存入数据库的路径path
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                info.setUser_id(user_id);
                file.transferTo(dest);
                String file_addr = AddressMethod.GeneratorAddressOut(user_id, fileName);

                patientUploadFileMapper.insertInvasiveInstrumentsAddr(file_addr, instrument_id);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传失败");
                flag = 0;
            }
            System.out.println("上传成功");

        }


        ResponseMessage response = new ResponseMessage();
        if (flag == 1) {
            response.setStatus_code(1);
            return response;
        } else {
            response.setStatus_code(0);
            return response;
        }
    }

    //上传门诊病历
    //门诊病历
    public ResponseMessage outPatientMedicalRecords(List<MultipartFile> files, TextInfo info){

        // if (instrumentation == null)
        //     System.out.println("instrumentation ------   null");
        // else
        //     System.out.println(instrumentation.getObjectSize(files));

        Long user_id = info.getUserId();
        int flag =1;

        info.setUser_id(user_id);
        patientUploadFileMapper.insertOutpatient(info);

        List<OutPatient> IdReceive = patientUploadFileMapper.selectOutPatient(user_id);
        Long outPatient_id = 0L;

        for (OutPatient outPatient : IdReceive) {
            if (outPatient_id < outPatient.getId()){
                outPatient_id = outPatient.getId();
            }
        }
        System.out.println("===========操作系统是:"+System.getProperties().getProperty("os.name"));

        System.out.println(files.size());

        System.out.println(files);

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                System.out.println("file is empty");
            }
            String fileName = file.getOriginalFilename();

            fileName = fileName.split("\\.")[0] + System.currentTimeMillis() + "."
                    + fileName.split("\\.")[1];
            File dest = new File(AddressMethod.GeneratorAddress(user_id, fileName));
            //存入数据库的路径path
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                info.setUser_id(user_id);
                file.transferTo(dest);
                String file_addr = AddressMethod.GeneratorAddressOut(user_id, fileName);

                patientUploadFileMapper.insertOutPatientAddrInfo(file_addr, outPatient_id);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传失败");
                flag = 0;
            }
            System.out.println("上传成功");

        }


        ResponseMessage response = new ResponseMessage();
        if (flag == 1) {
            response.setStatus_code(1);
            return response;
        } else {
            response.setStatus_code(0);
            return response;
        }
    }



}



