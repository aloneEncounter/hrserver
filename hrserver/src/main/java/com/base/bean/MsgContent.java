package com.base.bean;

import lombok.Data;

import java.util.Date;
@Data
public class MsgContent {
    private Long id;
    private String message;
    private String title;
    private Date createDate;

}
