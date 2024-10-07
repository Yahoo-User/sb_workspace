package org.zerock.myapp.runner;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.zerock.myapp.config.JdbcConfigurationProperties;
import org.zerock.myapp.util.JdbcConnectionManager;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Objects;


@Log4j2
@NoArgsConstructor

@Component
public class CustomCommandLineRunner
    implements CommandLineRunner {
    @Autowired private JdbcConfigurationProperties jdbcConnProps;
    @Autowired private JdbcConnectionManager jdbcConnMgr;


    @Override
    public void run(String... args) throws Exception {
        log.trace("run({}) invoked.", Arrays.toString(args));

        log.info("\t+ 1. this.jdbcConnProps: {}", this.jdbcConnProps);
        log.info("\t+ 2. this.jdbcConnMgr: {}", this.jdbcConnMgr);

        Connection conn = this.jdbcConnMgr.getConnection();
        Objects.requireNonNull(conn);

        try (conn) {
            log.info("\t+ conn: {}", conn);
        } // try-with-resources
    } // run

} // end class
