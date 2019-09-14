package com.base.bean;

import lombok.Data;

@Data
public class SysMsg {
    private Long id;
    private Long mid;
    private Integer type;
    private Long hrid;
    private Integer state;
    private MsgContent msgContent;



}
