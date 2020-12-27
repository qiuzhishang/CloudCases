package com.xd.mapper;

import com.xd.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
//医生查询患者的各项文本的信息
public interface DoctorSelectPatientTextInfo {

    @Select("select * from admission_info where user_id = #{user_id} and flag = 1")
    List<AdmissionNote> doctorSelectAdmissionNote(Long user_id);

    @Select("select * from outpatient_info where user_id = #{user_id} and flag = 1")
    List<OutPatient> doctorSelectOutPatient(Long user_id);

    @Select("select * from outpatient_records where user_id = #{user_id} and flag = 1")
    List<OutPatientRecords> doctorSelectOutPatientRecords(Long user_id);

    @Select("select * from disease_examine_info where user_id = #{user_id} and flag = 1")
    List<Examine> doctorSelectExamine(Long user_id);

    @Select("select * from disease_picture_info where user_id = #{user_id} and flag = 1")
    List<DiseasePicture> doctorSelectDiseasePicture(Long user_id);

    @Select("select * from report_info where user_id = #{user_id} and flag = 1")
    List<Report> doctorSelectMedicalExaminationReportId(Long user_id);

    @Select("select * from laboratory_info where user_id = #{user_id} and flag = 1")
    List<LaboratoryPicture> doctorSelectLaboratoryExaminationId(Long user_id);

    @Select("select * from image_info where user_id = #{user_id} and flag = 1")
    List<ImagePicture> doctorSelectImageExaminationId(Long user_id);

    @Select("select * from instrument_info where user_id = #{user_id} and flag = 1")
    List<InstrumentPicture> doctorSelectInvasiveInstrumentsId(Long user_id);

    @Select("select * from disease_info where user_id = #{user_id} and flag =1")
    List<PatientDiseaseInfo> doctorSelectPatientDiseaseInfo(Long user_id);

}
