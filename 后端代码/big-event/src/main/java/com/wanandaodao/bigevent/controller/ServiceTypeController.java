package com.wanandaodao.bigevent.controller;


import com.wanandaodao.bigevent.pojo.Result;
import com.wanandaodao.bigevent.pojo.ServiceType;
import com.wanandaodao.bigevent.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/serviceType")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @GetMapping
    public Result<List<ServiceType>>getall(){
        List<ServiceType>list = serviceTypeService.selectAll();
        return Result.success(list);
    }
}
