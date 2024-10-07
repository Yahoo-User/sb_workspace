package org.zerock.myapp.security;

//import lombok.Setter;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import java.util.Objects;
//import java.util.UUID;


//@Log4j2

/*
 * ===========================================================
 * `proxyBeanMethods` property
 * ===========================================================
 * Specify whether `@Bean` methods should get proxied in order to enforce bean lifecycle behavior.
 */
//@Configuration(proxyBeanMethods = false)

//@EnableWebSecurity(debug = true)
//@EnableWebSecurity
public class SecurityConfig4SpringBootV2

    // ***************************************************************************
    // In the Spring Boot v2.7.x
    // ***************************************************************************

    /*
     * ===========================================================
     * 1. `WebSecurityCustomizer` interface                 - XX
     * ===========================================================
     * Callback interface for customizing `WebSecurity`.
     * Beans of this type will automatically be used by `WebSecurityConfiguration` to customize `WebSecurity`.
     *
     * Example usage:
     *
     *   @Bean
     *   public WebSecurityCustomizer ignoringCustomizer() {
     *   	return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
     *   } // ignoringCustomizer
     *
     */

    //implements WebSecurityCustomizer {

    // 1. Override from the super class ** `WebSecurityCustomizer` ** declared in the Spring Boot v2.7.x & v3.x
//    @Override
//    public void customize(WebSecurity web) { ;; } // customize


    /*
     * ===========================================================
     * 2. * @Deprecated * `WebSecurityConfigurerAdapter`    - XX
     * ===========================================================
     * Instead, use a `org.springframework.security.web.SecurityFilterChain` Bean
     * to configure `HttpSecurity` or a `WebSecurityCustomizer` Bean to configure `WebSecurity`.
     *
     *       @Bean
     *       public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     *           http.authorizeHttpRequests( (authz) -> authz.anyRequest().authenticated() );
     *           // ...
     *
     *           return http.build();
     *       } // securityFilterChain
     *
     *      @Bean
     *      public WebSecurityCustomizer webSecurityCustomizer() {
     *          return (web) -> web.ignoring().antMatchers("/resources/**");
     *      } // webSecurityCustomizer
     *
     */

    //extends WebSecurityConfigurerAdapter {      // `@Deprecated` - Abstract Class.

    // 2. Override from the super class ** `WebSecurityConfigurerAdapter` ** in the Spring Boot v2.7.x
//    @Deprecated
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {} // configure

