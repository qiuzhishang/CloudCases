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
public interface PatientUploadFileMapper {


    //疾病信息
    @Insert("insert into disease_picture_info" +
            "( picture_type, information, user_id, date, flag)" +
            "values" +
            "(#{picture_type}, #{information}, #{user_id}, #{date}, 0)")
    int insertPictureInfo(TextInfo textInfo);


    @Select("select * from disease_picture_info where user_id = #{user_id}")
    List<DiseasePicture> selectDiseasePicture(Long user_id);

    @Insert("insert into disease_picture_addr_info" +
            "(file_addr, disease_picture_id, flag)" +
            "values" +
            "(#{file_addr}, #{disease_picture_id}, 0)")

    int insertPictureAddrInfo(String file_addr, Long disease_picture_id);


    @Select("select file_addr from disease_picture_addr_info where disease_picture_id = #{disease_picture_id}")
    List<String> selectDiseasePictureAddr(Long disease_picture_id);


    //体检报告
    @Insert("insert into report_info" +
            "( date, hospital, report_info, result, user_id, flag)" +
            "values" +
            "(#{date}, #{hospital}, #{report_info}, #{result}, #{user_id}, 0)")
    int insertMedicalExaminationReport(TextInfo info);

    @Select("select * from report_info where user_id = #{user_id}")
    List<Report> selectMedicalExaminationReportId(Long user_id);

    @Select("select file_addr from report_addr_info where report_id = #{report_id}")
    List<String> selectAddress(Long report_id);

    @Insert("insert into report_addr_info" +
            "( file_addr, report_id, flag)" +
            "values" +
            "(#{file_addr}, #{report_id}, 0)")
    int insertMedicalExaminationReportAddr(String file_addr, Long report_id);

    //化验检查
    @Insert("insert into laboratory_info" +
            "( date, items, result, user_id, flag)" +
            "values" +
            "(#{date}, #{items}, #{result}, #{user_id}, 0)")
    int insertLaboratoryExamination(TextInfo info);

    @Select("select * from laboratory_info where user_id = #{user_id}")
    List<LaboratoryPicture> selectLaboratoryExaminationId(Long user_id);


    @Insert("insert into laboratory_addr_info" +
            "( file_addr, laboratory_id, flag)" +
            "values" +
            "(#{file_addr}, #{laboratory_id}, 0)")
    int insertLaboratoryExaminationAddr(String file_addr, Long laboratory_id);

    @Select("select file_addr from laboratory_addr_info where laboratory_id = #{laboratory_id}")
    List<String> selectLaboratoryAddress(Long report_id);

    //影像检查
    @Insert("insert into image_info" +
            "( date, items, result, user_id, flag)" +
            "values" +
            "(#{date}, #{items}, #{result}, #{user_id}, 0)")
    int insertImageExamination(TextInfo info);

    @Select("select * from image_info where user_id = #{user_id}")
    List<ImagePicture> selectImageExaminationId(Long user_id);

    @Insert("insert into image_addr_info" +
            "( file_addr, image_id, flag)" +
            "values" +
            "(#{file_addr}, #{image_id}, 0)")
    int insertImageExaminationAddr(String file_addr, Long image_id);

    @Select("select file_addr from image_addr_info where image_id = #{image_id}")
    List<String> selectImageExaminationAddress(Long image_id);

    //侵入器械检查
    @Insert("insert into instrument_info" +
            "( date, items, result, user_id, flag)" +
            "values" +
            "(#{date}, #{items}, #{result}, #{user_id}, 0)")
    int insertInvasiveInstruments(TextInfo info);

    @Select("select * from instrument_info where user_id = #{user_id}")
    List<InstrumentPicture> selectInvasiveInstrumentsId(Long user_id);

    @Insert("insert into instrument_addr_info" +
            "( file_addr, instrument_id, flag)" +
            "values" +
            "(#{file_addr}, #{instrument_id}, 0)")
    int insertInvasiveInstrumentsAddr(String file_addr, Long instrument_id);

    @Select("select file_addr from image_addr_info where image_id = #{image_id}")
    List<String> selectInvasiveInstrumentsAddress(Long image_id);

    //门诊病历图片上传

    //门诊病历
    @Insert("insert into outpatient_info" +
            "( date, department, hospital, doctor_name, disease_info,  user_id, flag)" +
            "values" +
            "( #{date}, #{department}, #{hospital}, #{doctor_name}, #{disease_info},  #{user_id}, 0)")
    int insertOutpatient(TextInfo info);

    @Insert("insert into outpatient_addr_info "
            +"(file_addr, outpatient_id)"
            +"values"
            +"(#{file_addr}, #{outpatient_id})")
    int insertOutPatientAddrInfo(String file_addr, Long outpatient_id);

    @Select("select file_addr from outpatient_addr_info where outpatient_id = #{outpatient_id}")
    List<String> selectOutPatientAddrInfo(Long outpatient_id);


    @Select("select * from outpatient_info where user_id = #{user_id}")
    List<OutPatient> selectOutPatient(Long user_id);


}
