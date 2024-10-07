package org.zerock.myapp.seq_5.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Log4j2
@NoArgsConstructor
public class HandlerInterceptor2 implements HandlerInterceptor, BeanNameAware {    // Spring Interceptor


    @Override
    public void setBeanName(String name) {
        log.trace("setBeanName({}) invoked.", name);
    } // setBeanName


    @Override
    // (1) Pre-processing before handling a request by a handler declared in the controller.
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.trace("(1) preHandle(req, res, handler: {}) invoked.", handler);

        return true;
    } // preHandle

    @Override
    // (2) Post-processing after creating a model & a view name by a handler declared in the controller.
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
        log.trace("(2) postHandle(req, res, handler: {}, modelAndView: {}) invoked.", handler, modelAndView);
    } // postHandle

    @Override
    // (3) Processing after completion of a request, regardless of raising an exception occurred in the handler of a controller.
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception e) {
        log.trace("(3) afterCompletion(req, res, handler: {}, e: {}) invoked.", handler, e);
    } // afterCompletion

} // end class

