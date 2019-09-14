package com.base.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/*
 *Created by IntelliJ IDEA.
 *@author Miao
 *@date 2019/8/20 22:40
 *
 */

@Data
public class Hr  {
    private Long id;
    private String name;
    private String phone;
    private String telephone;
    private String address;
    private boolean enabled;
    private String username;
    private String password;
    private String remark;
    private List<Role> roles;
    private String userface;

}