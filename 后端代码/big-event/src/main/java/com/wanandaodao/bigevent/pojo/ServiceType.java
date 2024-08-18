package com.wanandaodao.bigevent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceType {
    private Integer id;
    private String type;
    private Integer price;
    private String description;
}
