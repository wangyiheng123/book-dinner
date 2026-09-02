package com.example.bookdinner.controller;

import com.example.bookdinner.entity.User;
import com.example.bookdinner.service.UserService;
import com.example.bookdinner.vo.ResultData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
登录功能的控制层
 */
@RestController
public class LoginController {

    @Resource
    private UserService userService;

    /*
    登录功能
     */
    @PostMapping("/login/userLogin")
    public ResultData login(@RequestBody User user){
        return userService.login(user.getUserName(), user.getPassword());
    }

}
