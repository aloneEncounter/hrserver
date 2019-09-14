package com.base.config.config;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 *Created by IntelliJ IDEA.
 *@author Miao
 *@date 2019/9/3 23:37
 *shiro 配置类
 */
@Configuration
public class ShiroConfig {

    /*
    * 创建ShiroFilterFactoryBean
    *
    * */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactorytBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        //添加shiro的内置过滤器
        /*
         * Shiro内置过滤器，可以实现权限相关的拦截器
         * 常用的过滤器：
         *    anon:无需认证
         *    authc：必须认证才可以访问
         *    user：如果使用rememberMe的功能可以直接访问
         *    perms：该资源必须得到资源权限才可以访问
         *    role：该资源必须得到角色权限才可以访问
         * */

        Map<String,String> filterMap=new LinkedHashMap<>();



//        filterMap.put("/","anon");
        filterMap.put("/home","anon");
        filterMap.put("/index.html","anon");
        filterMap.put("/static/**", "anon");
//        filterMap.put("/static/static/**", "anon");
        filterMap.put("/login_p", "anon");
        filterMap.put("/favicon.ico","anon");
//        filterMap.put("/index.html/home","anon");


//        filterMap.put("/a","perms[user:add]");
//        filterMap.put("/**","authc");

        //修改跳转的登陆页面
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//        shiroFilterFactoryBean.setLoginUrl("/login");

        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/home");

        //設置未授權的401頁面401
//        shiroFilterFactoryBean.setUnauthorizedUrl("/error/401.html");



        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        return shiroFilterFactoryBean;
    }


    /*
    * 创建DefaultWebSecurityManager
    * */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /*
    * 创建Realm
    * */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return  userRealm;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(UserRealm userRealm){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(getDefaultWebSecurityManager(userRealm));
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 密码匹配凭证管理器
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 采用MD5方式加密
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 设置加密次数
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }


}
