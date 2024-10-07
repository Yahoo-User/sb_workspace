package org.zerock.myapp.config;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;


@Log4j2

// Class contains `required fields`,
// you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfigImplementingWebSecurityCustomizer

    /*
     * =======================================================
     * @FunctionalInterface
     * public interface WebSecurityCustomizer
     * =======================================================
     * Callback interface for customizing `WebSecurity`.
     * Beans of this type will automatically be used by `WebSecurityConfiguration` to customize `WebSecurity`.
     *
     * Example usage:
     *
     *   @Bean
     *   public WebSecurityCustomizer ignoringCustomizer() {
     *   	return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
     *   } // ignoringCustomizer
     */
    implements WebSecurityCustomizer {

    /*
     * =======================================================
     * public final class WebSecurity
     *      extends AbstractConfiguredSecurityBuilder<Filter, WebSecurity>
     *      implements SecurityBuilder<Filter>, ApplicationContextAware, ServletContextAware
     * =======================================================
     * The `WebSecurity` is created by `WebSecurityConfiguration` to create the `FilterChainProxy`
     * known as the Spring Security Filter Chain (springSecurityFilterChain).
     *
     * The springSecurityFilterChain is the Filter that the `DelegatingFilterProxy` delegates to.
     * Customizations to the `WebSecurity` can be made by creating a `WebSecurityConfigurer`
     * or exposing a `WebSecurityCustomizer` bean.
     */
    @Override
    public void customize(WebSecurity web) {
        log.trace("customize({}) invoked.", web);

//        web.ignoring().anyRequest();
    } // customize

} // end class
