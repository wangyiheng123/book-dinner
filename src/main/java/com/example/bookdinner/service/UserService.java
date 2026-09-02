package com.example.bookdinner.service;

import com.example.bookdinner.vo.ResultData;

public interface UserService {
    /*
    登录功能的业务
     */
    ResultData login(String userName, String password);

    /*
    获取验证码功能的业务
     */
    ResultData gainCode(String userName);

    /*
    注册功能的业务
     */
    ResultData register(String userName,String checkCode);

    /*
    查询邮箱是否存在。
     */
    ResultData existEmail(String email);

    /*
    修改昵称
     */
    ResultData updateName(Integer id,String name);

    /*
    根据id查询用户的信息
     */
    ResultData findById(Integer id);

    /*
    修改邮箱
     */
    ResultData updateEmail(Integer id,String email);

    /*
    修改年龄
     */
    ResultData updateAge(Integer id,Integer age);

    /*
    修改爱好
     */
    ResultData updateLikes(Integer id,String likes);

    /*
    修改个性签名
     */
    ResultData updateContent(Integer id,String content);

    /*
    查询旧密码是否正确
     */
    ResultData findPwdIsRight(Integer id,String password);

    /*
    修改密码
     */
    ResultData updatePwd(Integer id,String newPassword,String oldPassword);

    /*
    查询所有的用户
     */
    ResultData findAllUser();

    /*
    将用户设为管理员
     */
    ResultData updateUserToManage(Integer userId);

}
