package org.zerock.myapp.seq_4.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Log4j2
@NoArgsConstructor

@RequestMapping("/interceptor")

@Controller("interceptorController")
public class InterceptorController implements BeanNameAware {


        @Override
        public void setBeanName(String name) {
                log.trace("setBeanName({}) invoked.", name);
        } // setBeanName


        @GetMapping("/{handlerSequence}")
        String handler(@PathVariable("handlerSequence") Integer handlerSequence, Model model) {
                log.trace("handler({}, {}) invoked.", handlerSequence, model);

                model.addAttribute("handlerSequence", handlerSequence);
                return "interceptor/handler";
        } // handler

} // end class
