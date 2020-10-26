package com.xd.mapper;

import com.xd.pojo.Doctor;
import com.xd.pojo.DoctorAddr;
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
            "(name, id_num, specialty, personal_info, social_work, user_id)"+
            "values"+
            "(#{name}, #{id_num}, #{specialty}, #{personal_info}, #{social_work}, #{user_id})")
    int insertDoctorInfo(Doctor doctor);

    //医生第二次上传图片执行更新操作


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

}
