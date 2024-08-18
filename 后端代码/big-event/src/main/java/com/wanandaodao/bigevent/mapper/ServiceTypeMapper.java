package com.wanandaodao.bigevent.mapper;


import com.wanandaodao.bigevent.pojo.ServiceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ServiceTypeMapper {
    @Select("select * from service_type")
    List<ServiceType> selectAll();
}
