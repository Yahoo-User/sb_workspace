package org.zerock.myapp.seq_2.filter;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.Serial;


@Log4j2
@NoArgsConstructor

//@WebFilter( filterName = "MyFilter1", urlPatterns = { "/Hello" } )
public class MyFilter1 extends HttpFilter {
    @Serial private static final long serialVersionUID = 1L;


    @PostConstruct
    void postConstruct() {
        log.trace("postConstruct() invoked.");
    } // postConstruct

    @Override
    public void init(FilterConfig filterConfig) {
        log.trace("init({}) invoked.", filterConfig);
    } // init

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.trace("doFilter(req, res, {}) invoked.", chain);

        log.info("\t+ (1) Pre-processing ...");
        chain.doFilter(request, response);
        log.info("\t+ (2) Post-processing ...");
    } // doFilter

    @PreDestroy
    void preDestroy() {
        log.trace("preDestroy() invoked.");
    } // preDestroy

    @Override
    public void destroy() {
        log.trace("destroy() invoked.");
    } // destroy

} // end class
