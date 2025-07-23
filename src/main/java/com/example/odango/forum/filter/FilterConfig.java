package com.example.odango.forum.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter() {
        FilterRegistrationBean<LoginFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LoginFilter());
        //ログイン情報が必要なURL(URLは仮)
        bean.addUrlPatterns("/Forum");
        bean.addUrlPatterns("/Forum/management/*");
        bean.addUrlPatterns("/Forum/new");
        bean.addUrlPatterns("/Forum/edit/*");
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<AdministratorFilter> AdministratorFilter(){
        FilterRegistrationBean<AdministratorFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new AdministratorFilter());
        bean.addUrlPatterns("/Forum/management/*");
        bean.setOrder(2);
        return bean;
    }
}
