package com.wanandaodao.bigevent.controller;


import com.wanandaodao.bigevent.pojo.Department;
import com.wanandaodao.bigevent.pojo.Doctor;
import com.wanandaodao.bigevent.pojo.Result;
import com.wanandaodao.bigevent.service.DoctorService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/department")
    public Result<List<Department>> getDepartment(){
        List<Department> departments = doctorService.getDepartment();
        return Result.success(departments);

    }
    @PutMapping()
    public Result add(@RequestBody Doctor doctor){
        doctorService.add(doctor);
        return Result.success();
    }

    @PutMapping("/add")
    public Result addWithoutUserId(@RequestBody Doctor doctor){
        doctorService.addWithoutUserId(doctor);
        return Result.success();
    }

    @GetMapping
    public Result<List<Doctor>> getDoctor(){
        List<Doctor> doctors = doctorService.get();
        return Result.success(doctors);
    }

    @PostMapping
    public Result update(@RequestBody Doctor doctor){
        doctorService.update(doctor);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(String id){
        doctorService.delete(id);
        return Result.success();
    }
}
