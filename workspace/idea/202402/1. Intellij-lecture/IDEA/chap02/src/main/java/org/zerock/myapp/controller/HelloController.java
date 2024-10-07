package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@NoArgsConstructor

@RestController
public class HelloController
        implements InitializingBean, DisposableBean {


//    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    String hello() {
        log.trace("hello() invoked.");

        return "hello";
    } // hello

    @Override
    public void afterPropertiesSet() throws Exception {
        log.trace("afterPropertiesSet() invoked.");

    } // afterPropertiesSet

    @Override
    public void destroy() throws Exception {
        log.trace("destroy() invoked.");

    } // destroy

} // end class
