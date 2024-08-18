package com.wanandaodao.bigevent.service;

import com.wanandaodao.bigevent.pojo.AppointmentedInfo;
import com.wanandaodao.bigevent.pojo.DoctorPatientInfo;
import com.wanandaodao.bigevent.pojo.Patient;

import java.util.List;

public interface PatientService {
    void add(Patient patient);

    List<AppointmentedInfo> getInfo();

    List<DoctorPatientInfo> getPatientInfo();
}
