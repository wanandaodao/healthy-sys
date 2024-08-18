package com.wanandaodao.bigevent.controller;


import com.wanandaodao.bigevent.pojo.AppointmentedInfo;
import com.wanandaodao.bigevent.pojo.DoctorPatientInfo;
import com.wanandaodao.bigevent.pojo.Patient;
import com.wanandaodao.bigevent.pojo.Result;
import com.wanandaodao.bigevent.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @PutMapping
    public Result add(@RequestBody Patient patient){
        patientService.add(patient);
        return Result.success();
    }

    //获取当前用户已预约陪诊信息
    @GetMapping
    public Result<List<AppointmentedInfo>> getInfo(){
        List<AppointmentedInfo> list = patientService.getInfo();
        return Result.success(list);
    }

    //获取当前用户已预约患者信息
    @GetMapping("/patientInfo")
    public Result<List<DoctorPatientInfo>> getPatientInfo(){
        List<DoctorPatientInfo> list = patientService.getPatientInfo();
        return Result.success(list);
    }
}
