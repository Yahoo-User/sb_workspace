package org.zerock.myapp.listener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringBootVersion;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;


//=================================================
// 1. For Spring Boot 2.7.x
//=================================================
//import javax.servlet.ServletRequestEvent;
//import javax.servlet.ServletRequestListener;
//import javax.servlet.annotation.WebListener;
//import javax.servlet.http.HttpServletRequest;

//=================================================
// 2. For Spring Boot 3.1.x
//=================================================
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;


@Log4j2

// Class contains `required fields`, You have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@WebListener
public final class RequestListener implements ServletRequestListener {


    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        String url = request.getRequestURL().toString();
        if(url.contains("favicon.ico")) return;

        String requestId;

        try {
            Method getRequestIdMethod = request.getClass().getMethod("getRequestId");
            Objects.requireNonNull(getRequestIdMethod);

            requestId = (String) getRequestIdMethod.invoke(request);
        } catch (Exception e) {
            requestId = SpringBootVersion.getVersion();
        } // try-catch

        boolean requestedSessionIdValid = request.isRequestedSessionIdValid();
        String requestedSessionId = request.getRequestedSessionId();

        String format =
                String.format("* Request Initialized (RID: %s, SID: %s, %s).",
                                requestId, requestedSessionId, requestedSessionIdValid);

        log.trace("");
        log.trace("****************************************************************************");
        log.trace(format);
        log.trace("****************************************************************************");

        String method = request.getMethod();
        Map<String, String[]> parameterMap = request.getParameterMap();

        log.info("\t1. Method: {}, URL: {}", method, url);
        log.info("\t2. ParameterMap: {}", parameterMap);
    } // requestInitialized

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        String url = request.getRequestURL().toString();
        if(url.contains("favicon.ico")) return;

        String requestId;

        try {
            Method getRequestIdMethod = request.getClass().getMethod("getRequestId");
            Objects.requireNonNull(getRequestIdMethod);

            requestId = (String) getRequestIdMethod.invoke(request);
        } catch (Exception e) {
            requestId = SpringBootVersion.getVersion();
        } // try-catch

        boolean requestedSessionIdValid = request.isRequestedSessionIdValid();
        String requestedSessionId = request.getRequestedSessionId();

        String format =
                String.format("* Request Initialized (RID: %s, SID: %s, %s).",
                                requestId, requestedSessionId, requestedSessionIdValid);

        log.trace("****************************************************************************");
        log.trace(format);
        log.trace("****************************************************************************");
    } // requestDestroyed

} // end class
