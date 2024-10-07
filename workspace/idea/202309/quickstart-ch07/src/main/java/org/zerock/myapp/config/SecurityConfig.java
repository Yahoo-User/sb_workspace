package org.zerock.myapp.config;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.myapp.service.BoardUserDetailsService;

import javax.sql.DataSource;


@Log4j2

// Class contains `required fields`,
// you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@Configuration
@EnableWebSecurity
public class SecurityConfig
    extends WebSecurityConfigurerAdapter implements InitializingBean {
    @Autowired private DataSource dataSource;
    @Autowired private BoardUserDetailsService boardUserDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.trace("*******************************************");
        log.trace("* configure({}) invoked.", http);
        log.trace("*******************************************");

        // ----------------------------------------------
        // 1. Customizing Access Control For The Request URIs.
        // ----------------------------------------------

        // Access Control Table :
        // (1) Anyone can access `/` root URI.
        // (2) Only anyone who is `authenticated` & has a `MEMBER` role can access URI with started `/member`.
        // (3) Only anyone who is `authenticated` & has a `MANAGER` role can access URI with started `/member`.
        // (4) Only anyone who is `authenticated` & has `MANAGER`, `ADMIN` roles can access URI with started `/manager`.
        // (5) Only anyone who is `authenticated` & has a `ADMIN` role can access URI with started `/admin`.


        // 1-1. Method #1

/*
        // `http.authorizeRequests()` method invocation also is the same as `http.authorizeHttpRequests()`.
        http.authorizeRequests().antMatchers("/").permitAll();                                  // (1)
        http.authorizeRequests().antMatchers("/member").authenticated();                        // (2)
        http.authorizeRequests().antMatchers("/manager").hasRole("MANAGER");                    // (3)
        // http.authorizeRequests().antMatchers("/manager").hasAnyRole("MANAGER, ADMIN");       // (4)
        http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN");                        // (4)
*/

/*
        // `http.authorizeRequests()` method invocation also is the same as `http.authorizeHttpRequests()`.
        http.
            authorizeRequests().
//            authorizeHttpRequests().
                antMatchers("/").permitAll().                                                   // (1)
                antMatchers("/member").authenticated().                                         // (2)
                antMatchers("/manager").hasRole("MANAGER").                                     // (3)
                // antMatchers("/manager").hasAnyRole("MANAGER", "ADMIN").                      // (4)
                antMatchers("/admin").hasRole("/ADMIN");                                        // (5)
*/


        // 1-2. Method #2

/*
        // `http.authorizeRequests()` method invocation also is the same as `http.authorizeHttpRequests()`.
        http.authorizeRequests(customizer -> customizer.antMatchers("/").permitAll());                              // (1)
        http.authorizeRequests(customizer -> customizer.antMatchers("/member").authenticated());                    // (2)
        http.authorizeRequests(customizer -> customizer.antMatchers("/manager").hasRole("MANAGER"));                // (3)
        // http.authorizeRequests(customizer -> customizer.antMatchers("/manager").hasAnyRole("MANAGER, ADMIN"));   // (4)
        http.authorizeRequests(customizer -> customizer.antMatchers("/admin").hasRole("ADMIN"));                    // (5)
*/

        // `http.authorizeRequests()` method invocation also is the same as `http.authorizeHttpRequests()`.
        http.
            authorizeRequests(
            // authorizeHttpRequests(
                customizer ->
                    customizer.
                        antMatchers("/").permitAll().                                           // (1)
                        antMatchers("/member").authenticated().                                 // (2)
                        antMatchers("/manager").hasRole("MANAGER").                             // (3)
                        // antMatchers("/manager").hasAnyRole("MANAGER", "ADMIN").              // (4)
                        antMatchers("/admin").hasRole("ADMIN")                                  // (5)
        );


        // ----------------------------------------------
        // 2. Disable CSRF If You Want To Use RESTful Services
        // ----------------------------------------------

        // http.csrf().disable();                                  // 2-1. Method #1
        // http.csrf(customizer -> customizer.disable());          // 2-2. Method #2
        http.csrf(AbstractHttpConfigurer::disable);             // 2-3. Method #3


        // ----------------------------------------------
        // 3. Asking Default Login Screen If *NOT* Authenticated
        // ----------------------------------------------

        // http.formLogin();                                       // 3-1. Method #1
        http.formLogin( Customizer.withDefaults() );            // 3-1. Method #2


        // ----------------------------------------------
        // 4. Customizing Default Login Screen As User-Defined
        // ----------------------------------------------

        // Method #1

/*
        http.formLogin().
                // To set up the `User-defined Login URI` to be authenticated if required.
                loginPage("/login").

                    // In the `defaultSuccessUrl(defaultSuccessUrl, alwaysUse)`,

                    // (1) If `alwaysUse` is `false`,
                    //      Go to the Original Request URI after authentication success.
                    // defaultSuccessUrl("/loginSuccess");         // == `defaultSuccessUrl("/loginSuccess", false)`
                    // defaultSuccessUrl("/loginSuccess", false);

                    // (2) If boolean is `true`,
                    //      Go to the specified `defaultSuccessUrl` after authentication success.
                    defaultSuccessUrl("/loginSuccess", true);
*/

        // Method #1
        //http.formLogin(customizer -> customizer.loginPage("/login").defaultSuccessUrl("/loginSuccess"));

        // Method #2
        http.formLogin(customizer -> customizer.loginPage("/login").defaultSuccessUrl("/loginSuccess", true));


        // ----------------------------------------------
        // 5. Customizing Exception Handling
        // ----------------------------------------------

        // Method #1
        // http.exceptionHandling().accessDeniedPage("/accessDenied");

        // Method #2
        http.exceptionHandling(customizer -> customizer.accessDeniedPage("/accessDenied"));


        // ----------------------------------------------
        // 6. Customizing logout
        // ----------------------------------------------

        // Method #1
//        http.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
        //http.logout().invalidateHttpSession(true).deleteCookies(cookieNamesToClear).logoutSuccessUrl("/login");

        // Method #2
        http.logout(customizer -> customizer.invalidateHttpSession(true).logoutSuccessUrl("/login"));


        // ----------------------------------------------
        // 7. Customizing Default UserDetailsService by User-Defined one.
        // ----------------------------------------------

        log.info("\t+ this.boardUserDetailsService: {}", this.boardUserDetailsService);
        http.userDetailsService(this.boardUserDetailsService);
    } // configure


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.trace("*******************************************");
        log.trace("configure({}) invoked.", auth);
        log.trace("*******************************************");

        // ----------------------------------------------
        // 1. Register In-Memory Users
        // ----------------------------------------------

