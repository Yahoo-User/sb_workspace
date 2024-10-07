package org.zerock.myapp.seq_6.config;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.zerock.myapp.seq_5.interceptor.HandlerInterceptor1;
import org.zerock.myapp.seq_5.interceptor.HandlerInterceptor2;
import org.zerock.myapp.seq_5.interceptor.HandlerInterceptor3;


@Log4j2
@NoArgsConstructor

@Configuration("handlerInterceptorConfig")
public class HandlerInterceptorConfig implements WebMvcConfigurer, BeanNameAware {


    @Override
    public void setBeanName(String name) {
        log.trace("setBeanName({}) invoked.", name);
    } // setBeanName


    @Override
    // To register all Spring Handler Interceptors with `InterceptorRegistry`
    public void addInterceptors(InterceptorRegistry registry) {
        log.trace("addInterceptors({}) invoked.", registry);

        // --------------------------
        // Case1 - Intercepts all request URIs.
        // --------------------------
//        registry.addInterceptor(new SpringInterceptor1()).addPathPatterns("/**");                                                         // OK

        // --------------------------
        // Case2 - Intercepts all request URIs, but excludes "/2.jpg" request URI.
        // --------------------------
//        registry.addInterceptor(new SpringInterceptor1()).addPathPatterns("/**").excludePathPatterns("/2.jpg");       // OK

        // --------------------------
        // Case3 - Intercepts the specified all request URIs with base "/interceptor/*" request URI.
        // --------------------------
//        registry.addInterceptor(new SpringInterceptor1()).addPathPatterns("/interceptor/*");                                      // OK

//        registry.addInterceptor(new SpringInterceptor1()).addPathPatterns("/interceptor/**");                                     // OK

//        registry.addInterceptor(new SpringInterceptor2())
//                    .addPathPatterns("/interceptor/**")
//                    .addPathPatterns("/2.jpg")
//                    .excludePathPatterns("/interceptor/7");                                                                                                // OK

        // --------------------------
        // Case4 - Spring Interceptor chain in the specified order about the specific request URI.
        // --------------------------
        registry.addInterceptor(new HandlerInterceptor1()).addPathPatterns("/interceptor/3").order(3);
        registry.addInterceptor(new HandlerInterceptor2()).order(1).addPathPatterns("/interceptor/3");
        registry.addInterceptor(new HandlerInterceptor3()).addPathPatterns("/interceptor/3").order(2);
    } // addInterceptors

} // end class


