package com.wanandaodao.bigevent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorPatientInfo {
    private String name;
    private Integer age;
    private String phone;
    private String cases;
    private String description;
    private String address;
    private String serviceType;
}
