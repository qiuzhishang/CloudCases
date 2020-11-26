package com.xd.mapper;

import com.xd.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DoctorMapper {


    //医生登录
    @Select("select * from doctor_info where user_id = #{user_id}")
    Doctor selectDoctorByUserId(Long user_id);

    //查询医生图片信息
    @Select("select * from doctor_addr_info where doctor_id = #{doctor_id}")
    List<DoctorAddr> selectDoctorAddrInfo(Long doctor_id);


    //医生填写个人信息
    @Insert("insert into doctor_info"+
            "(name, id_num, hospital, department, specialty, personal_info, social_work, user_id, flag)"+
            "values"+
            "(#{name}, #{id_num}, #{hospital}, #{department}, #{specialty}, #{personal_info}, #{social_work}, #{user_id}, 0)")
    int insertDoctorInfo(Doctor doctor);

    //医生查询个人信息
    @Select("select * from doctor_info where user_id = #{user_id}")
    Doctor selectDoctorInfo(Long user_id);

    //医生图片信息
    @Insert("insert into doctor_addr_info"
            + "(doctor_addr_info, picture_type, doctor_id)"
            + "values"
            + "(#{doctor_addr_info}, #{picture_type}, #{doctor_id})")
    int insertDoctorAddr(String doctor_addr_info, Long picture_type, Long doctor_id);

    //@Update("update doctor_patient_connection set flag = #{flag} where id = #{id}")
    //医生第二次上传更新图片信息
    @Update("update doctor_addr_info set doctor_addr_info = #{doctor_addr_info} and flag =0 where picture_type = #{picture_type} and doctor_id = #{doctor_id}  ")
    int updateDoctorAddr(String doctor_addr_info, Long picture_type, Long doctor_id);

    @Select("select * from doctor_addr_info where doctor_id=#{doctor_id} and picture_type=#{picture_type}")
    DoctorAddr selectDoctorAddr(Long doctor_id, Long picture_type);

    //医生患者关系
    @Insert("insert into doctor_patient_connection"
            + "(patient_id, doctor_id, flag)"
            + "values"
            + "(#{patient_id}, #{doctor_id}, #{flag})")
    // int insertPatientAndDoctor(PatientAndDoctor patientAndDoctor);
    int insertPatientAndDoctor(Long patient_id, Long doctor_id, int flag);

    @Select("select * from doctor_patient_connection where patient_id = #{patient_id} and doctor_id = #{doctor_id}")
    PatientAndDoctor selectExits(Long patient_id, Long doctor_id);

    @Select("select doctor_id from doctor_patient_connection where patient_id = #{patient_id}")
    List<Long> selectedDoctorId(Long patient_id);

    //医生查找对应的患者
    @Select("select patient_id from doctor_patient_connection where doctor_id = #{doctor_id} and flag = #{flag}")
    List<Long> selectPatient(Long doctor_id, int flag);

    //@Update("update user_enter_info set token = #{token} where phone_num = #{phone_num}")
    @Update("update doctor_patient_connection set flag = #{flag} where id = #{id}")
    int updateFlag(int flag, Long id);

    //医生查看跟自己关联患者的详细信息（模糊查询）
    @Select("<script>select id, name, birthday, id_num,sex,race,birthplace,postal_addr,pre_addr1, pre_addr2, user_id" +
            "FROM patient_info p LEFT JOIN doctor_patient_connection dp ON p.user_id=dp.patient_id " +
            "where"+
            "flag=1 AND doctor_id= #{doctor_id}  " +
            "<if test='name!= null'> and name like concat(#{name}, '%' ) </if>" +
            "<if test='id_num!= null'> and id_num like concat(#{id_num}, '%' ) </if>"+
            "<if test='phone_num!= null'> and phone_num like concat(#{phone_num}, '%' ) </if></script>")
    List<Patient> selectPatients(Long doctor_id,  String name, String id_num, String phone_num);

    //医生查看患者的某一项病历的图片信息

    //report
    @Select("select file_addr from report_addr_info where report_id = #{report_id} ")
    List<String> selectPatientReport(Long report_id);

    @Select("select * from report_info where id=#{id}")
    Report selectReport(Long id);

    //laboratory
    @Select("select file_addr from laboratory_addr_info where laboratory_id = #{laboratory_id} ")
    List<String> selectLaboratoryAddrInfo(Long laboratory_id);

    @Select("select * from laboratory_info where id=#{id}")
    LaboratoryPicture selectLaboratoryInfo(Long id);

    //Instrument
    @Select("select file_addr from instrument_addr_info where instrument_id = #{instrument_id} ")
    List<String> selectInstrumentAddrInfo(Long instrument_id);

    @Select("select * from instrument_info where id=#{id}")
    InstrumentPicture selectInstrumentInfo(Long id);

    //Image
    @Select("select file_addr from image_addr_info where image_id = #{image_id} ")
    List<String> selectImageAddrInfo(Long image_id);

    @Select("select * from image_info where id=#{id}")
    ImagePicture selectImageInfo(Long id);

    //DiseasePicture
    @Select("select file_addr from disease_picture_addr_info where disease_picture_id = #{disease_picture_id} ")
    List<String> selectDiseasePictureAddrInfo(Long disease_picture_id);

    @Select("select * from disease_picture_info where id=#{id}")
    DiseasePicture selectDiseasePictureInfo(Long id);



}
