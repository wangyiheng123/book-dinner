package com.example.bookdinner.util;

import java.util.Random;

/*
生成6位验证码的工具类
 */
public class CheckCode {

    private static final char[] charCode = {'0','1','2','3','4','5','6','7','8','9'};

    /*6
    生成6位验证码的算法
     */
    public static String createCode(){
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0;i < 6;i++){
           int index = random.nextInt(10);
           code.append(charCode[index]);
        }
        return code.toString();
    }

}
