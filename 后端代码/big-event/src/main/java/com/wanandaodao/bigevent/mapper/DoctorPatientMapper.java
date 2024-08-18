package com.wanandaodao.bigevent.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DoctorPatientMapper {

    @Insert("insert into doctor_patient(patient_id, doctor_id) value (#{id},#{doctorId})")
    void add(Integer id, Integer doctorId);

    @Select("select patient_id from doctor_patient where doctor_id = #{id}")
    List<Integer> selectPatientIdsByDoctorId(int id);
}
