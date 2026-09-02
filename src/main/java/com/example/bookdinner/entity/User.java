package com.example.bookdinner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
存储用户信息的实体类对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /*
    用户的id号
     */
    private Integer id;
    /*
    用户注册的用户名（用邮箱注册的所以就是邮箱）
     */
    private String userName;
    /*
    用户的登录密码
     */
    private String password;
    /*
    用户的昵称
     */
    private String name;
    /*
    用户的邮箱
     */
    private String email;
    /*
    用户的年龄
     */
    private Integer age;
    /*
    用户的喜好
     */
    private String likes;
    /*
    用户的个性签名
     */
    private String content;

    public User(String userName, String password, String name, String email) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
