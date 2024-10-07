package org.zerock.myapp.util;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;


@Log4j2
@NoArgsConstructor

@Component
public class JdbcConnectionManagerBean {


    // If using `application.properties` file, The below bean NOT needed.
    //@Bean
    public JdbcConnectionManager jdbcConnectionManager() {
        log.trace("jdbcConnectionManager() invoked.");

        JdbcConnectionManager mgr = new JdbcConnectionManager();
        mgr.setDriveClass("org.h2.Driver");
        mgr.setUrl("jdbc:h2:tcp://localhost/C:/app/h2/data/test");
        mgr.setUsername("sa");
        mgr.setPassword("sa");

        return mgr;
    } // jdbcConnectionManager

} // end class
