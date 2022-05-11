package com.niuniu.hcs_discuz.config;

import com.niuniu.hcs_discuz.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author niuniu
 * @date 2022/3/29
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Value("${my.file.serverPath}")
    String serverPath;

    @Value("${my.file.mappingPath}")
    String mappingPath;

    // 权限拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")  //过滤掉登录页面
                .excludePathPatterns("/user/register")  //过滤掉注册控制器
                .excludePathPatterns("/user/backlogin")  //过滤掉未登录控制器
                .excludePathPatterns("/user/resetpwd")  //过滤掉重置密码控制器
                .excludePathPatterns("/code/**")  //过滤掉验证码
                .excludePathPatterns("/js/**", "/css/**", "/img/**")  //过滤掉静态资源
                .excludePathPatterns("/index.html"); //过滤掉主页
    }

    //外部路径映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(mappingPath + "**")
                .addResourceLocations("file:" + serverPath);
    }

    // 允许跨域请求
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true);
    }

}
