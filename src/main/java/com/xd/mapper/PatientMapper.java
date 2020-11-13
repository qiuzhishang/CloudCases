package com.xd.mapper;

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

}
