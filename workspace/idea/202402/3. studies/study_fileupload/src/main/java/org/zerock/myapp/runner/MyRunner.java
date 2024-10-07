package org.zerock.myapp.runner;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartResolver;

import java.util.Arrays;
import java.util.Objects;


@Log4j2

// Class contains `required fields`, You have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@Component
public class MyRunner implements CommandLineRunner {
    // org.springframework.web.multipart.support.StandardServletMultipartResolver
    @Setter(onMethod_ = @Autowired)
    private MultipartResolver multipartResolver;


    @Override
    public void run(String... args) {
        log.trace("run({}) invoked.", Arrays.toString(args));

        Objects.requireNonNull(this.multipartResolver);
        log.info("\t+ this.multipartResolver: {}", this.multipartResolver);
    } // run

} // end class
