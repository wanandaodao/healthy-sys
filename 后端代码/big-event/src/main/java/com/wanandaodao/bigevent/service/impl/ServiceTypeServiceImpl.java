package com.wanandaodao.bigevent.service.impl;

import com.wanandaodao.bigevent.mapper.ServiceTypeMapper;
import com.wanandaodao.bigevent.pojo.ServiceType;
import com.wanandaodao.bigevent.service.ServiceTypeService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {
    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    @Override
    public List<ServiceType> selectAll() {
        return serviceTypeMapper.selectAll();
    }
}
