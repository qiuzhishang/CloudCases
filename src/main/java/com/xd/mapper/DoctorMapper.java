package com.xd.mapper;

import com.xd.pojo.Doctor;
import com.xd.pojo.DoctorAddr;
import com.xd.pojo.Patient;
import com.xd.pojo.PatientAndDoctor;
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

    //查询医生
    @Select("select * from doctor_info where id_num = #{id_num}")
    Doctor selectDoctorByIdNum(String id_num);

    @Select("select * from doctor_info")
    List<Doctor> selectAllDoctor();

    @Select("select doctor_id from doctor_patient_connection where patient_id = #{patient_id} and flag = #{flag}")
    List<Long> selectDoctorId(Long patient_id, int flag);

    //医生填写个人信息
    @Insert("insert into doctor_info"+
            "(name, id_num, hospital, department, specialty, personal_info, social_work, user_id)"+
            "values"+
            "(#{name}, #{id_num}, #{hospital}, #{department}, #{specialty}, #{personal_info}, #{social_work}, #{user_id})")
    int insertDoctorInfo(Doctor doctor);

    //医生查询个人信息
    @Select("select * from doctor_info where user_id = #{user_id}")
    Doctor selectDoctorInfo(Long user_id);

    //医生图片信息
    @Insert("insert into doctor_addr_info"
            + "(doctor_addr_info, picture_type, doctor_id, flag)"
            + "values"
            + "(#{doctor_addr_info}, #{picture_type}, #{doctor_id}, 0)")
    int insertDoctorAddr(String doctor_addr_info, Long picture_type, Long doctor_id);

    //@Update("update doctor_patient_connection set flag = #{flag} where id = #{id}")
    //医生第二次上传更新图片信息
    @Update("update doctor_addr_info set doctor_addr_info = #{doctor_addr_info} where picture_type = #{picture_type} and doctor_id = #{doctor_id}  ")
    int updateDoctorAddr(String doctor_addr_info, Long picture_type, Long doctor_id);

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


}
