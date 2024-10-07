package org.zerock.myapp.filter;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


@Log4j2
@NoArgsConstructor

@Configuration
public class TokenAuthenticationFilterConfig {


    @Bean
    @Order(1)
    public FilterRegistrationBean<TokenAuthenticationFilter> tokenAuthenticationFilterRegistrationBean() {
        log.trace("tokenAuthenticationFilterRegistrationBean() invoked.");

        FilterRegistrationBean<TokenAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenAuthenticationFilter());
        registrationBean.addUrlPatterns("/health", "/faq/*");

        return registrationBean;
    } // tokenAuthenticationFilterRegistrationBean

} // end class
