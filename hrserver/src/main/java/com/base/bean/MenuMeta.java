package com.base.bean;

import lombok.Data;

import java.io.Serializable;
@Data
public class MenuMeta implements Serializable {

    private boolean keepAlive;
    private boolean requireAuth;

}
