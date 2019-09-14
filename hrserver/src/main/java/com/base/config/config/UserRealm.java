package com.base.config.config;


import com.base.bean.Hr;
import com.base.bean.Menu;
import com.base.dao.HrMapper;
import com.base.dao.MenuMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/*
 *Created by IntelliJ IDEA.
 *@author Miao
 *@date 2019/9/3 23:40
 * 自定义Realm
 * 认证逻辑
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private HrMapper hrMapper;


    @Autowired
    private MenuMapper menuMapper;

    /*
     * 执行授权逻辑
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登陆用户
        Subject subject = SecurityUtils.getSubject();
        Hr hr=(Hr) subject.getPrincipal();
        //遍历菜单集合
        List<Menu> menusByHrId = menuMapper.getMenusByHrId(hr.getId());

        info.addStringPermissions(menusByHrId.stream().map(e->e.getPermission())
                                          .collect(Collectors.toList()));
        return null;
    }

    /*
     * 执行认证逻辑
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userName = (String) authenticationToken.getPrincipal();
        System.out.println(userName);
        Hr hr = hrMapper.loadUserByUsername(userName);
        System.out.println("-----------");
        System.out.println(SecurityUtils.getSubject().getPrincipal() + "oooo");
//        System.out.println(this.getName());
        if (hr == null) {
            return null;
        }
        //认证通过将信息保存咋尸体类中
        String password = hr.getPassword();
//        ActiveUser activeUser = new ActiveUser();
//        BeanUtils.copyProperties(hr, activeUser);

//        activeUser.setId(hr.getId());
//        activeUser.setUsername(hr.getUsername());
//        activeUser.setName(hr.getName());
//        System.out.println(activeUser + "------");
        //遍历菜单集合
        List<Menu> menusByHrId = menuMapper.getMenusByHrId(hr.getId());
//        activeUser.setMenus(menusByHrId);
        //
        // 颜值加密的颜，可以用用户名
        ByteSource credentialsSalt = ByteSource.Util.bytes(hr.getUsername());

        return new SimpleAuthenticationInfo(hr, password,credentialsSalt, this.getName());

//        //编写shiro判断逻辑，判断用户名和密码
//        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
//        if (!token.getUsername().equals("admin")){
//            //用户名不存在
//            return null;   //shiro底层会抛出UnkonwAccountException
//        }
//
//        //2、判断密码
//        return new SimpleAuthenticationInfo();
//        return null;
    }
}
