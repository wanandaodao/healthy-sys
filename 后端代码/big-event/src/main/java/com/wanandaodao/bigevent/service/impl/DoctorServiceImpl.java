package com.wanandaodao.bigevent.service.impl;

import com.wanandaodao.bigevent.mapper.DepartmentMapper;
import com.wanandaodao.bigevent.mapper.DoctorMapper;
import com.wanandaodao.bigevent.mapper.UserMapper;
import com.wanandaodao.bigevent.pojo.Department;
import com.wanandaodao.bigevent.pojo.Doctor;
import com.wanandaodao.bigevent.service.DoctorService;
import com.wanandaodao.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Map;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Department> getDepartment() {
        return doctorMapper.getDepartment();
    }

    @Override
    public void add(Doctor doctor) {
        Map<String,Object> map= ThreadLocalUtil.get();
        int id = (int)map.get("id");
        //判断当前用户是否已经申请过陪诊师
        Doctor doc = doctorMapper.selectByUserId(id);
        if(doc!=null){
            doctor.setId(doc.getId());
            doctorMapper.update(doctor);
        }else{
            doctor.setUserId(id);
            //将该用户的权限修改为医生权限
            if(id!=1){
                userMapper.becomeDoctor(id);
            }
            doctorMapper.add(doctor);
        }
    }

    @Override
    public List<Doctor> get() {
        List<Doctor> doctors =  doctorMapper.get();
        for(Doctor doctor:doctors){
            Integer id = doctor.getDepartmentId();
            if(id == null){
                return null;
            }
            String name = departmentMapper.getDepartmentNameById(id);
            doctor.setDepartmentName(name);
        }
        return doctors;
    }

    @Override
    public void update(Doctor doctor) {
        doctorMapper.update(doctor);
    }

    @Override
    public void delete(String id) {
        doctorMapper.delete(id);
    }

    @Override
    public void addWithoutUserId(Doctor doctor) {
//        Map<String,Object> map= ThreadLocalUtil.get();
//        int id = (int)map.get("id");
//        //判断当前用户是否已经申请过陪诊师
//        doctor.setUserId(id);
//        //将该用户的权限修改为医生权限
//        if(id!=1){
//            userMapper.becomeDoctor(id);
//        }
        doctorMapper.add(doctor);
    }
}
