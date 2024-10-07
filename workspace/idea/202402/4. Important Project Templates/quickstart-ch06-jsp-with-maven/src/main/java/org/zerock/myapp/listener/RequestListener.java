package org.zerock.myapp.listener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

//=================================================
// 1. For Spring Boot 2.7.x
//=================================================
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

//=================================================
// 2. For Spring Boot 3.1.x
//=================================================
//import jakarta.servlet.ServletRequestEvent;
//import jakarta.servlet.ServletRequestListener;
//import jakarta.servlet.annotation.WebListener;
//import jakarta.servlet.http.HttpServletRequest;


@Log4j2
@NoArgsConstructor

@WebListener
public class RequestListener implements ServletRequestListener {


    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.trace("");
        log.trace("=======================================");
        log.trace("* requestInitialized(sre) invoked.");
        log.trace("=======================================");

        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        String requestURL = request.getRequestURL().toString();
        String requestMethod = request.getMethod();
        Map<String, String[]> requestParameterMap = request.getParameterMap();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        Map<String, String[]> paramMap = request.getParameterMap();

        log.info("\t+ 1. Method: {}, URL: {}", method, url);
        log.info("\t+ 2. Parameters: {}", paramMap);
    } // requestInitialized

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.trace("=======================================");
        log.trace("* requestDestroyed(sre) invoked.");
        log.trace("=======================================");

        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        Map<String, String[]> paramMap = request.getParameterMap();

        log.info("\t+ 1. Method: {}, URL: {}", method, url);
        log.info("\t+ 2. Parameters: {}", paramMap);
    } // requestDestroyed

} // end class
