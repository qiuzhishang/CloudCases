package com.xd.service;

import com.xd.mapper.UploadFileMapper;
import com.xd.mapper.UserInfoMapper;
import com.xd.pojo.*;
import com.xd.utils.AddressMethod;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("all")
@Service
public class UploadFileService {
    @Autowired
    UploadFileMapper uploadFileMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

//    public String fileUpload(MultipartFile file, String phone_num, int picture_type, Date date) {
//        String filePath = "D:\\picture\\";
//        String picture_addr = "";
//        int flag = 1;
//        Register user = userInfoMapper.selectUserByPhoneNum(phone_num);
//        Long id = user.getId();
//
//        File dir = new File(filePath + id);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        String fileName = file.getOriginalFilename();
//        System.out.println(fileName);
//        fileName = fileName.split("\\.")[0] + System.currentTimeMillis() + "."
//                + fileName.split("\\.")[1];
//        System.out.println("fileName:" + fileName);
//        File dest = new File(filePath + id + "\\" + fileName);
//        try {
//            // 将获取到的附件file,transferTo写入到指定的位置(即:创建dest时，指定的路径)
//            file.transferTo(dest);
//        } catch (IllegalStateException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            System.out.println("上传失败");
//            flag = 0;
//        }
//        picture_addr += filePath + id + "\\" + fileName;
//        System.out.println("文件的全路径名字(含路径、后缀)>>>>>>>" + filePath + id + "\\" + fileName);
////        }
//
//        upFileMapper.insertPictureInfo(picture_addr, picture_type, id, date);
//        System.out.println(picture_addr + picture_type + phone_num);
//        if (flag == 1)
//            return "{\"address\":\"" + picture_addr + "\"}";
//        return null;
//    }

    //病症图片
    public ResponseMessage DiseasePictureUpload(List<MultipartFile> files, TextInfo info) {

        int flag = 1;

        Register user = userInfoMapper.selectUserByPhoneNum(info.getPhone_num());
        Long user_id = user.getId();

        System.out.println(files.size());
        for (MultipartFile file : files)

         {
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
                info.setUser_id(user_id);
                uploadFileMapper.insertPictureInfo(info);
                List<DiseasePicture> diseasePictures = uploadFileMapper.selectDiseasePicture(user_id);
                Long max = 0L;
                for (DiseasePicture diseasePicture : diseasePictures) {
                    if (max < diseasePicture.getId())
                        max = diseasePicture.getId();
                }
                uploadFileMapper.insertPictureAddrInfo(file_addr, max);


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

        Register user = userInfoMapper.selectUserByPhoneNum(info.getPhone_num());
        Long user_id = user.getId();
        info.setUser_id(user_id);
        uploadFileMapper.insertMedicalExaminationReport(info);
        List<TextInfo> IdReceive= uploadFileMapper.selectMedicalExaminationReportId(user_id);
        Long max = 0L;
        for (TextInfo textInfo : IdReceive) {
            if (max < textInfo.getId()){
                max = textInfo.getId();
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

                uploadFileMapper.insertMedicalExaminationReportAddr(file_addr, max);

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

        Register user = userInfoMapper.selectUserByPhoneNum(info.getPhone_num());
        Long user_id = user.getId();
        info.setUser_id(user_id);
        uploadFileMapper.insertLaboratoryExamination(info);
        List<LaboratoryPicture> IdReceive= uploadFileMapper.selectLaboratoryExaminationId(user_id);
        Long max = 0L;
        for (LaboratoryPicture laboratoryPicture : IdReceive) {
            if (max < laboratoryPicture.getId()){
                max = laboratoryPicture.getId();
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

                uploadFileMapper.insertLaboratoryExaminationAddr(file_addr, max);

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

        Register user = userInfoMapper.selectUserByPhoneNum(info.getPhone_num());
        Long user_id = user.getId();
        info.setUser_id(user_id);
        uploadFileMapper.insertImageExamination(info);
        List<ImagePicture> IdReceive= uploadFileMapper.selectImageExaminationId(user_id);
        Long max = 0L;
        for (ImagePicture imagePicture : IdReceive) {
            if (max < imagePicture.getId()){
                max = imagePicture.getId();
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

                uploadFileMapper.insertImageExaminationAddr(file_addr, max);

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

        Register user = userInfoMapper.selectUserByPhoneNum(info.getPhone_num());
        Long user_id = user.getId();
        info.setUser_id(user_id);
        uploadFileMapper.insertInvasiveInstruments(info);
        List<InstrumentPicture> IdReceive= uploadFileMapper.selectInvasiveInstrumentsId(user_id);
        Long max = 0L;
        for (InstrumentPicture imagePicture : IdReceive) {
            if (max < imagePicture.getId()){
                max = imagePicture.getId();
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

                uploadFileMapper.insertInvasiveInstrumentsAddr(file_addr, max);

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



