package com.example.bookdinner.controller;

import com.example.bookdinner.service.UserService;
import com.example.bookdinner.vo.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    /*
    修改昵称
     */
    @GetMapping("/user/updateName/{id}/{name}")
    public ResultData updateName(@PathVariable("id") Integer id, @PathVariable("name") String name){
        return userService.updateName(id, name);
    }

    /*
    根据id查询用户信息
     */
    @GetMapping("/user/findById/{id}")
    public ResultData findById(@PathVariable("id") Integer id){
        return userService.findById(id);
    }

    /*
    修改邮箱
     */
    @GetMapping("/user/updateEmail/{id}/{email}")
    public ResultData updateEmail(@PathVariable("id") Integer id,@PathVariable("email") String email){
        return userService.updateEmail(id, email);
    }

    /*
    修改年龄
     */
    @GetMapping("/user/updateAge/{id}/{age}")
    public ResultData updateAge(@PathVariable("id") Integer id,@PathVariable("age") Integer age){
        return userService.updateAge(id, age);
    }

    /*
    修改爱好
     */
    @GetMapping("/user/updateLikes/{id}/{likes}")
    public ResultData updateLikes(@PathVariable("id") Integer id,@PathVariable("likes") String likes){
        return userService.updateLikes(id, likes);
    }

    /*
   修改个性签名
    */
    @GetMapping("/user/updateContent/{id}/{content}")
    public ResultData updateContent(@PathVariable("id") Integer id,@PathVariable("content") String content){
        return userService.updateContent(id, content);
    }

    /*
    查询旧密码是否正确
     */
    @GetMapping("/user/findPwdIsRight/{id}/{password}")
    public ResultData findPwdIsRight(@PathVariable("id") Integer id,@PathVariable("password") String password){
        return userService.findPwdIsRight(id, password);
    }

    /*
    修改密码
     */
    @GetMapping("/user/updatePwd/{id}/{newPassword}/{oldPassword}")
    public ResultData updatePwd(@PathVariable("id") Integer id,@PathVariable("newPassword") String newPassword,@PathVariable("oldPassword") String oldPassword){
        return userService.updatePwd(id, newPassword, oldPassword);
    }

    /*
    查询所有非管理用户
     */
    @GetMapping("/user/findAllUser")
    public ResultData findAllUser(){
        return userService.findAllUser();
    }

    /*
    将用户设置为管理员
     */
    @GetMapping("/user/updateUserToManage/{userId}")
    public ResultData updateUserToManage(@PathVariable("userId") Integer userId){
        return userService.updateUserToManage(userId);
    }
}
