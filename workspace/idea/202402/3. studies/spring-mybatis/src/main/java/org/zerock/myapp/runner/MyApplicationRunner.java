package org.zerock.myapp.runner;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Log4j2
@NoArgsConstructor

@Component  // * Required *
public class MyApplicationRunner implements ApplicationRunner {
    @Setter(onMethod_ = @Autowired)
    private SqlSessionFactory sqlSessionFactory;

    @Setter(onMethod_ = @Autowired)
    private SqlSession sqlSession;


    @Override
    public void run(ApplicationArguments args) {
        log.trace("run({}) invoked.", args);

        Objects.requireNonNull(this.sqlSessionFactory);
        log.info("\t+1. this.sqlSessionFactory: {}", this.sqlSessionFactory);

        Objects.requireNonNull(this.sqlSession);
        log.info("\t+2. this.sqlSession: {}", this.sqlSession);
    } // run

} // end class
