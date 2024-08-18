package com.wanandaodao.bigevent.service.impl;

import com.wanandaodao.bigevent.mapper.DoctorMapper;
import com.wanandaodao.bigevent.mapper.DoctorPatientMapper;
import com.wanandaodao.bigevent.mapper.PatientMapper;
import com.wanandaodao.bigevent.pojo.AppointmentedInfo;
import com.wanandaodao.bigevent.pojo.DoctorPatientInfo;
import com.wanandaodao.bigevent.pojo.Patient;
import com.wanandaodao.bigevent.service.PatientService;
import com.wanandaodao.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private DoctorPatientMapper doctorPatientMapper;
    @Override
    public void add(Patient patient) {
        List<String> cases = patient.getCases();
        String caseDescription = new String();
        if(cases.isEmpty()){
            caseDescription = "无";
        }else{
            for(String c : cases){
                caseDescription+=c+",";
            }
            caseDescription = caseDescription.substring(0,caseDescription.length()-1);//去掉最后一个逗号
        }
        patient.setCaseDescription(caseDescription);
        Map<String,Object> map= ThreadLocalUtil.get();
        int id = (int)map.get("id");
        patient.setUserId(id);
        if(patient.getAddress().isEmpty()){
            patient.setAddress("无");
        }
        patientMapper.add(patient);
        doctorPatientMapper.add(patient.getId(),patient.getDoctorId());
    }

    @Override
    public List<AppointmentedInfo> getInfo() {
        Map<String,Object> map= ThreadLocalUtil.get();
        int id = (int)map.get("id");
        //获取当前登录用户的id
        List<Patient> patients = patientMapper.getInfoById(id);
        List<AppointmentedInfo> list = new ArrayList<>();
        for(Patient patient:patients){
            AppointmentedInfo appointmentedInfo = new AppointmentedInfo();
            appointmentedInfo.setName(patient.getName());
            appointmentedInfo.setAge(patient.getAge());
            appointmentedInfo.setVisitTime(patient.getVisitTime());
            appointmentedInfo.setServiceType(patient.getServiceType());
            appointmentedInfo.setDoctorName(doctorMapper.selectNameById(patient.getDoctorId()));
            appointmentedInfo.setDoctorPhone(doctorMapper.selectPhoneById(patient.getDoctorId()));
            list.add(appointmentedInfo);
        }
        return list;
    }

    @Override
    public List<DoctorPatientInfo> getPatientInfo() {
        //获取当前登录用户的id
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id = (int)map.get("id");
        //查询当前陪诊师id
        id = doctorMapper.selectIdByUserId(id);
        if(id == null){
            return null;
        }
        //获取当前陪诊师对应的患者的id
        List<Integer>patientIds = doctorPatientMapper.selectPatientIdsByDoctorId(id);
        List<DoctorPatientInfo>list = new ArrayList<>();
        for(Integer patientId :patientIds){
            //根据患者id查询患者信息
            Patient patient = patientMapper.selectById(patientId);
            DoctorPatientInfo doctorPatientInfo = new DoctorPatientInfo();
            doctorPatientInfo.setName(patient.getName());
            doctorPatientInfo.setAge(patient.getAge());
            doctorPatientInfo.setPhone(patient.getPhone());
            doctorPatientInfo.setDescription(patient.getDescription());
            doctorPatientInfo.setCases(patient.getCaseDescription());
            doctorPatientInfo.setAddress(patient.getAddress());
            doctorPatientInfo.setServiceType(patient.getServiceType());
            list.add(doctorPatientInfo);
        }
        return list;
    }
}
