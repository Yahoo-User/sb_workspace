package org.zerock.myapp.seq_3.config;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zerock.myapp.seq_2.filter.*;


@Log4j2
@NoArgsConstructor

@Configuration("filterConfig")
public class FilterConfig {


    @Bean
//    @Order(6)         // Not Worked
    FilterRegistrationBean<FrontFilter> frontFilterRegister() {
        log.trace("frontFilterRegister() invoked.");

        FilterRegistrationBean<FrontFilter> register = new FilterRegistrationBean<>();

        register.setFilter(new FrontFilter());
        register.setOrder(1);                               // Determine the order of this filter In the Filter Chain.  (***)
        register.setName("FrontFilter");
        register.addUrlPatterns("/*");

        return register;
    } //frontFilterRegister

    @Bean
//    @Order(5)         // Not Worked
    FilterRegistrationBean<MyFilter1> myFilter1Register() {
        log.trace("myFilter1Register() invoked.");

        FilterRegistrationBean<MyFilter1> register = new FilterRegistrationBean<>();

        register.setFilter(new MyFilter1());
        register.setOrder(2);                               // Determine the order of this filter In the Filter Chain.  (***)
        register.setName("MyFilter1");
        register.addUrlPatterns("/Hello1");

        return register;
    } //myFilter1Register

    @Bean
//    @Order(4)         // Not Worked
    FilterRegistrationBean<MyFilter2> myFilter2Register() {
        log.trace("myFilter2Register() invoked.");

        FilterRegistrationBean<MyFilter2> register = new FilterRegistrationBean<>();

        register.setFilter(new MyFilter2());
        register.setOrder(3);                               // Determine the order of this filter In the Filter Chain.  (***)
        register.setName("MyFilter2");
        register.addUrlPatterns("/Hello1");

        return register;
    } //myFilter2Register

    @Bean
//    @Order(3)         // Not Worked
    FilterRegistrationBean<MyFilter3> myFilter3Register() {
        log.trace("myFilter3Register() invoked.");

        FilterRegistrationBean<MyFilter3> register = new FilterRegistrationBean<>();

        register.setFilter(new MyFilter3());
        register.setOrder(2);                               // Determine the order of this filter In the Filter Chain.  (***)
        register.setName("MyFilter3");
        register.addUrlPatterns("/Hello2");

        return register;
    } //myFilter3Register

    @Bean
//    @Order(2)         // Not Worked
    FilterRegistrationBean<MyFilter4> myFilter4Register() {
        log.trace("myFilter4Register() invoked.");

        FilterRegistrationBean<MyFilter4> register = new FilterRegistrationBean<>();

        register.setFilter(new MyFilter4());
        register.setOrder(3);                               // Determine the order of this filter In the Filter Chain.  (***)
        register.setName("MyFilter4");
        register.addUrlPatterns("/Hello2");

        return register;
    } //myFilter4Register

    @Bean
//    @Order(1)         // Not Worked
    FilterRegistrationBean<MyFilter5> myFilter5Register() {
        log.trace("myFilter5Register() invoked.");

        FilterRegistrationBean<MyFilter5> register = new FilterRegistrationBean<>();

        register.setFilter(new MyFilter5());
        register.setOrder(4);                               // Determine the order of this filter In the Filter Chain.  (***)
        register.setName("MyFilter5");
        register.addUrlPatterns("/Hello2");

        return register;
    } //myFilter5Register

} // end class

