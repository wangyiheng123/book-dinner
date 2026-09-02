package com.example.bookdinner.controller.interceptor;

import com.example.bookdinner.util.ThreadLocalUtil;
import com.example.bookdinner.vo.Code;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/*
检测异步请求是否携带token(处理异步请求)
 */
@Component
public class CheckLoginInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null){
            //未登录
            response.getWriter().println(Code.NOT_LOGIN);
            return false;
        }else {
            String userMsg = stringRedisTemplate.opsForValue().get("user:"+token);
            if (userMsg == null){
                //登录失效
                response.getWriter().println(Code.TOKEN_ERROR);
                return false;
            }else {
                ThreadLocalUtil.setString(token);
                stringRedisTemplate.expire("user:"+token,30, TimeUnit.MINUTES);
                return true;
            }
        }
    }
}
