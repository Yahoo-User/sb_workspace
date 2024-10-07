package org.zerock.myapp.filter;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Log4j2
@NoArgsConstructor

// Ordering of Filter Chain
@Order(1)

//@Component
public class TestFilter1 extends OncePerRequestFilter {


    // ============================================================
    // 1. Regardless of the `@Order(n)` annotations of all filters,
    //    All the `initFilterBean` callbacks Invoked alphabetically *firstly* before context initialized.
    // 2. According to the forward order Of `@Order(n)` annotations of all filters,
    //    All the `initFilterBean` Callback Invoked *secondly* after context initialized.
    // ============================================================
    @Override
    protected void initFilterBean() throws ServletException {
        log.trace("initFilterBean() invoked.");

    } // initFilterBean

    // ============================================================
    // 1. According to the *forward* order of `@Order(n)` annotations of all filters,
    //    All the *pre-processing* processed.
    // 2. According to the *reverse* order of `@Order(n)` annotations of all filters,
    //    All the *post-processing* processed.
    // ============================================================
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
            log.trace("doFilterInternal(req, res, chain) invoked.");

            log.info("\t+ Pre-processing ...");
            chain.doFilter(req, res);
            log.info("\t+ Post-processing ...");
    } // doFilterInternal

    // ============================================================
    // 1. According to the forward order Of `@Order(n)` annotations of all filters,
    //    All the `destroy` callback invoked *firstly* before context destroyed.
    // 2. According to the reverse order Of `@Order(n)` annotations of all filters,
    //    All the `destroy` Callback Invoked *secondly* before context destroyed.
    // ============================================================
    @Override
    public void destroy() {
        log.trace("destroy() invoked.");

    } // destroy

} // end class
