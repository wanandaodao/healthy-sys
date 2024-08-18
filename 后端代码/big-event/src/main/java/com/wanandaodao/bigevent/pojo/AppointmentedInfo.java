package com.wanandaodao.bigevent.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentedInfo {
    private String name;
    private Integer age;
    private String visitTime;
    private String doctorName;
    private String doctorPhone;
    private String serviceType;
}
