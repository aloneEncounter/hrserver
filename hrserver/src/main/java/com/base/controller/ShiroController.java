package com.base.controller;


import com.base.bean.ResultVO;
import com.base.common.HrUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *Created by IntelliJ IDEA.
 *@author Miao
 *@date 2019/9/4 23:13
 *
 */
@Controller
public class ShiroController {


    /*
    * 登陆逻辑处理
    * */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResultVO login(String username, String password) {

        //使用shiro编写认证操作
        //1、获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        //3、执行登陆方法
        ResultVO resultVO = null;
        try {
            subject.login(token);
            return ResultVO.success("登陆成功", HrUtils.getCurrentHr());
            //登陆成功
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            //登陆失败:用户名不存在
            resultVO = ResultVO.error("账户名或者密码输入错误!");

        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            //登陆失败:密码错误
            resultVO = ResultVO.error("账户名或者密码输入错误!");

        }

        return resultVO;
    }


}
