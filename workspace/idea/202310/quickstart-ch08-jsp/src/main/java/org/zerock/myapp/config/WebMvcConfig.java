package org.zerock.myapp.config;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Log4j2
@NoArgsConstructor

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.trace("addViewControllers({}) invoked.", registry);

//        registry.
//            addViewController("/board/insertBoard").
//            setStatusCode(HttpStatus.OK).
//            setViewName("board/insertBoard");
    } // addViewControllers

} // end class

