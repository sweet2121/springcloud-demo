package com.wrd.config;




import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        //是否支持安全证书
        config.setAllowCredentials(true);
        //允许来源
        config.setAllowedOrigins(Arrays.asList("*"));
        //允许的头信息
        config.setAllowedHeaders(Arrays.asList("*"));
        //允许的方法：Get\Post
        config.setAllowedMethods(Arrays.asList("*"));
        //请求的有效期,，单位为秒,在这个时间内不需要在验证跨域
        config.setMaxAge(3600L);
        //匹配所有请求
        source.registerCorsConfiguration("/**", config);
        //跨域过滤器
        CorsFilter corsFilter = new CorsFilter(source);
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(corsFilter);
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }
}
