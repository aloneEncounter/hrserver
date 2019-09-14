package com.base.common;

import com.base.bean.Hr;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class HrUtils {

    public static Hr getCurrentHr() {
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //取出身份信息
        return (Hr) subject.getPrincipal();
    }
}