/*
        auth.inMemoryAuthentication().
                withUser("member").
                password("{noop}member123").
                roles();

        auth.inMemoryAuthentication().
                withUser("manager").
                password("{noop}manager123").
                roles("MANAGER");

        auth.inMemoryAuthentication().
                withUser("admin").
                password("{noop}admin123").
                roles("MANAGER", "ADMIN");
                //roles("ADMIN");
*/


        // ----------------------------------------------
        // 2. Register JDBC Users
        // ----------------------------------------------

        log.info("\t+ this.dataSource: {}", this.dataSource);

        String usersQuery = "SELECT id AS username, concat('{noop}', password) AS password, enabled FROM member WHERE id = ?";
        String authoritiesQuery = "SELECT id, role FROM member WHERE id = ?";

        auth.jdbcAuthentication().
                dataSource(this.dataSource).
                usersByUsernameQuery(usersQuery).
                authoritiesByUsernameQuery(authoritiesQuery);
    } // configure

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.trace("passwordEncoder() invoked.");

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    } // passwordEncoder


    @Override
    public void afterPropertiesSet() {
        log.trace("afterPropertiesSet() invoked.");
        log.info("\t+ this.dataSource: {}", this.dataSource);
        log.info("\t+ this.boardUserDetailsService: {}", this.boardUserDetailsService);
    } // afterPropertiesSet

} // end class





