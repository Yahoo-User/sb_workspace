package org.zerock.myapp.filter;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zerock.myapp.jwt.JJWTProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@Log4j2
@NoArgsConstructor

@Order(1)
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired private JJWTProvider jjwtProvider;


    @Override
    protected void initFilterBean() throws ServletException {
        log.trace("initFilterBean() invoked.");

    } // initFilterBean

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
        throws ServletException, IOException {
        log.trace("doFilterInternal(req, res, chain) invoked.");

        // 1. Pre-processing ...
        log.info("\t+ Pre-processing ...");
        log.info("\t+ this.jjwtProvider: {}", this.jjwtProvider);

        chain.doFilter(req, res);

        // 2. Post-processing ...
        log.info("\t+ Post-processing ...");
    } // doFilterInternal

    @Override
    public void destroy() {
        log.trace("destroy() invoked.");

    } // destroy

    // ------------------------
    // How to set URL-pattern to be filtered in the OncePerRequestFilter ?
    // ------------------------
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        log.trace("shouldNotFilter(request) invoked.");

//        return true;   // Do *Not* Filter !
        return false;
    } // shouldNotFilter
} // end class
