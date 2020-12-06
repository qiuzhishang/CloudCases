package com.xd.service;

import com.xd.mapper.DoctorMapper;
import com.xd.mapper.PatientMapper;
import com.xd.mapper.RegisterMapper;
import com.xd.mapper.PatientUploadFileMapper;
import com.xd.pojo.*;
import com.xd.utils.ResponseMessage;
import com.xd.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.SpanIterator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class PatientService {

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private PatientUploadFileMapper patientUploadFileMapper;


    public List<Doctor> selectAllDoctor(RequestMessage message) {

        List<Doctor> doctors = patientMapper.patientSelectDoctors(message.getUserId(), message.getDoctor().getName(),
                message.getDoctor().getHospital(), message.getDoctor().getDepartment());

        System.out.println(message.getDoctor().getHospital() + message.getDoctor().getName() + message.getDoctor().getDepartment());

        for (Doctor doctor : doctors)

            //picture_type=1L表示1是Long型，医生的证件照
            doctor.setAddr(patientMapper.patientSelectDoctorPictureInfoByType(doctor.getId(), 1L));


        return doctors;

    }


    //患者完善个人信息
    public ResponseMessage patientInsertInfo(RequestMessage message){

        Patient user = patientMapper.selectPatientByIdNum(message.getPatient().getId_num());
        System.out.println(user);
        if (user != null){
            ResponseMessage response = new ResponseMessage();
            response.setStatus_code(0);//身份证信息已存在
            return response;
        }
        if ((message.getPatient().getSex() != 0 || message.getPatient().getSex() != 1) &&
                message.getPatient().getName()       !=null &&
                message.getPatient().getId_num()     !=null &&
                message.getPatient().getBirthplace() !=null &&
                message.getPatient().getEmerge()     !=null &&
                message.getPatient().getNow_addr()   !=null &&
                message.getPatient().getPostal_addr()!=null &&
                message.getPatient().getRace()       != null &&
                message.getPatient().getBirthday() !=null) {

            Long id = message.getUserId();
            message.getPatient().setUser_id(id);

            String date = message.getPatient().getBirthday();

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date sDate = null;
            try {
                java.util.Date date3 = sdf2.parse(date);
                sDate = new java.sql.Date(date3.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            patientMapper.insertPatientInfo(message.getPatient().getName(),
                    message.getPatient().getId_num(),
                    message.getPatient().getSex(),
                    message.getPatient().getRace(),
                    message.getPatient().getBirthplace(),
                    message.getPatient().getPostal_addr(),
                    message.getPatient().getNow_addr(),
                    message.getPatient().getPre_addr1(),
                    message.getPatient().getPre_addr2(),
                    message.getPatient().getUser_id(),
                    sDate);

            List<Emerge> emerge = message.getPatient().getEmerge();
            System.out.println(emerge);
            for (Emerge em : emerge) {
                patientMapper.insertEmergeContactInfo(em.getName(),em.getPhone_num(),id);
            }


            ResponseMessage response = new ResponseMessage();
            response.setStatus_code(1);//信息填入成功

            response.setName(message.getPatient().getName());
            response.setSex(message.getPatient().getSex());
            return response;
        }
        else {
            ResponseMessage response = new ResponseMessage();
            response.setStatus_code(2);//必填信息不全
            return response;
        }
    }

    //患者选择医生
    public ResponseMessage PatientSelectDoctor(RequestMessage message) {

        Register register = registerMapper.selectUserByUserId(message.getUserId());

        System.out.println(register);

        if (register == null){
            return null;
        }

        Long patient_id = register.getUser_id();

        System.out.println("patient id ========="+ patient_id);

        List<Long> defectList ;//差集List
        List<Long> collectionList;//交集List

        List<Long> doctors_id = message.getAdd_doctor_id();//获取的要添加的医生id
        System.out.println(doctors_id);

        List<Long> selected_id = doctorMapper.selectedDoctorId(patient_id);//已经存在列表的医生id


        if (selected_id.size() == 0) {
            for (Long doctor_id : doctors_id) {

                try {

                    System.out.println(doctor_id);
                    System.out.println("==============" + patient_id + "==================");

                    doctorMapper.insertPatientAndDoctor(patient_id, doctor_id, 1);
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        } else {

            //获取交集
            collectionList = Tools.receiveCollectionList(selected_id, doctors_id);
            System.out.println("-----------------------------");
            System.out.println(collectionList);
            System.out.println("-----------------------------");

            System.out.println("===================交集===================");
            System.out.println(collectionList);

            for (Long aLong : collectionList) {

                System.out.println("----"+ aLong + "------");

                System.out.println("==============" + patient_id);

                PatientAndDoctor patientAndDoctor = doctorMapper.selectExits(patient_id, aLong);

                doctorMapper.updateFlag(1, patientAndDoctor.getId());

            }


            //获取差集
            defectList = Tools.receiveDefectList(selected_id, collectionList);
            for (Long aLong : defectList) {

                System.out.println("----"+ aLong + "------");

                System.out.println("==============" + patient_id);

                PatientAndDoctor patientAndDoctor = doctorMapper.selectExits(patient_id, aLong);

                doctorMapper.updateFlag(0, patientAndDoctor.getId());

            }

            defectList = Tools.receiveDefectList(doctors_id, collectionList);

            for (Long aLong : defectList) {

                System.out.println("----"+ aLong + "------");

                doctorMapper.insertPatientAndDoctor(patient_id, aLong, 1);

            }
        }

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus_code(1);

        return responseMessage;

    }

    //患者既往病史
    public ResponseMessage PatientDiseaseInfo(RequestMessage message){

        ResponseMessage response = new ResponseMessage();

        //int insertDiseaseInfo(int disease_type, String disease_info, Long user_id);
        patientMapper.insertDiseaseInfo(message.getPatientDiseaseInfo().getDisease_type(), message.getPatientDiseaseInfo().getDisease_info(),message.getUserId());

        response.setStatus_code(1);
        return response;
    }


    //查找所有图片
    public ResponseMessage selectAllPicture(RequestMessage message){

        ResponseMessage response = new ResponseMessage();

        List<Report> reports = selectReportPicture(message);
        List<DiseasePicture> diseasePictures = selectDiseasePicture(message);
        List<LaboratoryPicture> laboratoryPictures = selectLaboratoryPicture(message);
        List<ImagePicture> imagePictures = selectImagePicture(message);
        List<InstrumentPicture> instrumentPictures = selectInstrumentPicture(message);

        response.setReports(reports);
        response.setDiseasePictures(diseasePictures);
        response.setLaboratoryPictures(laboratoryPictures);
        response.setImagePictures(imagePictures);
        response.setInstrumentPictures(instrumentPictures);


        return response;

    }

    //查找体检报告
    public List<Report> selectReportPicture(RequestMessage message){

        Long user_id = message.getUserId();

        List<Report> reports = patientUploadFileMapper.selectMedicalExaminationReportId(user_id);

        for (Report report : reports) {
            System.out.println(report.getId());
            List<String> address = patientUploadFileMapper.selectAddress(report.getId());
            System.out.println(address);
            report.setAddress(address);
        }

        return reports;
    }

    //查找病历照片
    public List<DiseasePicture> selectDiseasePicture(RequestMessage message){

        Long user_id = message.getUserId();

        List<DiseasePicture> diseasePictures;
        diseasePictures = patientUploadFileMapper.selectDiseasePicture(user_id);

        for (DiseasePicture diseasePicture : diseasePictures) {
            System.out.println(diseasePicture.getId());
            List<String> address = patientUploadFileMapper.selectDiseasePictureAddr(diseasePicture.getId());
            System.out.println(address);
            diseasePicture.setAddress(address);
        }

        return diseasePictures;

    }

    //查找化验检查
    public List<LaboratoryPicture> selectLaboratoryPicture(RequestMessage message){

        Long user_id = message.getUserId();

        List<LaboratoryPicture> laboratoryPictures;
        laboratoryPictures = patientUploadFileMapper.selectLaboratoryExaminationId(user_id);
        ResponseMessage response = new ResponseMessage();
        for (LaboratoryPicture laboratoryPicture : laboratoryPictures) {
            System.out.println(laboratoryPicture.getId());
            List<String> address = patientUploadFileMapper.selectLaboratoryAddress(laboratoryPicture.getId());
            System.out.println(address);
            laboratoryPicture.setAddress(address);
        }

        return laboratoryPictures;
    }

    //查找影像检查
    public List<ImagePicture> selectImagePicture(RequestMessage message){

        Long user_id = message.getUserId();


        List<ImagePicture> imagePictures;
        imagePictures = patientUploadFileMapper.selectImageExaminationId(user_id);
        ResponseMessage response = new ResponseMessage();
        for (ImagePicture imagePicture : imagePictures) {
            System.out.println(imagePicture.getId());
            List<String> address = patientUploadFileMapper.selectImageExaminationAddress(imagePicture.getId());
            System.out.println(address);
            imagePicture.setAddress(address);
        }

        return imagePictures;
    }

    //侵入型器械检查
    public List<InstrumentPicture> selectInstrumentPicture(RequestMessage message){

        Long user_id = message.getUserId();

        List<InstrumentPicture> instrumentPictures;
        instrumentPictures = patientUploadFileMapper.selectInvasiveInstrumentsId(user_id);

        for (InstrumentPicture instrumentPicture : instrumentPictures) {
            System.out.println(instrumentPicture.getId());

            System.out.println(patientUploadFileMapper.selectInvasiveInstrumentsAddress(instrumentPicture.getId()));
            instrumentPicture.setAddress(patientUploadFileMapper.selectInvasiveInstrumentsAddress(instrumentPicture.getId()));
        }

        return instrumentPictures;
    }
    //查询门诊病历
    public List<OutPatient> selectOutPatient(RequestMessage message){

        Long user_id = message.getUserId();

        List<OutPatient> outPatients = patientUploadFileMapper.selectOutPatient(user_id);

        for (OutPatient outPatient : outPatients) {

            outPatient.setAddress(patientUploadFileMapper.selectOutPatientAddrInfo(outPatient.getId()));
            System.out.println(patientUploadFileMapper.selectOutPatientAddrInfo(outPatient.getId()));
        }

        return outPatients;
    }
}
