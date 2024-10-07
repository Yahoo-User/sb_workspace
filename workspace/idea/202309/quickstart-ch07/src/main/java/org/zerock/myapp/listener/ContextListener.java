package org.zerock.myapp.listener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


//=================================================
// 1. For Spring Boot 2.7.x
//=================================================
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//=================================================
// 2. For Spring Boot 3.1.x
//=================================================
//import jakarta.servlet.ServletContextEvent;
//import jakarta.servlet.ServletContextListener;
//import jakarta.servlet.annotation.WebListener;


@Log4j2

// Class contains `required fields`,
// you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@WebListener
public class ContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.trace("---------------------------------------");
        log.trace("* contextInitialized(sce) invoked.");
        log.trace("---------------------------------------");

        String contextPath = sce.getServletContext().getContextPath();
        log.info("\t+ contextPath: {}", contextPath);
    } // contextInitialized

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.trace("---------------------------------------");
        log.trace("* contextDestroyed(sce) invoked.");
        log.trace("---------------------------------------");

        String contextPath = sce.getServletContext().getContextPath();
        log.info("\t+ contextPath: {}", contextPath);
    } // contextDestroyed

} // end class
