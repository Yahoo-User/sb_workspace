package org.zerock.myapp.seq_7.interceptor;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;


/**
 * In Spring Boot 3, the `WebRequestInterceptor` is an interceptor used for pre- and post-processing of web requests.
 * As part of the Spring MVC framework,
 *      (1) it is useful for inserting *common logic before and after a specific request is processed.
 *      (2) The `WebRequestInterceptor` is particularly often used to define logic that executes before and after *rendering a view.
 *      (3) The HTTP *Servlet Request is *Not a target. (***)
 */

@Log4j2
@NoArgsConstructor
public class WebRequestInterceptor1 implements WebRequestInterceptor, BeanNameAware {


    @Override
    public void setBeanName(String name) {
        log.trace("setBeanName({}) invoked.", name);
    } // XX


    @Override
    // Logic to execute before handling the web request.
    public void preHandle(WebRequest request) {
        log.trace("(1) preHandle(request: {}) invoked.", request);
    } // preHandle

    @Override
    // Logic to execute after request handling but before view rendering.
    public void postHandle(WebRequest request, ModelMap model) {
        log.trace("(2) postHandle(request: {}, model: {}) invoked.", request, model);
    } // postHandle

    @Override
    // Logic to execute after rendering the view.
    public void afterCompletion(WebRequest request, Exception ex) {
        log.trace("(3) afterCompletion(request: {}, ex: {}) invoked.", request, ex);
    } // afterCompletion

} // end class
