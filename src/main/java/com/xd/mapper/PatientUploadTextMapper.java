package com.xd.mapper;

import com.xd.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface PatientUploadTextMapper {


    //住院病历
    @Insert("insert into admission_info" +
            "( department_treatment, hospital, doctor_name, admission_info, s_date, o_date, user_id, flag)" +
            "values" +
            "( #{department_treatment}, #{hospital}, #{doctor_name}, #{admission_info}, #{s_date}, #{o_date}, #{user_id}, 0)")
    int insertAdmission(String department_treatment, String hospital, String doctor_name, String admission_info, Date s_date, Date o_date, Long user_id);

    //查询住院病历
    @Select("select * from admission_info where user_id = #{user_id}")
    List<AdmissionNote> selectAdmissionNote(Long user_id);

    //门诊就诊记录
    @Insert("insert into outpatient_records" +
            "( department_treatment, hospital, disease_info, doctor_name, treat_info, treating_info, treat_items, treat_methods, date, user_id, flag)" +
            "values" +
            "( #{department_treatment}, #{hospital}, #{disease_info}, #{doctor_name}, #{treat_info}, #{treating_info}, #{treat_items}, #{treat_methods}, #{date}, #{user_id}, 0)")
    int insertOutPatientRecords(String department_treatment, String hospital, String disease_info, String doctor_name, String treat_info, String treating_info, String treat_items, String treat_methods, Date date, Long user_id);
//            int insertOutPatientRecords(OutPatientRecords outPatientRecords, Long user_id);

    @Select("select * from outpatient_records where user_id = #{user_id}")
    List<OutPatientRecords> selectOutPatientRecords(Long user_id);

    @Insert("insert into medicine_info " +
            "(medicine_name, medicine_method, time, treat_id)" +
            " values " +
            "(#{medicine_name}, #{medicine_method}, #{time}, #{treat_id}) ")
    int insertMedicine(String medicine_name, String medicine_method, String time, Long treat_id);

    @Select("select * from medicine_info where treat_id = #{treat_id}")
    List<Medicine> selectMedicine(Long treat_id);

    //病理学检查
    @Insert("insert into disease_examine_info"
            +"(examine_info, date, user_id, flag)"
            + "values"
            +"(#{examine_info}, #{date}, #{user_id}, 0)")
    int insertExamineInfo(String examine_info, Date date, Long user_id);

    //查询病理学检查
    @Select("select * from disease_examine_info where user_id = #{user_id}")
    List<Examine> selectExamine(Long user_id);

}
