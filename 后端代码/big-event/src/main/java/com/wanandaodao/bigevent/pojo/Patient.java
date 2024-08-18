package com.wanandaodao.bigevent.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private Integer id;
    private Integer userId;
    private String name;
    private Integer age;
    private String phone;
    private String gender;
    private String description;
    private List<String> cases;
    private String visitTime;
    private Integer doctorId;
    private String caseDescription;
    private String serviceType;
    private String address;
}
