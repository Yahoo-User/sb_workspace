package org.zerock.myapp.annotation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.zerock.myapp.oauth2.SessionUser;

import javax.servlet.http.HttpSession;


@Log4j2
@AllArgsConstructor

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private HttpSession httpSession;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.trace("supportsParameter({}) invoked.", parameter);

        boolean isLoginUserAnnotation = ( parameter.getParameterAnnotation(LoginUser.class) != null );
        log.info("\t+ isLoginUserAnnotation: {}", isLoginUserAnnotation);

        boolean isSessionUserParameterType = SessionUser.class.equals( parameter.getParameterType() );
        log.info("\t+ isSessionUserParameterType: {}", isSessionUserParameterType);

        return isLoginUserAnnotation && isSessionUserParameterType;
    } // supportsParameter

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        log.trace("resolveArgument({}, {}, {}, {}) invoked.", parameter, mavContainer, webRequest, binderFactory);

        return this.httpSession.getAttribute("user");
    } //resolveArgument

} // end class
