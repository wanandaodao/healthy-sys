package com.wanandaodao.bigevent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    private Integer id;
    private String name;
    private Integer age;
    private String phone;
    private String idCard;
    private Integer departmentId;
    private String departmentName;
    private String gender;
    private Integer userId;
    private String picture;
}
