package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Log4j2
@NoArgsConstructor

@Component
public class MyApplicationListener
        implements ApplicationListener<ApplicationEvent> {


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
//        log.trace("onApplicationEvent({}) invoked.", event);

    } // onApplicationEvent

} // end class
