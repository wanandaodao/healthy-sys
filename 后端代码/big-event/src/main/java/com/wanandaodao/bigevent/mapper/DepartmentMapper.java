package com.wanandaodao.bigevent.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper {

    @Select("select name from department where id = #{id}")
    String getDepartmentNameById(int id);
}
