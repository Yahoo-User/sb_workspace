package org.zerock.myapp.config;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.zerock.myapp.domain.Role;
import org.zerock.myapp.oauth2.CustomOAuth2UserService;


@Log4j2
@AllArgsConstructor

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfigUsingBeanSecurityFilterChain {
    private CustomOAuth2UserService customOAuth2UserService;


    /*
     * =======================================================
     * public final class HttpSecurity
     *      extends AbstractConfiguredSecurityBuilder<DefaultSecurityFilterChain, HttpSecurity>
     *      implements SecurityBuilder<DefaultSecurityFilterChain>, HttpSecurityBuilder<HttpSecurity>
     * =======================================================
     * A `HttpSecurity` is similar to Spring Security's XML <http> element in the namespace configuration.
     * It allows configuring web based security for specific http requests.
     *
     * By default it will be applied to all requests,
     * but can be restricted using requestMatcher(`RequestMatcher`) or other similar methods.
     *
     * Example Usage:
     *
     * The most basic form based configuration can be seen below.
     * The configuration will require that any URL that is requested will require a User with the role "ROLE_USER".
     *
     * It also defines an in memory authentication scheme
     * with a user that has the username "user", the password "password", and the role "ROLE_USER".
     *
     * For additional examples, refer to the Java Doc of individual methods on `HttpSecurity`.
     *
     *   @Configuration
     *   @EnableWebSecurity
     *   public class FormLoginSecurityConfig {
     *
     *      @Bean
     *      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     *   		http.authorizeRequests().
     *   		    antMatchers("/**").
     *   		        hasRole("USER").
     *   		    and().
     *   		        formLogin();
     *
     *   		return http.build();
     *      } // securityFilterChain
     *
     *      @Bean
     *      public UserDetailsService userDetailsService() {
     *   		UserDetails user =
     *   		    User.
     *   		        withDefaultPasswordEncoder().
     *   			        username("user").
     *   			        password("password").
     *   			        roles("USER").
     *   			        build();
     *
     *   		return new InMemoryUserDetailsManager(user);
     *      } // userDetailsService
     *
     *   } // end class
     *
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {{
        log.trace("securityFilterChain({}) invoked.", http);

        /*
         * =======================================================
         * public interface SecurityFilterChain
         * =======================================================
         * Defines a filter chain which is capable of being matched against an `HttpServletRequest`.
         * in order to decide whether it applies to that request.
         *
         * Used to configure a `FilterChainProxy`.
         *
         */

        /*
         * =======================================================
         * public RememberMeConfigurer<HttpSecurity>
         *      HttpSecurity.rememberMe() throws Exception
         * =======================================================
         * Allows configuring of `Remember Me` authentication.
         *
         * Example Configuration :
         *
         *  The following configuration demonstrates how to allow token based `remember me` authentication.
         *  Upon authenticating if the HTTP parameter named "remember-me" exists,
         *  then the user will be remembered even after their `javax.servlet.http.HttpSession` expires.
         *
         *   @Configuration
         *   @EnableWebSecurity
         *   public class RememberMeSecurityConfig {
         *
         *      @Bean
         *      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         *   		http.
         *   	   	    authorizeRequests().
         *   	   	        antMatchers("/**").hasRole("USER").
         *   	   	    and().
         *   	   	        formLogin()
         *   				.permitAll().
         *   			and()
         *   				// Example `Remember Me` Configuration
         *   				.rememberMe();
         *
         *   		return http.build();
         *      } // securityFilterChain
         *
         *      @Bean
         *      public UserDetailsService userDetailsService() {
         *   		UserDetails user =
         *   		    User.
         *   		        withDefaultPasswordEncoder().
         *   			        username("user").
         *   			        password("password").
         *   			        roles("USER").
         *   			        build();
         *
         *   		return new InMemoryUserDetailsManager(user);
         *      } // userDetailsService
         *
         *   } // end class
         *
         * Returns:
         *      the `RememberMeConfigurer` for further customizations
         * Throws:
         *      `Exception`
         *
         */
//        http.rememberMe().disable();

        /*
         * =======================================================
         * public HttpSecurity
         *      HttpSecurity.rememberMe(Customizer<RememberMeConfigurer<HttpSecurity>> rememberMeCustomizer)
         *      throws Exception
         * =======================================================
         * Allows configuring of `Remember Me` authentication.
         *
         * Example Configuration :
         *
         *  The following configuration demonstrates how to allow token based `remember me` authentication.
         *  Upon authenticating if the HTTP parameter named "remember-me" exists,
         *  then the user will be remembered even after their `javax.servlet.http.HttpSession` expires.
         *
         *   @Configuration
         *   @EnableWebSecurity
         *   public class RememberMeSecurityConfig {
         *
         *      @Bean
         *      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         *   		http
         *   			.authorizeRequests((authorizeRequests) ->
         *   				authorizeRequests
         *   					.antMatchers("/**").hasRole("USER")
         *   			)
         *   			.formLogin( withDefaults() )
         *   			.rememberMe( withDefaults() );
         *
         *   		return http.build();
         *      } // securityFilterChain
         *
         *      @Bean
         *      public UserDetailsService userDetailsService() {
         *   		UserDetails user = User.withDefaultPasswordEncoder()
         *   			.username("user")
         *   			.password("password")
         *   			.roles("USER")
         *   			.build();
         *
         *   		return new InMemoryUserDetailsManager(user);
         *      } // userDetailsService
         *
         *   } // end class
         *
         *
         * Params:
         *      rememberMeCustomizer – the `Customizer` to provide more options for the `RememberMeConfigurer`
         * Returns:
         *      the `HttpSecurity` for further customizations
         * Throws:
         *      `Exception`
         *
         */
//        http.rememberMe(rememberMeCustomizer -> rememberMeCustomizer.disable());

        http.

            // ======================================================= //
            // (1) For using H2 console.
            // ======================================================= //

            /*
             * =======================================================
             * public CsrfConfigurer<HttpSecurity>
             *     HttpSecurity.csrf() throws Exception
             * =======================================================
             * Enables CSRF protection.
             * This is activated by default when using `EnableWebSecurity`'s default constructor.
             *
             * Returns:
             *      the `CsrfConfigurer` for further customizations
             * Throws:
             *      `Exception`
             */
            csrf().

                /*
                 * =======================================================
                 * public HttpSecurity AbstractHttpConfigurer.disable()
                 * =======================================================
                 * Disables the `AbstractHttpConfigurer` by removing it.
                 * After doing so a fresh version of the configuration can be applied.
                 *
                 * Returns:
                 *      the `HttpSecurityBuilder` for additional customizations
                 */
                disable().

            /*
             * =======================================================
             * public HeadersConfigurer<HttpSecurity>
             *     HttpSecurity.headers() throws Exception
             * =======================================================
             * Adds the Security headers to the response.
             * This is activated by default when using `EnableWebSecurity`.
             *
             * Accepting the default provided by `EnableWebSecurity` or
             * only invoking `headers()` without invoking additional methods on it.
             *
             * Returns:
             *      the `HeadersConfigurer` for further customizations
             * Throws:
             *      `Exception`
             */
            headers().

                /*
                 * =======================================================
                 * public HeadersConfigurer.FrameOptionsConfig
                 *      HeadersConfigurer.frameOptions()
                 * =======================================================
                 * Allows customizing the `XFrameOptionsHeaderWriter`.
                 *
                 * Returns:
                 *      the `HeadersConfigurer.FrameOptionsConfig` for additional customizations
                 */
                frameOptions().

                /*
                 * =======================================================
                 * public HeadersConfigurer<HttpSecurity>
                 *     HeadersConfigurer.FrameOptionsConfig.disable()
                 * =======================================================
                 * Prevents the header from being added to the response.
                 *
                 * Returns:
                 *      the `HeadersConfigurer` for additional configuration.
                 */
                disable().


            // ======================================================= //
            // (2) Access control for URL patterns
            // ======================================================= //

            /*
             * =======================================================
             * public HttpSecurity SecurityConfigurerAdapter.and()
             * =======================================================
             * Return the `SecurityBuilder` when done using the `SecurityConfigurer`.
             * This is useful for method chaining.
             *
             * Returns:
             *      the `SecurityBuilder` for further customizations
             */
            and().

                /*
                 * =======================================================
                 * public AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
                 *      HttpSecurity.authorizeHttpRequests() throws Exception
                 * =======================================================
                 * Allows restricting access based upon the `HttpServletRequest`
                 * using `RequestMatcher` implementations (i.e. via URL patterns).
                 *
                 * Example Configurations :
                 *
                 *  The most basic example is to configure all URLs to require the role "ROLE_USER".
                 *
                 *  The configuration below requires authentication to every URL
                 *  and will grant access to both the user "admin" and "user".
                 *
                 *  @Configuration
                 *  @EnableWebSecurity
                 *  public class AuthorizeUrlsSecurityConfig {
                 *
                 *    @Bean
                 *    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                 *   		http
                 *   			.authorizeHttpRequests()
                 *   				.antMatchers("/**").hasRole("USER")
                 *   				.and()
                 *   			.formLogin();
                 *   		return http.build();
                 *    } // securityFilterChain
                 *
                 *    @Bean
                 *    public UserDetailsService userDetailsService() {
                 *   		UserDetails user =
                 *   	   	    User.
                 *   	   	        withDefaultPasswordEncoder().
                 *   			    username("user").
                 *   			    password("password").
                 *   			    roles("USER").
                 *   			    build();
                 *
                 *   		UserDetails admin =
                 *   	    	User.
                 *   	       	    withDefaultPasswordEncoder().
                 *   			    username("admin").
                 *   			    password("password").
                 *   			    roles("ADMIN", "USER").
                 *   			    build();
                 *
                 *   		return new InMemoryUserDetailsManager(user, admin);
                 *    } // userDetailsService
                 *
                 *  } // end class
                 *
                 *  We can also configure multiple URLs.
                 *
                 *  The configuration below requires authentication to every URL
                 *  and will grant access to URLs starting with /admin/ to only the "admin" user.
                 *
                 *  All other URLs either user can access.
                 *
                 *  @Configuration
                 *  @EnableWebSecurity
                 *  public class AuthorizeUrlsSecurityConfig {
                 *
                 *    @Bean
                 *    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                 *   		http
                 *   			.authorizeHttpRequests()
                 *   				.antMatchers("/admin").hasRole("ADMIN")
                 *   				.antMatchers("/**").hasRole("USER")
                 *   				.and()
                 *   			.formLogin();
                 *
                 *   		return http.build();
                 *    } // securityFilterChain
                 *
                 *    @Bean
                 *    public UserDetailsService userDetailsService() {
                 *   		UserDetails user =
                 *   	        User.withDefaultPasswordEncoder()
                 *   			    .username("user")
                 *   			    .password("password")
                 *   			    .roles("USER")
                 *   			    .build();
                 *
                 *   		UserDetails admin =
                 *   		    User.withDefaultPasswordEncoder()
                 *   			    .username("admin")
                 *   			    .password("password")
                 *   			    .roles("ADMIN", "USER")
                 *   			    .build();
                 *
                 *   		return new InMemoryUserDetailsManager(user, admin);
                 *    } // userDetailsService
                 *
                 *  } // end class
                 *
                 *  Note that the matchers are considered in order.
                 *
                 *  Therefore, the following is invalid because the first matcher matches every request
                 *  and will never get to the second mapping:
                 *
                 *  @Configuration
                 *  @EnableWebSecurity
                 *  public class AuthorizeUrlsSecurityConfig {
                 *
                 *    @Bean
                 *    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                 *   		http
                 *   			.authorizeHttpRequests()
                 *   				.antMatchers("/**").hasRole("USER")
                 *   				.antMatchers("/admin/**").hasRole("ADMIN")
                 *   				.and()
                 *   			.formLogin();
                 *
                 *   		return http.build();
                 *    } // securityFilterChain
                 *
                 *  } // end class
                 *
                 * Returns:
                 *      the `HttpSecurity` for further customizations
                 * Throws:
                 *      `Exception`
                 */
                authorizeHttpRequests().

                    /*
                     * =======================================================
                     * public AuthorizeHttpRequestsConfigurer.AuthorizedUrl
                     *      AbstractRequestMatcherRegistry.antMatchers(String... antPattern)
                     * =======================================================
                     * Maps a List of `AntPathRequestMatcher` instances that do not care which `HttpMethod` is used.
                     *
                     * Params:
                     *      antPatterns – the ant patterns to create `AntPathRequestMatcher` from
                     * Returns:
                     *      the object that is chained after creating the `RequestMatcher`
                     */
                    antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console").

                        /*
                         * =======================================================
                         * public AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
                         *      AuthorizeHttpRequestsConfigurer.AuthorizedUrl.permitAll()
                         * =======================================================
                         * Specify that `URLs` are allowed by anyone.
                         *
                         * Returns:
                         *      the `AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry`
                         *      for further customizations
                         */
                        permitAll().

                    antMatchers("/api/v1/**").

                        /*
                         * =======================================================
                         * public AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
                         *      AuthorizeHttpRequestsConfigurer.AuthorizedUrl.hasRole(String role)
                         * =======================================================
                         * Specifies a user requires a role.
                         *
                         * Params:
                         *      role – the role that should be required which is prepended with `ROLE_` automatically
                         *              (i.e. USER, ADMIN, etc).
                         *              It should not start with `ROLE_`
                         * Returns:
                         *      `AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry`
                         *      for further customizations
                         */
                        hasRole(Role.USER.name()).

                    /*
                     * =======================================================
                     * public AuthorizeHttpRequestsConfigurer.AuthorizedUrl
                     *      AbstractRequestMatcherRegistry.anyRequest()
                     * =======================================================
                     * Maps any request.
                     *
                     * Returns:
                     *      the object that is chained after creating the `RequestMatcher`
                     */
                    anyRequest().

                        /*
                         * =======================================================
                         *  public AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
                         *      AuthorizeHttpRequestsConfigurer.AuthorizedUrl.authenticated()
                         * =======================================================
                         * Specify that URLs are allowed by any authenticated user.
                         *
                         * Returns:
                         *      the `AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry`
                         *      for further customizations
                         */
                        authenticated().


            // ======================================================= //
            // (3) For processing logout.
            // ======================================================= //

            /*
             * =======================================================
             * public HttpSecurity SecurityConfigurerAdapter.and()
             * =======================================================
             * Return the `SecurityBuilder` when done using the `SecurityConfigurer`.
             * This is useful for method chaining.
             *
             * Returns:
             *      the `SecurityBuilder` for further customizations
             */
            and().

                /*
                 * =======================================================
                 * public LogoutConfigurer<HttpSecurity>
                 *     HttpSecurity.logout() throws Exception
                 * =======================================================
                 * Provides logout support.
                 * This is automatically applied when using `EnableWebSecurity`.
                 *
                 * The default is that accessing the URL "/logout" will log the user out
                 * by invalidating the HTTP Session, cleaning up any `rememberMe()` authentication that was configured,
                 * clearing the `SecurityContextHolder`, and then redirect to "/login?success".
                 *
                 * Example Custom Configuration:
                 *
                 *  The following customization to log out when the URL "/custom-logout" is invoked.
                 *  Log out will remove the cookie named "remove", not invalidate the HttpSession,
                 *  clear the `SecurityContextHolder`, and upon completion redirect to "/logout-success".
                 *
                 *  @Configuration
                 *  @EnableWebSecurity
                 *  public class LogoutSecurityConfig {
                 *
                 *      @Bean
                 *      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                 *          http.
                 *              authorizeRequests().
                 *                  antMatchers("/**").
                 *                  hasRole("USER").
                 *              and().
                 *                  formLogin().
                 *              and().
                 *                  // sample logout customization
                 *                  logout().
                 *                      deleteCookies("remove").
                 *                      invalidateHttpSession(false).
                 *                      logoutUrl("/custom-logout").
                 *                      logoutSuccessUrl("/logout-success");
                 *
                 *          return http.build();
                 *      } // securityFilterChain
                 *
                 *      @Bean
                 *      public UserDetailsService userDetailsService() {
                 *          UserDetails user =
                 *              User.
                 *                  withDefaultPasswordEncoder().
                 *                      username("user").
                 *                      password("password").
                 *                      roles("USER").
                 *                      build();
                 *
                 *          return new InMemoryUserDetailsManager(user);
                 *      } // userDetailsService
                 *
                 *  } // end class
                 *
                 *
                 * Returns:
                 *      the `LogoutConfigurer` for further customizations
                 * Throws:
                 *      `Exception`
                 */
                logout().

                    /*
                     * =======================================================
                     * public LogoutConfigurer<HttpSecurity>
                     *     LogoutConfigurer.deleteCookies(String... cookieNamesToClear)
                     * =======================================================
                     * Allows specifying the names of cookies to be removed on logout success.
                     * This is a shortcut to easily invoke `addLogoutHandler(LogoutHandler)`
                     * with a `CookieClearingLogoutHandler`.
                     *
                     * Params:
                     *      cookieNamesToClear – the names of cookies to be removed on logout success.
                     * Returns:
                     *      the `LogoutConfigurer` for further customization
                     */
//                    deleteCookies("JSESSIONID").

                    /*
                     * =======================================================
                     * public LogoutConfigurer<HttpSecurity>
                     *     LogoutConfigurer.invalidateHttpSession(boolean invalidateHttpSession)
                     * =======================================================
                     * Configures `SecurityContextLogoutHandler` to invalidate the `HttpSession` at the time of logout.
                     *
                     *
                     * Params:
                     *      invalidateHttpSession – true if the `HttpSession` should be invalidated (default),
                     *                              or false otherwise.
                     * Returns:
                     *      the `LogoutConfigurer` for further customization
                     */
//                    invalidateHttpSession(true).

                    /*
                     * =======================================================
                     * public LogoutConfigurer<HttpSecurity>
                     *     LogoutConfigurer.clearAuthentication(boolean clearAuthentication)
                     * =======================================================
                     * Specifies if `SecurityContextLogoutHandler` should clear the `Authentication` at the time of logout.
                     *
                     *
                     * Params:
                     *      clearAuthentication – true `SecurityContextLogoutHandler` should clear the `Authentication` (default),
                     *                            or false otherwise.
                     * Returns:
                     *      the `LogoutConfigurer` for further customization
                     */
//                    clearAuthentication(true).

                    /*
                     * =======================================================
                     * public LogoutConfigurer<HttpSecurity>
                     *     LogoutConfigurer.addLogoutHandler(LogoutHandler logoutHandler)
                     * =======================================================
                     * Adds a LogoutHandler.
                     *
                     * `SecurityContextLogoutHandler` and `LogoutSuccessEventPublishingLogoutHandler` are added
                     * as last `LogoutHandler` instances by default.
                     *
                     * Params:
                     *      logoutHandler – the `LogoutHandler` to add
                     * Returns:
                     *      the `LogoutConfigurer` for further customization
                     */
//                    addLogoutHandler((req, res, auth) -> {
//                        log.info("\t+ LogoutHandler(req, res, auth: {}) invoked.", auth);
//                    }).

                    /*
                     * =======================================================
                     * public LogoutConfigurer<HttpSecurity>
                     *     LogoutConfigurer.logoutSuccessHandler(LogoutSuccessHandler logoutSuccessHandler)
                     * =======================================================
                     * Sets the `LogoutSuccessHandler` to use.
                     * If this is specified, `logoutSuccessUrl(String)` is ignored.
                     *
                     * Params:
                     *      logoutSuccessHandler – the `LogoutSuccessHandler` to use
                     *                             after a user has been logged out.
                     * Returns:
                     *      the `LogoutConfigurer` for further customizations
                     */
//                    logoutSuccessHandler((req, res, auth) -> {
//                        log.info("\t+ logoutSuccessHandler(req, res, auth: {}) invoked.", auth);
//
//                        res.sendRedirect("/");
//                    }).

                    /*
                     * =======================================================
                     * public LogoutConfigurer<HttpSecurity>
                     *     LogoutConfigurer.logoutSuccessUrl(String logoutSuccessUrl)
                     * =======================================================
                     * The URL to redirect to after logout has occurred.
                     * The default is "/login?logout".
                     *
                     * This is a shortcut for invoking `logoutSuccessHandler(LogoutSuccessHandler)`
                     * with a `SimpleUrlLogoutSuccessHandler`.
                     *
                     * Params:
                     *      logoutSuccessUrl – the URL to redirect to after logout occurred
                     * Returns:
                     *      the `LogoutConfigurer` for further customization
                     */
//                    logoutSuccessUrl("/").


            // ======================================================= //
            // (4) For get user attributes after oauth2 login success.
            // ======================================================= //

            /*
             * =======================================================
             * public HttpSecurity SecurityConfigurerAdapter.and()
             * =======================================================
             * Return the `SecurityBuilder` when done using the `SecurityConfigurer`.
             * This is useful for method chaining.
             *
             * Returns:
             *      the `SecurityBuilder` for further customizations
             */
            and().

                /*
                 * =======================================================
                 * public OAuth2LoginConfigurer<HttpSecurity>
                 *     HttpSecurity.oauth2Login() throws Exception
                 * =======================================================
                 * Configures authentication support using an `OAuth 2.0` and/or `OpenID Connect 1.0 Provider`.
                 * The "authentication flow" is implemented using the Authorization Code Grant,
                 * as specified in the `OAuth 2.0 Authorization Framework` and `OpenID Connect Core 1.0 specification`.
                 *
                 * As a prerequisite to using this feature, you must register a client with a provider.
                 * The client registration information may than be used for configuring a
                 * `org.springframework.security.oauth2.client.registration.ClientRegistration`
                 * using a `org.springframework.security.oauth2.client.registration.ClientRegistration.Builder`.
                 *
                 * `org.springframework.security.oauth2.client.registration.ClientRegistration(s)` are composed
                 * within a `org.springframework.security.oauth2.client.registration.ClientRegistrationRepository`,
                 * which is required and must be registered with the `ApplicationContext`
                 * or configured via `oauth2Login().clientRegistrationRepository(..)`.
                 *
                 * The default configuration provides an auto-generated login page at "/login"
                 * and redirects to "/login?error" when an authentication error occurs.
                 *
                 * The login page will display each of the clients with a link that is capable of
                 * initiating the "authentication flow".
                 *
                 * Example Configuration :
                 *
                 *  The following example shows the minimal configuration required,
                 *  using Google as the Authentication Provider.
                 *
                 *   @Configuration
                 *   @EnableWebSecurity
                 *   public class OAuth2LoginSecurityConfig {
                 *
                 *      @Bean
                 *      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                 *   		http
                 *   			.authorizeRequests()
                 *   				.anyRequest().authenticated()
                 *   			.and()
                 *   			    .oauth2Login();
                 *
                 *   		return http.build();
                 *      } // securityFilterChain
                 *
                 *      @Bean
                 *  	public ClientRegistrationRepository clientRegistrationRepository() {
                 *  		return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
                 *  	} // clientRegistrationRepository
                 *
                 *   	private ClientRegistration googleClientRegistration() {
                 *   		return
                 *   	    	ClientRegistration.
                 *   	    	    withRegistrationId("google").
                 *   			        clientId("google-client-id").
                 *   			        clientSecret("google-client-secret").
                 *   			        clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC).
                 *   			        authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE).
                 *   			        redirectUri("{baseUrl}/login/oauth2/code/{registrationId}").
                 *   			        scope("openid", "profile", "email", "address", "phone").
                 *   			        authorizationUri("https://accounts.google.com/o/oauth2/v2/auth").
                 *   			        tokenUri("https://www.googleapis.com/oauth2/v4/token").
                 *   			        userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo").
                 *   			        userNameAttributeName(IdTokenClaimNames.SUB).
                 *   			        jwkSetUri("https://www.googleapis.com/oauth2/v3/certs").
                 *   			        clientName("Google").
                 *   			        build();
                 *  	} // googleClientRegistration
                 *
                 *   } // end class
                 *
                 * For more advanced configuration,
                 * see `OAuth2LoginConfigurer` for available options to customize the defaults.
                 *
                 *
                 * Returns:
                 *      the `OAuth2LoginConfigurer` for further customizations
                 * Throws:
                 *      `Exception`
                 */
                oauth2Login().

                    /*
                     * =======================================================
                     * public OAuth2LoginConfigurer.UserInfoEndpointConfig
                     *      OAuth2LoginConfigurer.userInfoEndpoint()
                     * =======================================================
                     * Returns the `OAuth2LoginConfigurer.UserInfoEndpointConfig`
                     * for configuring the Authorization Server's `UserInfo` Endpoint.
                     *
                     * Returns:
                     *      the `OAuth2LoginConfigurer.UserInfoEndpointConfig`
                     */
                    userInfoEndpoint().

                    /*
                     * =======================================================
                     * public OAuth2LoginConfigurer.UserInfoEndpointConfig
                     *      OAuth2LoginConfigurer.UserInfoEndpointConfig.userService(
                     *          OAuth2UserService<OAuth2UserRequest, OAuth2User> userService
                     *      )
                     * =======================================================
                     * Sets the OAuth 2.0 service used for obtaining the user attributes of
                     * the End-User from the `UserInfo` Endpoint.
                     *
                     * Params:
                     *      userService – the OAuth 2.0 service used for obtaining the user attributes of
                     *                    the End-User from the `UserInfo` Endpoint
                     *
                     * Returns:
                     *      the `OAuth2LoginConfigurer.UserInfoEndpointConfig` for further configuration
                     */
                    userService(this.customOAuth2UserService);


        return http.build();
    }} // securityFilterChain

} // end class
