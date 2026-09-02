package com.example.bookdinner.mapper;

import com.example.bookdinner.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
用户管理模块的dao层
 */
@Mapper
public interface UserMapper {

    /*
    查询用户所输入的邮箱和手机号是否一致
     */
    @Select("select id,username,password,name,email,age,likes,content from user where username = #{userName} and password = #{password}")
    User findUser(@Param("userName") String userName, @Param("password") String password);

    /*
    根据id查询用户信息
     */
    @Select("select id,username,password,name,email,age,likes,content from user where id = #{id}")
    User findUserById(@Param("id") Integer id);

    /*
    添加新的用户信息
     */
    @Insert("insert into user(id,username,password,name,email,age) values(null,#{userName},#{password},#{name},#{email},1)")
    void addUser(User user);

    /*
    查询用户名是否存在
     */
    @Select("select count(id) from user where username = #{email}")
    int findEmail(@Param("email") String email);

    /*
    修改昵称
     */
    @Update("update user set name = #{name} where id = #{id}")
    int updateName(@Param("id") Integer id,@Param("name") String name);

    /*
    修改邮箱
     */
    @Update("update user set email = #{email} where id = #{id}")
    int updateEmail(@Param("id") Integer id,@Param("email") String email);

    /*
    修改年龄
     */
    @Update("update user set age = #{age} where id = #{id}")
    int updateAge(@Param("id") Integer id,@Param("age") Integer age);

    /*
    修改爱好
     */
    @Update("update user set likes = #{likes} where id = #{id}")
    int updateLikes(@Param("id") Integer id,@Param("likes") String likes);

    /*
    修改个性签名
     */
    @Update("update user set content = #{content} where id = #{id}")
    int updateContent(@Param("id") Integer id,@Param("content") String content);

    /*
    根据id号查密码
     */
    @Select("select count(id) from user where id = #{id} and password = #{password}")
    int findPwdById(@Param("id") Integer id,@Param("password") String password);

    /*
    根据id改密码
     */
    @Update("update user set password = #{password} where id = #{id}")
    int updatePwd(@Param("id") Integer id,@Param("password") String password);

    /*
    查询账号的状态
     */
    @Select("select status from userstatus where userId = #{userId}")
    Integer findUserStatus(@Param("userId") Integer userId);

    /*
    查询所有的用户
     */
    @Select("select id,username,password,name,email,age,likes,content from user")
    List<User> findAllUser();

    /*
    查询管理员的Id
     */
    @Select("select userId from userstatus")
    List<Integer> findUserManange();

    /*
    将用户插入管理员表中
     */
    @Insert("insert into userstatus values(null,#{userId},0)")
    int addManage(@Param("userId") Integer userId);
}
