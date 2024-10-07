package org.zerock.myapp.seq_8.config;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zerock.myapp.seq_7.interceptor.WebRequestInterceptor1;
import org.zerock.myapp.seq_7.interceptor.WebRequestInterceptor2;
import org.zerock.myapp.seq_7.interceptor.WebRequestInterceptor3;


@Log4j2
@NoArgsConstructor

@Configuration("webRequestInterceptorConfig")
public class WebRequestInterceptorConfig implements WebMvcConfigurer, BeanNameAware {


    @Override
    public void setBeanName(String name) {
        log.trace("setBeanName({}) invoked.", name);
    } // setBeanName


    @Override
    // To register all Spring Web Request Interceptors with `InterceptorRegistry`
    public void addInterceptors(InterceptorRegistry registry) {
        log.trace("addInterceptors({}) invoked.", registry);

        // --------------------------
        // Case1 - Intercepts all web request.
        // --------------------------
//        registry.addWebRequestInterceptor(new WebRequestInterceptor1())
//                    .addPathPatterns("/**");                                                                            // OK

        // --------------------------
        // Case2 - Intercepts all request URIs, but excludes "/2.jpg" request URI.
        // --------------------------
//        registry.addWebRequestInterceptor(new WebRequestInterceptor1())
//                    .addPathPatterns("/**")
//                    .excludePathPatterns("/2.jpg");                                                                 // OK

        // --------------------------
        // Case3 - Intercepts the specified all request URIs with base "/interceptor/*" request URI.
        // --------------------------
//        registry.addWebRequestInterceptor(new WebRequestInterceptor1())
//                    .addPathPatterns("/interceptor/*");                                                           // OK

//        registry.addWebRequestInterceptor(new WebRequestInterceptor1())
//                    .addPathPatterns("/interceptor/**");                                                          // OK

//        registry.addWebRequestInterceptor(new WebRequestInterceptor1())
//                    .addPathPatterns("/interceptor/**")
//                    .addPathPatterns("/2.jpg")
//                    .excludePathPatterns("/interceptor/7");                                                     // OK

        // --------------------------
        // Case4 - Spring Interceptor chain in the specified order about the specific request URI.
        // --------------------------
        registry.addWebRequestInterceptor(new WebRequestInterceptor1()).addPathPatterns("/interceptor/3").order(3);
        registry.addWebRequestInterceptor(new WebRequestInterceptor2()).order(1).addPathPatterns("/interceptor/3");
        registry.addWebRequestInterceptor(new WebRequestInterceptor3()).addPathPatterns("/interceptor/3").order(2);
    } // addInterceptors

} // end class
