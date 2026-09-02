package com.example.bookdinner.controller;

import com.example.bookdinner.service.UserService;
import com.example.bookdinner.vo.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RegisterController {

    @Resource
    private UserService userService;

    /*
    后端获取验证码方法
     */
    @GetMapping("/register/gainCode/{userName}")
    public ResultData gainCode(@PathVariable("userName") String userName){
        return userService.gainCode(userName);
    }

    /*
    后端注册功能
     */
    @GetMapping("/register/userRegister/{userName}/{checkCode}")
    public ResultData register(@PathVariable("userName") String userName,@PathVariable("checkCode") String checkCode){
        return userService.register(userName,checkCode);
    }

    /*
    查询邮箱是否被注册过
     */
    @GetMapping("/register/existEmail/{email}")
    public ResultData existEmail(@PathVariable("email") String email){
        return userService.existEmail(email);
    }

}