{
//    @Setter(onMethod_ = @Autowired)
//    private SecurityUserDetailsService securityUserDetailsService;


    /*
     * Automatically called back in the process of (re-)booting Spring Boot Application.
     */
//    @Bean()
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        log.trace("securityFilterChain({}) invoked.", http);


        // ***************************************************************************
        // Step.1 Set `UserDetailsService` Interface Implementation Object.
        // ***************************************************************************

        /*
         * ===========================================================
         * `HttpSecurity.userDetailsService(UserDetailsService Implementation)` method
         * ===========================================================
         * set `Interface UserDetailsService` Implementation Object
         * to load & return `Interface UserDetails` Implementation Object to get the specified Username Credentials.
         *
         */

//        Objects.requireNonNull(this.securityUserDetailsService);
//        http.userDetailsService(this.securityUserDetailsService);

//        log.info("\t+ Step.1 Done.");
//        log.info("\t\t- Set `UserDetailsService` Using `SecurityUserDetailsService` Bean");

//        ---

        // ***************************************************************************
        // Step.2 The configuration requires authentication to the specified URLs and will grant access to
        //        URLs starting to only the specified user who has the specified roles.
        // ***************************************************************************

        /*
         * ===========================================================
         * @Deprecated(since = "6.1", forRemoval = true)
         * `HttpSecurity.authorizeHttpRequests()`
         * ===========================================================
         * Allows restricting access based upon the `HttpServletRequest`
         * using `RequestMatcher` implementations (i.e. via `URL patterns`).
         *
         * Example Configurations :
         *
         *  1. The most basic example is to configure all URLs to require the role "ROLE_USER".
         *     The configuration below requires authentication to every URL
         *     and will grant access to both the user "admin" and "user".
         *
         *      @Configuration
         *      @EnableWebSecurity
         *      public class AuthorizeUrlsSecurityConfig {
         *
         *        @Bean
         *        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         *              // Deprecated. (***)
         *   		    http.authorizeHttpRequests().requestMatchers("/**").hasRole("USER").and().formLogin();
         *
         *   		    return http.build();
         *        } // securityFilterChain
         *
         *        @Bean
         *        public UserDetailsService userDetailsService() {
         *   		    UserDetails user =
         *   		        User.withDefaultPasswordEncoder()
         *   			        .username("user")
         *   			        .password("password")
         *   			        .roles("USER")
         *   			        .build();
         *
         *   		    UserDetails admin =
         *   		        User.withDefaultPasswordEncoder()
         *   			        .username("admin")
         *   			        .password("password")
         *   			        .roles("ADMIN", "USER")
         *   			        .build();
         *
         *   		    return new InMemoryUserDetailsManager(user, admin);
         *        } // userDetailsService
         *
         *      } // end class
         *
         *  2. We can also configure multiple URLs.
         *     The configuration below requires authentication to every URL and will grant access to
         *     URLs starting with /admin/ to only the "admin" user.
         *
         *     All other URLs either user can access.
         *
         *      @Configuration
         *      @EnableWebSecurity
         *      public class AuthorizeUrlsSecurityConfig {
         *
         *          @Bean
         *          public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         *              // Deprecated. (***)
         *   		    http.authorizeHttpRequests()
         *   				    .requestMatchers("/admin").hasRole("ADMIN")
         *   				    .requestMatchers("/**").hasRole("USER")
         *   				    .and()
         *   			        .formLogin();
         *
         *   		    return http.build();
         *          } // securityFilterChain
         *
         *          @Bean
         *          public UserDetailsService userDetailsService() {
         *   		    UserDetails user =
         *   		        User.withDefaultPasswordEncoder()
         *   			        .username("user")
         *   			        .password("password")
         *   			        .roles("USER")
         *   			        .build();
         *
         *   		    UserDetails admin =
         *   		        User.withDefaultPasswordEncoder()
         *   			        .username("admin")
         *   			        .password("password")
         *   			        .roles("ADMIN", "USER")
         *   			        .build();
         *
         *   		    return new InMemoryUserDetailsManager(user, admin);
         *          } // userDetailsService
         *
         *      } // end class
         *
         *  3. Note that the matchers are considered in order.
         *     Therefore, the following is invalid
         *     because the first matcher matches every request and will never get to the second mapping:
         *
         *      @Configuration
         *      @EnableWebSecurity
         *      public class AuthorizeUrlsSecurityConfig {
         *
         *          @Bean
         *          public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         *              // Deprecated. (***)
         *   		    http.authorizeHttpRequests()
         *   				    .requestMatchers("/**").hasRole("USER")
         *   				    .requestMatchers("/admin/**").hasRole("ADMIN")
         *   				    .and()
         *   			        .formLogin();
         *
         *   		    return http.build();
         *          } // securityFilterChain
         *
         *      } // end class
         *
         *  4. Deprecated. (***)
         *     For removal in 7.0.
         *
         *     Use `authorizeHttpRequests(Customizer)` instead. (*******)
         */

        // ------------------------
        // 1st. method - OK
        // ------------------------
//        http.authorizeRequests().antMatchers("/", "/system/**").permitAll();
//        http.authorizeRequests().antMatchers("/board/**").authenticated();
//        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        // ------------------------
        // 2nd. method - OK
        // ------------------------
//        http.authorizeRequests( customizer -> customizer.antMatchers("/", "/system/**").permitAll() );
//        http.authorizeRequests( customizer -> customizer.antMatchers("/board/**").authenticated() );
//        http.authorizeRequests( customizer -> customizer.antMatchers("/admin/**").hasRole("ADMIN") );

        // ------------------------
        // 3rd. method - OK
        // ------------------------
//        http.authorizeRequests(
//            customizer ->
//                customizer.
//                    antMatchers("/", "/system/**").permitAll().
//                    antMatchers("/board/**").authenticated().
//                    antMatchers("/admin/**").hasRole("ADMIN").
//                    anyRequest().permitAll()
//        );

//        log.info("\t+ Step.2 Done.");
//        log.info("\t\ta. '/', '/system/**' - Access Permitted To `All` User.");
//        log.info("\t\tb. '/board/**' - Access Permitted To Only `Authenticated` Users.");
//        log.info("\t\tc. '/admin/**' - Access Permitted To Only `Authenticated` & `ROLE_ADMIN` Users.");
//        log.info("\t\td. The other any Requests - Access Permitted To `All` User.");

//        ---

        // ***************************************************************************
        // Step.3 Enable or Disable `CSRF` Protection.
        // ***************************************************************************

        /*
         * ===========================================================
         * @Deprecated(since = "6.1", forRemoval = true)
         * `HttpSecurity.csrf()` method.
         * ===========================================================
         * Enables CSRF protection.
         * This is activated by default when using `EnableWebSecurity`.
         *
         * You can disable it using:
         *
         *   @Configuration
         *   @EnableWebSecurity
         *   public class CsrfSecurityConfig {
         *
         *      @Bean
         *      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         *   		http.csrf((csrf) -> csrf.disable());
         *
         *   		return http.build();
         *      } // securityFilterChain
         *
         *   } // end class
         *
         */

        // ------------------------
        // 1st. method - OK : Using Lambda Expression
        // ------------------------
//        http.csrf( csrf -> csrf.disable() );

        // ------------------------
        // 2nd. method - OK : Using Method Reference
        // ------------------------
//        http.csrf(AbstractHttpConfigurer::disable);

//        log.info("\t+ Step.3 Done.");
//        log.info("\t\t- Disabled `CSRF` Protection.");

//        ---

        // ***************************************************************************
        // Step.4 Customizing Form Login using login page & default login success url.
        // ***************************************************************************

        /*
         * ===========================================================
         * @Deprecated(since = "6.1", forRemoval = true)
         * HttpSecurity.formLogin() method.
         * ===========================================================
         * For removal in 7.0.
         *
         * Use `formLogin(Customizer)` or `formLogin(Customizer.withDefaults())` to stick with defaults.
         *
         */

        // ------------------------
        // 1st. method - OK
        // ------------------------
//        http.formLogin().loginPage("/system/login").defaultSuccessUrl("/board/getBoardList", true);

        // ------------------------
        // 2nd. method - OK
        // ------------------------
//        http.formLogin(
//            customizer ->
//                customizer.

                    /*
                     * ===========================================================
                     * customizer.loginPage(String loginUrl) method.
                     * ===========================================================
                     * Specifies the URL to send users to if login is required.
                     * If used with `EnableWebSecurity`,
                     *      a default login page will be generated when this attribute is NOT specified.
                     * If a URL is specified or this is not being used in conjunction with `EnableWebSecurity`,
                     *      users are required to process the specified URL to generate a login page.
                     *
                     * In general, the login page should create a form that submits a request
                     * with the following requirements to work with `UsernamePasswordAuthenticationFilter`:
                     *
                     *  a. It must be an `HTTP POST`
                     *  b. It must be submitted to `loginProcessingUrl(String)`
                     *  c. It should include the `username` as an HTTP parameter by the name of `usernameParameter(String)`
                     *  d. It should include the `password` as an HTTP parameter by the name of `passwordParameter(String)`
                     *
                     * -----------------------------
                     * 1. Example login.jsp :
                     * -----------------------------
                     *
                     *    Login pages can be rendered with any technology
                     *    you choose so long as the rules above are followed.
                     *
                     *    Below is an example `login.jsp` that can be used as a quick start
                     *    when using JSP's or as a baseline to translate into another view technology.
                     *
                     *   <c:url value="/login" var="loginProcessingUrl"/>
                     *
                     *   <form action="${loginProcessingUrl}" method="post">
                     *
                     *      <fieldset>
                     *          <legend>Please Login</legend>
                     *
                     *          <!-- use `param.erro`r assuming `FormLoginConfigurer#failureUrl` contains the query parameter error -->
                     *          <c:if test="${param.error != null}">
                     *              <div>
                     *                  Failed to login.
                     *                  <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                     *                    Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                     *                  </c:if>
                     *              </div>
                     *          </c:if>
                     *
                     *          <!-- the configured `LogoutConfigurer#logoutSuccessUrl` is `/login?logout` and contains the query param `logout` -->
                     *          <c:if test="${param.logout != null}">
                     *              <div>
                     *                  You have been logged out.
                     *              </div>
                     *          </c:if>
                     *
                     *          <p>
                     *              <label for="username">Username</label>
                     *              <input type="text" id="username" name="username"/>
                     *          </p>
                     *
                     *          <p>
                     *              <label for="password">Password</label>
                     *              <input type="password" id="password" name="password"/>
                     *          </p>
                     *
                     *          <!-- if using `RememberMeConfigurer` make sure `remember-me` matches `RememberMeConfigurer#rememberMeParameter` -->
                     *          <p>
                     *              <label for="remember-me">Remember Me?</label>
                     *              <input type="checkbox" id="remember-me" name="remember-me"/>
                     *          </p>
                     *
                     *          <div>
                     *              <button type="submit" class="btn">Log in</button>
                     *          </div>
                     *      </fieldset>
                     *
                     *   </form>
                     *
                     * -----------------------------
                     * 2. Impact on other defaults :
                     * -----------------------------
                     *
                     *    Updating this value, also impacts a number of other default values.
                     *    For example, the following are the default values when only `formLogin()` was specified.
                     *
                     *      a. /login GET - the login form
                     *      b. /login POST - process the credentials and if valid authenticate the user
                     *      c. /login?error GET - redirect here for failed authentication attempts
                     *      d. /login?logout GET - redirect here after successfully logging out
                     *      d. If "/authenticate" was passed to this method, it update the defaults as shown below:
                     *          (1) /authenticate GET - the login form
                     *          (2) /authenticate POST - process the credentials and if valid authenticate the user
                     *          (3) /authenticate?error GET - redirect here for failed authentication attempts
                     *          (4) /authenticate?logout GET - redirect here after successfully logging out
                     *
                     */

//                    loginPage("/system/login").

                    /*
                     * ===========================================================
                     * customizer.defaultSuccessUrl(String loginUrl, boolean alwaysUse) method.
                     * ===========================================================
                     * Specifies where users will be redirected after authenticating successfully,
                     * if they have not visited a secured page prior to authenticating or `alwaysUse` is true.
                     *
                     * This is a shortcut for calling `successHandler(AuthenticationSuccessHandler)`.
                     *
                     */
//                    defaultSuccessUrl("/board/getBoardList", true)
//        ); // .formLogin

//        log.info("\t+ Step.4 Done.");
//        log.info("\t\t- loginPage('/system/login'), defaultSuccessUrl('/board/getBoardList', true)");

//        ---

        // ***************************************************************************
        // Step.5 Configuring Exception Handling.
        // ***************************************************************************

        /*
         * ===========================================================
         * @Deprecated(since = "6.1", forRemoval = true)
         * HttpSecurity.exceptionHandling() method.
         * ===========================================================
         * For removal in 7.0.
         *
         * Allows configuring exception handling.
         * This is automatically applied when using `EnableWebSecurity`.
         *
         * Use `exceptionHandling(Customizer)` or `exceptionHandling(Customizer.withDefaults())` to stick with defaults.
         *
         */

        // ------------------------
        // 1st. method - OK
        // ------------------------
//        http.exceptionHandling().accessDeniedPage("/system/AccessDenied");

        // ------------------------
        // 2nd. method - OK
        // ------------------------
//        http.exceptionHandling( customizer -> customizer.accessDeniedPage("/system/accessDenied") );

//        log.info("\t+ Step.5 Done.");
//        log.info("\t\t- accessDeniedPage('/system/accessDenied')");

//        ---

        // ***************************************************************************
        // Step.6 Configuring Logout (Sign-Out) Processing.
        // ***************************************************************************

        /*
         * ===========================================================
         * @Deprecated(since = "6.1", forRemoval = true)
         * HttpSecurity.logout() method.
         * ===========================================================
         * For removal in 7.0.
         *
         * Use `logout(Customizer)` or `logout(Customizer.withDefaults())` to stick with defaults.
         *
         */

        // ------------------------
        // 1st. method - OK without `Remember-Me` Feature
        // ------------------------
//        http.
//          logout().
//              logoutUrl("/system/logout").
//              invalidateHttpSession(true).
//              logoutSuccessUrl("/");

        // ------------------------
        // 2nd. method - OK without `Remember-Me` Feature
        // ------------------------
//        http.logout(
//            customizer ->
//                customizer.
//                    logoutUrl("/system/logout").
//                    invalidateHttpSession(true).
//                    logoutSuccessUrl("/")
//        ); // .logout

        // ------------------------
        // 3rd. method - OK with `Remember-Me` Feature By using Cookies.
        // ------------------------
//        http.
//            logout().
//            logoutUrl("/system/logout").
//            invalidateHttpSession(true).
//            deleteCookies("JSESSIONID").
//            and().
//            // Enable `Remember-Me` Feature Using `JSESSIONID` And 'remember-me' Cookies.
//            rememberMe().
//            // Default `remember-me` Parameter Name.
//            rememberMeParameter("remember-me").
//            // Key : Private Random Secret Key
//            key(UUID.randomUUID().toString()).
//            // `remember-me` Cookie Validity Seconds
//            tokenValiditySeconds(60 * 60 * 24 * 7); // During 1 week,

//        log.info("\t+ Step.6 Done.");
//        log.info("\t\t- logoutUrl('/system/logout'), invalidateHttpSession(true), logoutSuccessUrl('/')");

//        ---

        // ***************************************************************************
        // Step.7 Build A Object or null If The Implementation Allows It.
        // ***************************************************************************

        /*
         * ===========================================================
         * HttpSecurity.build() method.
         * ===========================================================
         * From interface: `org.springframework.security.config.annotation.SecurityBuilder` Builds the object
         * and returns it or null.
         *
         * the Object to be built or null if the implementation allows it.
         */

//        return http.build();
//    } // securityFilterChain

/*
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.trace("passwordEncoder() invoked.");
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    } // passwordEncoder
*/

    /*
     * Automatically called back in the process of (re-)booting Spring Boot Application.
     *
     * (Caution) This bean must not be used with `securityFilterChain(HttpSecurity)` method simultaneously. (***)
     * Performs the customizations on `WebSecurity`.
     * @param `web` the instance of `WebSecurity` to apply to customizations to
     */

/*
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
       log.trace("webSecurityCustomizer() invoked.");

       // `void customize(WebSecurity web)` declared in the functional interface 'webSecurityCustomizer'.
       return web -> web.ignoring().antMatchers("/resources/**");
    } // webSecurityCustomizer
*/

} // end class
