package com.xd.mapper;


import com.xd.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface UploadFileMapper {
    //医生图片信息
    @Insert("insert into doctor_addr_info"
            + "(doctor_addr_info, picture_type, doctor_id)"
            + "values"
            + "(#{doctor_addr_info}, #{picture_type}, #{doctor_id})")
    int insertDoctorAddr(String doctor_addr_info, Long picture_type, Long doctor_id);

    //@Update("update doctor_patient_connection set flag = #{flag} where id = #{id}")
    //医生第二次上传更新图片信息
    @Update("update doctor_addr_info set doctor_addr_info = #{doctor_addr_info} where picture_type = #{picture_type} and doctor_id = #{doctor_id}  ")
    int updateDoctorAddr(String doctor_addr_info, Long picture_type, Long doctor_id);

    //疾病信息
    @Insert("insert into disease_picture_info" +
            "( picture_type, user_id, date)" +
            "values" +
            "(#{picture_type}, #{user_id}, #{date})")
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
    List<TextInfo> selectMedicalExaminationReportId(Long user_id);

    @Select("select file_addr from report_addr_info where report_id = #{report_id}")
    List<String> selectAddress(Long report_id);

    @Insert("insert into report_addr_info" +
            "( file_addr, report_id, flag)" +
            "values" +
            "(#{file_addr}, #{report_id}, 0)")
    int insertMedicalExaminationReportAddr(String file_addr, Long report_id);

    //化验检查
    @Insert("insert into laboratory_info" +
            "( date, items, result, user_id)" +
            "values" +
            "(#{date}, #{items}, #{result}, #{user_id})")
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
            "( date, items, result, user_id)" +
            "values" +
            "(#{date}, #{items}, #{result}, #{user_id})")
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
            "( date, items, result, user_id)" +
            "values" +
            "(#{date}, #{items}, #{result}, #{user_id})")
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


}
