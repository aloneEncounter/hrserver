package com.base.bean;

import lombok.Data;

import java.io.Serializable;
@Data
public class Role implements Serializable {
    private Long id;
    private String name;
    private String nameZh;

}
