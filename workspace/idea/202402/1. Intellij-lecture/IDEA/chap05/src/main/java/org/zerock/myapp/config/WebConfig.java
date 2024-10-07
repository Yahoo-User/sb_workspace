package org.zerock.myapp.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zerock.myapp.annotation.LoginUserArgumentResolver;

import java.util.List;
import java.util.Objects;


@Log4j2

// Class contains `required fields`,
// you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@Configuration
public class WebConfig implements WebMvcConfigurer, InitializingBean {
    @Autowired private LoginUserArgumentResolver loginUserArgumentResolver;


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        log.trace("addArgumentResolvers({}) invoked.", resolvers);

        Objects.requireNonNull(this.loginUserArgumentResolver);
        log.info("\t+ this.loginUserArgumentResolver: {}", loginUserArgumentResolver);

        resolvers.add(this.loginUserArgumentResolver);
    } // addArgumentResolvers


    @Override
    public void afterPropertiesSet() throws Exception {
        log.trace("afterPropertiesSet() invoked.");
        log.info("\t+ this.loginUserArgumentResolver: {}", this.loginUserArgumentResolver);
    } // afterPropertiesSet

} // end class
