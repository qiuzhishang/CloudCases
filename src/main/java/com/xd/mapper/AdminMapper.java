package com.xd.mapper;

import com.xd.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {

    @Select("select * from admission_info")
    List<AdmissionNote> selectAllAdmission();

    @Select("select * from disease_examine_info")
    List<Examine> selectAllExamine();

    @Select("select * from disease_info")
    List<PatientDiseaseInfo> selectAllDiseaseInfo();

    @Select("select * from outpatient_records ")
    List<OutPatientRecords> selectAllOutPatientRecords();

    @Select("select * from disease_picture_info")
    List<DiseasePicture> selectAllDiseasePicture();
    @Select("Select file_addr from disease_picture_addr_info where disease_picture_id = #{disease_picture_id}")
    List<String> selectAllDiseasePictureAddr(Long disease_picture_id);

    @Select("select * from doctor_info")
    List<Doctor> selectAllDoctor();
    @Select("select * from doctor_addr_info where doctor_id = #{doctor_id}")
    List<DoctorAddr> selectAllDoctorAddrInfo(Long doctor_id);

    @Select("select * from image_info")
    List<ImagePicture> selectAllImage();
    @Select("select file_addr from image_addr_info where image_id = #{image_id}")
    List<String> selectAllImageAddr(Long image_id);

    @Select("select * from instrument_info")
    List<InstrumentPicture> selectAllInstrumentPicture();
    @Select("select file_addr from instrument_addr_info where instrument_id = #{instrument_id}")
    List<String> selectAllInstrumentAddr(Long instrument_id);

    @Select("select * from laboratory_info ")
    List<LaboratoryPicture> SelectAllLaboratoryPicture();
    @Select("select file_addr from laboratory_addr_info where laboratory_id = #{laboratory_id}")
    List<String> selectAllLaboratoryAddr(Long laboratory_id);

    @Select("select * from outpatient_info ")
    List<OutPatient> selectAllOutPatient();
    @Select("select file_addr from outpatient_addr_info where outpatient_id = #{outpatient_id}")
    List<String> selectAllOutPatientAddr(Long outpatient_id);

    @Select("select * from report_info ")
    List<Report> selectAllReport();
    @Select("select file_addr from report_addr_info where report_id = #{report_id}")
    List<String> selectAllReportAddr(Long report_id);

    @Update("update ${table_name} set flag = #{flag}, content= #{content} where id = #{id}")
    int updateAdmission(String table_name, int flag, String content, Long id);

}
