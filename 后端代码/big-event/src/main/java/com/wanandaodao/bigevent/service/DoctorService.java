package com.wanandaodao.bigevent.service;

import com.wanandaodao.bigevent.pojo.Department;
import com.wanandaodao.bigevent.pojo.Doctor;

import java.util.List;

public interface DoctorService {
    List<Department> getDepartment();

    void add(Doctor doctor);

    List<Doctor> get();

    void update(Doctor doctor);

    void delete(String id);

    void addWithoutUserId(Doctor doctor);
}
