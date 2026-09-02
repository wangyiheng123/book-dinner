package com.example.bookdinner.config;

import com.example.bookdinner.controller.interceptor.CheckHTMLInterceptor;
import com.example.bookdinner.controller.interceptor.CheckLoginInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Component
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    private CheckLoginInterceptor checkLoginInterceptor;
    @Resource
    private CheckHTMLInterceptor checkHTMLInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkHTMLInterceptor).addPathPatterns("/html/address.html").addPathPatterns("/html/advice.html").addPathPatterns("/html/hotPointer.html").addPathPatterns("/html/index.html").addPathPatterns("/html/order.html").addPathPatterns("/html/user.html").addPathPatterns("/html/updateAddress.html").addPathPatterns("/html/updatePwd.html").addPathPatterns("/html/orderFood.html").addPathPatterns("/html/foodDetail.html").addPathPatterns("/html/submitOrder.html").addPathPatterns("/html/orderSuccess.html").addPathPatterns("/html/itemDetail.html").addPathPatterns("/html/itemAdvice.html").addPathPatterns("/html/adminIndex.html").addPathPatterns("/html/upShop.html").addPathPatterns("/html/havedShop.html").addPathPatterns("/html/adminFoodDetail.html").addPathPatterns("/html/adminRider.html").addPathPatterns("/html/adminShopDetail.html").addPathPatterns("/html/upFood.html").addPathPatterns("/html/adminRiderDetail.html").addPathPatterns("/html/adminUser.html").addPathPatterns("/html/adminPwd.html").addPathPatterns("/html/adminUserManange.html");
        registry.addInterceptor(checkLoginInterceptor).addPathPatterns("/**").excludePathPatterns("/html/**").excludePathPatterns("/img/**").excludePathPatterns("/js/**").excludePathPatterns("/login/**").excludePathPatterns("/register/**").excludePathPatterns("/favicon.ico");
    }

    //配置项目图片与图片真实存储位置的映射关系（文件上传必备）
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:"+"D:\\arithmetic\\book-dinner\\src\\main\\resources\\static\\img\\");
    }
}
