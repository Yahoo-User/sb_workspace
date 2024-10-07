package org.zerock.myapp.config;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.WebListenerRegistrar;
import org.springframework.boot.web.servlet.WebListenerRegistry;


@Log4j2
@NoArgsConstructor

// ** NOTE : This configuration was replaced with `@ServletComponentScan` annotation.

//@Configuration
public class WebListenerConfig implements WebListenerRegistrar {


    @Override
    public void register(WebListenerRegistry registry) {
        log.trace("register({}) invoked.", registry);

        registry.addWebListeners(
            "org.zerock.myapp.listener.RequestListener",

            "org.zerock.myapp.listener.SessionListener",
            "org.zerock.myapp.listener.SessionIdListener",
            "org.zerock.myapp.listener.SessionBindingListener",
            "org.zerock.myapp.listener.SessionActivationListener",

            "org.zerock.myapp.listener.ContextListener",

            "org.zerock.myapp.listener.RequestScopeAttributeListener",
            "org.zerock.myapp.listener.SessionScopeAttributeListener",
            "org.zerock.myapp.listener.ApplicationScopeAttributeListener"
        );
    } // register

} // end class
