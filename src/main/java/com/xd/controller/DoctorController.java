package com.xd.controller;

import com.xd.pojo.Doctor;
import com.xd.pojo.RequestMessage;
import com.xd.pojo.TextInfo;
import com.xd.service.DoctorService;
import com.xd.service.UploadFileService;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@SuppressWarnings("all")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    //医生全部信息填写
    @PostMapping(value = "/DoctorInfo")
    public ResponseMessage DoctorInfo(@RequestParam(value = "files[]") List<MultipartFile> files,
                                      @RequestParam(value = "types[]") List<Long> types,
                                      @RequestParam(value = "userId") Long userId,
                                      @RequestParam(value = "name") String name,
                                      @RequestParam(value = "id_num") String id_num,
                                      @RequestParam(value = "hospital") String hospital,
                                      @RequestParam(value = "department") String department,
                                      @RequestParam(value = "specialty") String specialty,
                                      @RequestParam(value = "personal_info") String personal_info,
                                      @RequestParam(value = "social_work") String social_work){
        TextInfo info = new TextInfo();
        Doctor doctor = new Doctor();

        info.setUserId(userId);
        doctor.setName(name);
        doctor.setId_num(id_num);
        doctor.setSpecialty(specialty);
        doctor.setPersonal_info(personal_info);
        doctor.setSocial_work(social_work);
        doctor.setHospital(hospital);
        doctor.setDepartment(department);

        return doctorService.DoctorInfo(files, doctor, info, types);

    }

    //医生查看跟自己相关联的患者基本信息
    @RequestMapping(value = "/selectDAP",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage DoctorSeclectPatient(@RequestBody RequestMessage message){

        return doctorService.DoctorWatchPatient(message);

    }

    //医生查看某个患者的患病详细信息
    @RequestMapping(value = "/watchPatientInfo",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage WatchPatientsInfo(@RequestBody RequestMessage message){

        return doctorService.WatchPatientHospitalInfo(message);
    }

    //医生查看个人信息
    @RequestMapping(value = "/DoctorInfos", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage DoctorPernalInfo(@RequestBody RequestMessage message){

        return doctorService.DoctorPersonalInfo(message);
    }


}
