package org.zerock.myapp.config;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.zerock.myapp.service.BoardUserDetailsService;

import java.util.Objects;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@Log4j2
@NoArgsConstructor

// Add this annotation to a `@Configuration` class to have the `Spring Security configuration` defined
// in any `WebSecurityConfigurer` or more likely by exposing a `SecurityFilterChain` bean:
@EnableWebSecurity

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {
//    @Setter(onMethod_ = @Autowired)
//    private DataSource dataSource;

    @Setter(onMethod_ = @Autowired)
    private BoardUserDetailsService boardUserDetailsService;


    // ---------------------------------------- //
    // @Since v2.7.14 and v3.1.2
    // ---------------------------------------- //
    // * NOTE : This `userDetailsService` bean cannot be used with `authenticate(auth)` method like the below.

/*
    @Bean
    public UserDetailsService userDetailsService() {
        log.trace("userDetailsService() invoked.");

        // @Since v2.7.14 and v3.1.2

        // * NOTE : User.withDefaultPasswordEncoder() is considered unsafe for production and is only intended for sample applications.
        //          'withDefaultPasswordEncoder()' is deprecated. (***)
        UserDetails manager = User.withDefaultPasswordEncoder().username("manager").password("manager123").roles("MANAGER").build();
        UserDetails admin   = User.withDefaultPasswordEncoder().username("admin").password("admin123").roles("ADMIN", "MANAGER").build();

        return new InMemoryUserDetailsManager(manager, admin);
    } // userDetailsService
*/


    // ---------------------------------------- //
    // @Since v2.7.14 and v3.1.2
    // ---------------------------------------- //
    // * NOTE : This `authenticate(auth)` method cannot be used with `userDetailsService` bean like the above.

/*

    @Autowired
    public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
        log.trace("authenticate({}) invoked.", auth);

        // @Since v2.7.14 and v3.1.2
//        auth.inMemoryAuthentication().withUser("manager").password("{noop}manager123").roles("MANAGER");
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin123").roles("ADMIN");

//        -------------------------------------

        Objects.requireNonNull(this.dataSource);

        // @Since v2.7.14 and v3.1.2
        String sql_1 = "SELECT id AS username, '{noop}' || password AS password, enabled FROM member WHERE id = ?";
        String sql_2 = "SELECT id, role FROM member WHERE id = ?";

        auth.
            jdbcAuthentication().
            dataSource(this.dataSource).
            usersByUsernameQuery(sql_1).
            authoritiesByUsernameQuery(sql_2);
    } // authenticate
 */


    // ---------------------------------------- //
    // @Since v2.7.14 and v3.1.2
    // ---------------------------------------- //

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.trace("securityFilterChain({}) invoked.", http);

        // ======================================================
        // @Until Spring Boot v2.7.14
        // ======================================================

/*
        http.authorizeHttpRequests( customizer -> customizer.antMatchers("/").permitAll() );
        http.authorizeHttpRequests( customizer -> customizer.antMatchers("/member/**").authenticated() );
        http.authorizeHttpRequests( customizer -> customizer.antMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN") );
        http.authorizeHttpRequests( customizer -> customizer.antMatchers("/admin/**").hasRole("ADMIN") );
*/

//        -------------------

/*
        http.
            authorizeHttpRequests( customizer -> customizer.antMatchers("/").permitAll() ).
            authorizeHttpRequests( customizer -> customizer.antMatchers("/member/**").authenticated() ).
            authorizeHttpRequests( customizer -> customizer.antMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN") ).
            authorizeHttpRequests( customizer -> customizer.antMatchers("/admin/**").hasRole("ADMIN") );
*/

//        -------------------

/*
        http.authorizeHttpRequests(
            customizer -> customizer.
                    antMatchers("/").permitAll().
                    antMatchers("/member/**").authenticated().
                    antMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN").
                    antMatchers("/admin/**").hasRole("ADMIN")
        );
*/


        // ======================================================
        // @Since Spring Boot v3.1.2
        // ======================================================

        // ------------------------
        // 1st. method - OK : Bug CVE-2023-34035 Fix Method.
        // ------------------------
/*
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(instropector);

        http.authorizeHttpRequests(
        customizer ->
            customizer.
                requestMatchers(mvcMatcherBuilder.pattern("/"), mvcMatcherBuilder.pattern("/hello")).permitAll().
                requestMatchers(mvcMatcherBuilder.pattern("/member")).authenticated().
                requestMatchers(mvcMatcherBuilder.pattern("/manager")).hasRole("MANAGER").
                requestMatchers(mvcMatcherBuilder.pattern("/admin")).hasRole("ADMIN").
                // 아래의 코드를 마지막으로 반드시 넣어야 합니다. (***)
                anyRequest().permitAll()
        ); // .authorizeHttpRequests
*/

        // ------------------------
        // 2nd. method - OK : Within Spring Security v6.
        // ------------------------
/*
        http.authorizeHttpRequests(
            customizer -> {
                customizer.requestMatchers(new AntPathRequestMatcher("/"), new AntPathRequestMatcher("/hello")).permitAll();
                customizer.requestMatchers(new AntPathRequestMatcher("/member")).authenticated();
                customizer.requestMatchers(new AntPathRequestMatcher("/manager")).hasAnyRole("MANAGER", "ADMIN");
                customizer.requestMatchers(new AntPathRequestMatcher("/admin")).hasRole("ADMIN");
                // 아래의 코드를 마지막으로 반드시 넣어야 합니다. (***)
                customizer.anyRequest().permitAll();
            }
        );  // .authorizeHttpRequests
*/

        // ------------------------
        // 3rd. method - OK
        // ------------------------
        http.authorizeHttpRequests(
            (customizer) ->
                customizer.
                    requestMatchers(antMatcher("/")).permitAll().
                    requestMatchers(antMatcher("/member/**")).authenticated().
                    requestMatchers(antMatcher("/manager/**")).hasAnyRole("MANAGER", "ADMIN").
                    requestMatchers(antMatcher("/admin/**")).hasRole("ADMIN").
                    // 아래의 코드를 마지막으로 반드시 넣어야 합니다. (***)
                    anyRequest().permitAll()
        ); // .authorizeHttpRequests


        // ======================================================
        // @Since v2.7.14 and v3.1.2 Common
        // ======================================================

//        http.csrf( customizer -> customizer.disable() );
        http.csrf( CsrfConfigurer<HttpSecurity>::disable );

//        -------------------

//        http.formLogin( withDefaults() );
//        http.formLogin( customizer -> customizer.loginPage("/login").defaultSuccessUrl("/loginSuccess") );
        http.formLogin( customizer -> customizer.loginPage("/login").defaultSuccessUrl("/loginSuccess", true) );

//        -------------------

        http.exceptionHandling( customizer -> customizer.accessDeniedPage("/accessDenied") );

//        -------------------

        http.logout( customizer -> customizer.invalidateHttpSession(true).logoutSuccessUrl("/login") );

//        -------------------

        Objects.requireNonNull(this.boardUserDetailsService);
        http.userDetailsService(this.boardUserDetailsService);

//        -------------------

//        http.sessionManagement();
        http.sessionManagement(
            // If RESTful API Service,
//            customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)   // Default
        );


        return http.build();
    } // securityFilterChain


    @Bean
    public PasswordEncoder passwordEncoder() {
        log.trace("passwordEncoder() invoked.");

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    } // passwordEncoder

} // end class
