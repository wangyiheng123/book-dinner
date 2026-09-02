package com.example.bookdinner.controller.interceptor;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
检测页面跳转时是否登录（同步）
 */
@Component
public class CheckHTMLInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        if (token == null){
            response.sendRedirect("/html/login.html");
            return false;
        }else {
            //从redis库中拿数据
            String userMsg = stringRedisTemplate.opsForValue().get("user:"+token);
            if (userMsg == null){
                response.sendRedirect("/html/login.html");
                return false;
            }else {
                return true;
            }
        }
    }
}
