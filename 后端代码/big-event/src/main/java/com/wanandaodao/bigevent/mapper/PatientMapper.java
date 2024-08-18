package com.wanandaodao.bigevent.mapper;

import com.wanandaodao.bigevent.pojo.Patient;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface PatientMapper {

    void add(Patient patient);

    @Select("select * from patient where user_id = #{id}")
    List<Patient> getInfoById(int id);

    @Select("select * from patient where id = #{id}")
    Patient selectById(int id);
}
