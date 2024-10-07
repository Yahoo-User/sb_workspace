package org.zerock.myapp.annotation;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.zerock.myapp.oauth2.SessionUser;

import javax.servlet.http.HttpSession;


@Log4j2

// Class contains `required fields`,
// you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@Component
public class LoginUserArgumentResolver

    /*
     * -----------------------------------
     * HandlerMethodArgumentResolver
     * -----------------------------------
     * Strategy interface for resolving method parameters into argument values in
     * the context of a given request.
     */
    implements HandlerMethodArgumentResolver, InitializingBean {

    @Autowired
    private HttpSession httpSession;


    // Whether the given method parameter is supported by this resolver.
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.trace("supportsParameter({}) invoked.", parameter);

        boolean isLoginUserAnnotation = ( parameter.getParameterAnnotation(LoginUser.class) != null );
        log.info("\t+ isLoginUserAnnotation: {}", isLoginUserAnnotation);

        boolean isSessionUserParameterType = SessionUser.class.equals( parameter.getParameterType() );
        log.info("\t+ isSessionUserParameterType: {}", isSessionUserParameterType);

        return isLoginUserAnnotation && isSessionUserParameterType;
    } // supportsParameter

    /*
     * From interface:
     *   `HandlerMethodArgumentResolver` Resolves a method parameter into an argument value from a given request.
     *   A `ModelAndViewContainer` provides access to the model for the request.
     *   A `WebDataBinderFactory` provides a way to create a `WebDataBinder` instance
     *   when needed for data binding and type conversion purposes.
     *
     * Specified by:
     *   resolveArgument in interface `HandlerMethodArgumentResolver`
     *
     * Params:
     *   parameter – the method parameter to resolve.
     *               This parameter must have previously been passed to supportsParameter
     *               which must have returned true.
     *   mavContainer – the `ModelAndViewContainer` for the current request
     *   webRequest – the current request
     *   binderFactory – a factory for creating `WebDataBinder` instances
     *
     * Returns:
     *   the resolved argument value, or null if not resolvable
     */
    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        log.trace("resolveArgument({}, {}, {}, {}) invoked.", parameter, mavContainer, webRequest, binderFactory);

        return this.httpSession.getAttribute("user");
    } //resolveArgument


    @Override
    public void afterPropertiesSet() {
        log.trace("afterPropertiesSet() invoked.");
        log.info("\t+ this.httpSession: {}", this.httpSession);
    } // afterPropertiesSet

} // end class
