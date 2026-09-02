package com.example.bookdinner.service.impl;

import com.example.bookdinner.entity.User;
import com.example.bookdinner.mapper.UserMapper;
import com.example.bookdinner.service.UserService;
import com.example.bookdinner.util.*;
import com.example.bookdinner.vo.Code;
import com.example.bookdinner.vo.ResultData;
import com.example.bookdinner.vo.TokenAndUser;
import com.google.gson.Gson;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultData login(String userName, String password) {
        //查询是否存在该账号
        User user = userMapper.findUser(userName,password);
        if (user != null){
            //生成一个令牌
            String token = UUID.randomUUID().toString();
            Gson gson = new Gson();
            //将user对象中的信息转换成JSON格式的字符串
            String userJson = gson.toJson(user);
            //以token为Key，存储在redis数据库中
            stringRedisTemplate.opsForValue().set("user:" + token,userJson,30, TimeUnit.MINUTES);
            //查询账号的状态
            Integer status = userMapper.findUserStatus(user.getId());
            if (status == null){
                return new ResultData(Code.COMMON_LOGIN,new TokenAndUser(token,user),"登录成功！");
            }else if (status == 0){
                return new ResultData(Code.ADMIN_LOGIN,new TokenAndUser(token,user),"登录成功！");
            }
        }
        return new ResultData(Code.LOGIN_ERR,null,"登录失败，用户名或密码错误！！！");
    }

    @Override
    public ResultData gainCode(String userName) {
        String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";
        //后端再次验证是否填写的是正确的邮箱
        if (!userName.matches(regex)){
            return new ResultData(Code.SEND_ERR,null,"请填写正确的邮箱！！！");
        }
        //生成一个验证码
        String code = CheckCode.createCode();
        //以邮箱号为key，验证码为value存储到redis数据库中
        stringRedisTemplate.opsForValue().set("user:" + userName,code,10,TimeUnit.MINUTES);
        //给用户发送验证码
        try {
            Mail.sendMail(userName,code);
        }catch (Exception e){
            return new ResultData(Code.SEND_ERR,null,"验证码发送失败！");
        }
        return new ResultData(Code.SEND_OK,null,"验证码已发送！");
    }

    @Override
    public ResultData register(String userName,String checkCode) {
        String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";
        //后端再次验证是否填写的是正确的邮箱
        if (!userName.matches(regex)){
            return new ResultData(Code.SEND_ERR,null,"请填写正确的邮箱！！！");
        }
        //从redis数据库中拿到验证码
        String code = stringRedisTemplate.opsForValue().get("user:"+userName);
        if (code != null){
            if (code.equals(checkCode)){
                //自动生成默认密码
                String password = Password.createPassword();
                //默认生成昵称
                String name = Name.createName();
                //注册成功，写入mysql数据库中
                userMapper.addUser(new User(userName,password,name,userName));
                //给用户发送邮件，告知用户的用户名和密码
                try {
                    Mail.sendPassword(userName,password);
                }catch (Exception e){
                    return new ResultData(Code.SEND_ERR,null,"用户名和密码发送失败！");
                }
                return new ResultData(Code.SEND_OK,null,"用户名和密码发送成功！");
            }
        }
        return new ResultData(Code.SEND_ERR,null,"注册失败！");
    }

    @Override
    public ResultData existEmail(String email) {
        int row = userMapper.findEmail(email);
        if (row > 0){
            return new ResultData(Code.SELECT_OK,null,"邮箱已被注册！");
        }
        return new ResultData(Code.SEND_ERR,null,"");
    }

    @Override
    public ResultData updateName(Integer id, String name) {
        int row = userMapper.updateName(id, name);
        if (row > 0){
            //从本地线程中拿token
            String token = ThreadLocalUtil.getString();
            User user = userMapper.findUserById(id);
            Gson gson = new Gson();
            String userJson = gson.toJson(user);
            //更新redis数据库
            stringRedisTemplate.opsForValue().set("user:"+token,userJson,30,TimeUnit.MINUTES);
            return new ResultData(Code.UPDATE_OK,user,"修改成功！");
        }
        return new ResultData(Code.UPDATE_ERR,null,"修改失败！");
    }

    @Override
    public ResultData findById(Integer id) {
        User user = userMapper.findUserById(id);
        if (user == null){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关信息!");
        }
        return new ResultData(Code.SELECT_OK,user,null);
    }

    @Override
    public ResultData updateEmail(Integer id, String email) {
        int row = userMapper.updateEmail(id, email);
        if (row > 0){
            //从本地线程中拿token
            String token = ThreadLocalUtil.getString();
            User user = userMapper.findUserById(id);
            Gson gson = new Gson();
            String userJson = gson.toJson(user);
            //更新redis数据库
            stringRedisTemplate.opsForValue().set("user:"+token,userJson,30,TimeUnit.MINUTES);
            return new ResultData(Code.UPDATE_OK,user,"修改成功！");
        }
        return new ResultData(Code.UPDATE_ERR,null,"修改失败！");
    }

    @Override
    public ResultData updateAge(Integer id, Integer age) {
        int row = userMapper.updateAge(id,age);
        if (row > 0){
            //从本地线程中拿token
            String token = ThreadLocalUtil.getString();
            User user = userMapper.findUserById(id);
            Gson gson = new Gson();
            String userJson = gson.toJson(user);
            //更新redis数据库
            stringRedisTemplate.opsForValue().set("user:"+token,userJson,30,TimeUnit.MINUTES);
            return new ResultData(Code.UPDATE_OK,user,"修改成功！");
        }
        return new ResultData(Code.UPDATE_ERR,null,"修改失败！");
    }

    @Override
    public ResultData updateLikes(Integer id, String likes) {
        int row = userMapper.updateLikes(id, likes);
        if (row > 0){
            //从本地线程中拿token
            String token = ThreadLocalUtil.getString();
            User user = userMapper.findUserById(id);
            Gson gson = new Gson();
            String userJson = gson.toJson(user);
            //更新redis数据库
            stringRedisTemplate.opsForValue().set("user:"+token,userJson,30,TimeUnit.MINUTES);
            return new ResultData(Code.UPDATE_OK,user,"修改成功！");
        }
        return new ResultData(Code.UPDATE_ERR,null,"修改失败！");
    }

    @Override
    public ResultData updateContent(Integer id, String content) {
        int row = userMapper.updateContent(id, content);
        if (row > 0){
            //从本地线程中拿token
            String token = ThreadLocalUtil.getString();
            User user = userMapper.findUserById(id);
            Gson gson = new Gson();
            String userJson = gson.toJson(user);
            //更新redis数据库
            stringRedisTemplate.opsForValue().set("user:"+token,userJson,30,TimeUnit.MINUTES);
            return new ResultData(Code.UPDATE_OK,user,"修改成功！");
        }
        return new ResultData(Code.UPDATE_ERR,null,"修改失败！");
    }

    @Override
    public ResultData findPwdIsRight(Integer id, String password) {
        int row = userMapper.findPwdById(id,password);
        if (row > 0){
            return new ResultData(Code.SELECT_OK,null,null);
        }
        return new ResultData(Code.SELECT_NOTFOUND,null,"旧密码不正确，请重新输入！！！");
    }

    @Override
    public ResultData updatePwd(Integer id, String newPassword,String oldPassword) {
        String regex = "^[a-zA-Z]\\w{5,17}$";
        int row = userMapper.findPwdById(id,oldPassword);
        if (!newPassword.matches(regex)){
            return  new ResultData(Code.UPDATE_ERR,null,"密码格式不正确！！！");
        }else if ("".equals(newPassword)){
            return new ResultData(Code.UPDATE_ERR,null,"密码不能为空！！！");
        }else if (row < 1){
            return new ResultData(Code.UPDATE_ERR,null,"旧密码不正确！！！");
        }else {
            int r = userMapper.updatePwd(id,newPassword);
            if (r > 0){
                return new ResultData(Code.UPDATE_OK,null,"修改成功！");
            }
            return new ResultData(Code.UPDATE_ERR,null,"修改失败！");
        }
    }

    @Override
    public ResultData findAllUser() {
        List<User> users = userMapper.findAllUser();
        if (users.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到用户的信息！");
        }
        List<Integer> integerList = userMapper.findUserManange();
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            for (int i : integerList){
                if (user.getId() == i){
                    iterator.remove();
                }
            }
        }
        return new ResultData(Code.SELECT_OK,users,null);
    }

    @Override
    public ResultData updateUserToManage(Integer userId) {
        int row = userMapper.addManage(userId);
        if (row > 0){
            List<User> users = userMapper.findAllUser();
            List<Integer> integerList = userMapper.findUserManange();
            Iterator<User> iterator = users.iterator();
            while (iterator.hasNext()){
                User user = iterator.next();
                for (int i : integerList){
                    if (user.getId() == i){
                        iterator.remove();
                    }
                }
            }
            return new ResultData(Code.UPDATE_OK,users,"设置成功！");
        }
        return new ResultData(Code.UPDATE_ERR,null,"设置失败！");
    }

}
