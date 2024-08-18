package com.wanandaodao.bigevent.mapper;


import com.wanandaodao.bigevent.pojo.Department;
import com.wanandaodao.bigevent.pojo.Doctor;
import org.apache.ibatis.annotations.*;

import javax.print.Doc;
import java.util.List;

@Mapper
public interface DoctorMapper {

    @Select("select * from department")
    List<Department> getDepartment();


    @Insert("insert into doctor(user_id, phone, picture, gender, age, name, department_id, id_card)values" +
            "(#{userId},#{phone},#{picture},#{gender},#{age},#{name},#{departmentId},#{idCard})")
    void add(Doctor doctor);

    @Select("select * from doctor")
    List<Doctor> get();


    @Update("update doctor set name = #{name},age = #{age},phone = #{phone},id_card = #{idCard},department_id = " +
            "#{departmentId},gender = #{gender} where id = #{id}")
    void update(Doctor doctor);


    @Delete("delete from doctor where id = #{id}")
    void delete(String id);

    @Select("select name from doctor where id = #{id}")
    String selectNameById(Integer id);

    @Select("select id from doctor where user_id = #{id}")
    Integer selectIdByUserId(Integer id);

    @Select("select phone from doctor where id = #{doctorId}")
    String selectPhoneById(Integer doctorId);

    @Select("select * from doctor where user_id = #{id}")
    Doctor selectByUserId(Integer id);
}
