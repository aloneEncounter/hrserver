package com.base.common;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/*
 *Created by IntelliJ IDEA.
 *@author Miao
 *@date 2019/9/14 15:05
 *
 */
public class ByteSourceUtils {


    public static String  encode(String username,String password){
        SimpleHash md5 = new SimpleHash("MD5", password, ByteSource.Util.bytes(username), 1024);
        return md5.toString();
    }

    public static void main(String[] args) {


        //盐值用的用的是对用户名的加密（测试用的"lisi"）
        ByteSource credentialsSalt01 = ByteSource.Util.bytes("admin");
        Object salt = null;//盐值
        Object credential = "123";//密码
        String hashAlgorithmName = "MD5";//加密方式
        //1024指的是加密的次数

        Object simpleHash = new SimpleHash(hashAlgorithmName, credential,
                credentialsSalt01, 1024);
        System.out.println("加密后的值----->" + simpleHash);
        System.out.println("加密后的值----->" + simpleHash.toString());
    }
}