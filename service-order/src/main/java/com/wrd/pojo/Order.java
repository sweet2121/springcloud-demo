package com.wrd.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer id;
    private String orderNo;
    private Date createTime;
    private Integer userId;
    private String username;
}
