package org.zerock.myapp.jwt;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Log4j2

@Data

/* --------------------------------------
 * @ConfigurationProperties
 * --------------------------------------
 * Annotation for externalized configuration.
 *
 * Add this to a class definition or a `@Bean` method in a `@Configuration` class
 * if you want to bind and validate some external Properties (e.g. from a `.properties` file).
 *
 * Binding is either performed by calling `setters` on the annotated class or,
 * if `@ConstructorBinding` is in use, by binding to the constructor parameters.
 *
 * Note that contrary to `@Value`, SpEL expressions are not evaluated since property values are externalized.
 */
@ConfigurationProperties(prefix = "jwt")

@Component
public class JWTProperties implements InitializingBean {
    private String issuer;          // Bound from the `application.properties` file.
    private String secretKey;       // Bound from the `application.properties` file.


    @Override
    public void afterPropertiesSet() {
        log.trace("afterPropertiesSet() invoked.");
        log.info("\t+ this: {}", this);
    } // afterPropertiesSet

} // end class
