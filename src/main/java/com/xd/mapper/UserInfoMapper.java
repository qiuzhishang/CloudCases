package com.xd.mapper;


import com.xd.pojo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserInfoMapper {

    @Select("select * from user_enter_info where id = #{id}")
    Register selectPhoneNum(Long id);

    //Doctor


    //DiseaseInfo
    @Insert("insert into disease_info"+
            "(disease_type, disease_info, user_id)"+
            "values"+
            "(#{disease_type}, #{disease_info}, #{user_id})")
    int insertDiseaseInfo(int disease_type, String disease_info, Long user_id);

    @Select("select * from disease_info where user_id = #{user_id}")
    List<PatientDiseaseInfo> selectDiseaseInfo(Long user_id);

    //Token
    @Select("select * from user_enter_info where phone_num = #{phone_num}")
    Register selectToken(String phone_num);
    @Update("update user_enter_info set token = #{token} where id = #{id}")
    int updateToken(String token, Long id);

    @Select("select * from user_enter_info where phone_num = #{phone_num}")
    Register selectUserByPhoneNum(String phone_num);

    @Insert("insert into user_enter_info"+
            "(phone_num, pass_word,  user_type, token, time, user_id)"+
            "values"+
            "(#{phone_num}, #{pass_word}, #{user_type}, #{token}, #{time}, #{user_id})")
    int insertUserInfo(Register register);

    @Update("update user_enter_info set user_id = #{id} where phone_num = #{phone_num}")
    Long updateUserIdByPhone(Long id, String phone_num);

    //病人信息
    @Select("select * from patient_info where id_num = #{id_num}")
    Patient selectPatientByIdNum(String id_num);

    @Select("select * from patient_info where user_id = #{user_id}")
    Patient selectPatientByUserId(Long user_id);

    @Insert("insert into patient_info"+
            "(name, id_num, sex, race, birthplace, postal_addr, now_addr, pre_addr1, pre_addr2, user_id)"+
            "values"+
            "(#{name}, #{id_num}, #{sex}, #{race}, #{birthplace}, #{postal_addr}, #{now_addr}, #{pre_addr1}, #{pre_addr2}, #{user_id})")
    int insertPatientInfo(Patient patient);

    @Insert("insert into emerge_contact" +
            "(name, phone_num, user_id)" +
            "values" +
            "(#{name}, #{phone_num}, #{user_id})")
    int insertEmergeContactInfo(String name, String phone_num, Long user_id);





}
