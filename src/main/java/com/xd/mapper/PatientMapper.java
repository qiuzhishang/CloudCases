package com.xd.mapper;

import com.xd.pojo.Doctor;
import com.xd.pojo.Patient;
import com.xd.pojo.PatientDiseaseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Mapper
@Repository
public interface PatientMapper {

    //病人信息
    @Select("select * from patient_info where id_num = #{id_num}")
    Patient selectPatientByIdNum(String id_num);

    @Select("select * from patient_info where user_id = #{user_id}")
    Patient selectPatientByUserId(Long user_id);

    @Insert("insert into patient_info"+
            "(name, id_num, sex, race, birthplace, postal_addr, now_addr, pre_addr1, pre_addr2, user_id, birthday)"+
            "values"+
            "(#{name}, #{id_num}, #{sex}, #{race}, #{birthplace}, #{postal_addr}, #{now_addr}, #{pre_addr1}, #{pre_addr2}, #{user_id}, #{birthday})")
    int insertPatientInfo(String name, String id_num, int sex, String race, String birthplace, String postal_addr, String now_addr, String pre_addr1, String pre_addr2, Long user_id, Date birthday);

    @Insert("insert into emerge_contact" +
            "(name, phone_num, user_id)" +
            "values" +
            "(#{name}, #{phone_num}, #{user_id})")
    int insertEmergeContactInfo(String name, String phone_num, Long user_id);

    //病症信息
    //DiseaseInfo
    @Insert("insert into disease_info"+
            "(disease_type, disease_info, user_id)"+
            "values"+
            "(#{disease_type}, #{disease_info}, #{user_id})")
    int insertDiseaseInfo(int disease_type, String disease_info, Long user_id);

    @Select("select * from disease_info where user_id = #{user_id}")
    List<PatientDiseaseInfo> selectDiseaseInfo(Long user_id);

    //患者查医生
    @Select("<script>select * " +
            "FROM doctor_info " +
            "where "+
            "flag=1 " +
            "<if test='name!= null'> and name like concat(#{name}, '%' ) </if>" +
            "<if test='hospital!= null'> and hospital = #{hospital} ) </if>"+
            "<if test='department!= null'> and department = #{department} ) </if></script>")
    List<Doctor> patientSelectDoctors(Long userId, String name, String hospital, String department);

    @Select("select doctor_addr_info from doctor_addr_info where doctor_id = #{doctor_id} and picture_type = #{picture_type}")
    String patientSelectDoctorPictureInfoByType(Long doctor_id, Long picture_type);

    @Select("select doctor_id from doctor_patient_connection where patient_id = #{patient_id} and flag =1")
    List<Long> selectedDoctorId(Long patient_id);


}
