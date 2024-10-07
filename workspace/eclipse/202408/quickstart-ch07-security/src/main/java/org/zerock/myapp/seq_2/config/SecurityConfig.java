package org.zerock.myapp.seq_2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.zerock.myapp.common.CommonBeanCallbacks;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@EnableWebSecurity(debug = false)
@Configuration(value = "securityConfig", proxyBeanMethods = false)
public class SecurityConfig extends CommonBeanCallbacks {
	@Autowired(required = false) private PasswordEncoder passwordEncoder;			// XX, No Bean
	@Autowired(required = true) private DataSource dataSource;
	
	@Autowired(required = false) @Qualifier("securityUserDetailsService")
	private UserDetailsService securityUserDetailsService;
	
	
	@PostConstruct
	public void postConstruct() {	
		log.trace("(2) {} -> postConstruct() invoked.", this.beanName);
		
		log.info("  + this.passwordEncoder: {}", this.passwordEncoder);
		log.info("  + this.dataSource: {}", this.dataSource);
		log.info("  + this.securityUserDetailsService: {}", this.securityUserDetailsService);
	} // postConstruct
	
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		log.trace("webSecurityCustomizer() invoked.");
		
		// You are asking Spring Security to ignore Mvc [pattern='/api/mappings'].
		// This is *Not recommended -- please use `permitAll` via `HttpSecurity#authorizeHttpRequests` instead.
/*		
		return web -> web.debug(false).ignoring()
										.requestMatchers(
												"/error", "/api/mappings", "/index.html", 
												"/security/login", "/security/logout", 
												"/security/accessDenied", "/security/authenticationFailed",
												"/board/getBoardList");
*/
		
		return web -> web.debug(false);		// Forbidden Any Request With Debugging Mode.
	} // webSecurityCustomizer
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		log.trace("securityFilterChain({}) invoked.", http);

		// -------------------
		// (1) Ignoring The Following Default URIs.
		// -------------------
		
		// 1st. method
/*
		http.authorizeHttpRequests(customizer -> customizer.requestMatchers("/error").permitAll());
		http.authorizeHttpRequests(customizer -> customizer.requestMatchers("/api/mappings").permitAll());
		http.authorizeHttpRequests(customizer -> customizer.requestMatchers("/index.html").permitAll());
		http.authorizeHttpRequests(customizer -> customizer.requestMatchers("/board/getBoardList").permitAll());
		http.authorizeHttpRequests(customizer -> customizer.requestMatchers("/security/login").permitAll());
		http.authorizeHttpRequests(customizer -> customizer.requestMatchers("/security/logout").permitAll());
		http.authorizeHttpRequests(customizer -> customizer.requestMatchers("/security/accessDenied").permitAll());
		http.authorizeHttpRequests(customizer -> customizer.requestMatchers("/security/authenticationFailed").permitAll());
*/

		// 2nd. method
		http.authorizeHttpRequests(customizer ->
					customizer.requestMatchers("/error").permitAll()
									  .requestMatchers("/api/mappings").permitAll()
									  .requestMatchers("/index.html").permitAll()
									  .requestMatchers("/board/getBoardList").permitAll()
									  .requestMatchers("/security/login").permitAll()
									  .requestMatchers("/security/logout").permitAll()
									  .requestMatchers("/security/accessDenied").permitAll()
									  .requestMatchers("/security/authenticationFailed").permitAll()
				);	// .authorizeHttpRequests
		
		
		// -------------------
		// (2) Apply Spring Security To The Request URIs To Service.
		// -------------------
		
		// 1st. method
/*
		http.authorizeHttpRequests(customizer -> customizer.requestMatchers("/security/root").permitAll());
		http.authorizeHttpRequests(customizer -> customizer.requestMatchers("/security/member").authenticated()).formLogin(Customizer.withDefaults());
		http.authorizeHttpRequests(customizer -> customizer.requestMatchers("/security/user").hasAnyRole("USER", "ADMIN")).formLogin(Customizer.withDefaults());
		http.authorizeHttpRequests(customizer -> customizer.requestMatchers("/security/admin").hasAuthority("ROLE_ADMIN")).formLogin(Customizer.withDefaults());
*/

		// 2nd. method
		http.authorizeHttpRequests(customizer ->
					customizer
							.requestMatchers("/security/root").permitAll()
							.requestMatchers("/security/member").fullyAuthenticated()
							.requestMatchers("/security/user").hasAnyRole("USER", "ADMIN")
							.requestMatchers("/security/admin").hasAnyAuthority("ROLE_ADMIN")
				);

		
		// -------------------
		// (3) Log-in & Log-out Customization
		// -------------------
		
		// 3-0. Default Login Configuration
		http.formLogin(Customizer.withDefaults());
		
		// 3-1. Login Customization
/*
		http.formLogin(customizer -> 
				customizer
						.loginPage("/security/login")								// User-defined Login URI
						.loginProcessingUrl("/security/login")				// OK, User-defined Login Processing URI
						.defaultSuccessUrl("/security/root", true)
						.failureUrl("/security/authenticationFailed"));
*/

		// 3-2. Logout Customization
		http.csrf(customizer -> customizer.disable());						// If using RESTful service, CSRF should be disabled.

/*
		http.logout(customizer -> 
				customizer
						.clearAuthentication(true)
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
//						.logoutUrl("/logout")												// Spring Security Default URI ( /logout )
						.logoutUrl("/security/logout")								// User-defined Logout URI
						.logoutSuccessUrl("/security/root"));
*/
		
		// -------------------
		// (4) Set 403 Forbidden (Access Denied) Page
		// -------------------
		// Important: Priority - customizer.accessDeniedHandler() > customizer.accessDeniedPage()
		
		// 1st. method
		http.exceptionHandling(customizer -> customizer.accessDeniedPage("/security/accessDenied"));

		// 2nd. method
/*
		http.exceptionHandling(customizer -> 
				customizer.accessDeniedHandler((req, res, e) -> {
					log.trace("accessDeniedHandler(req, res, {}) invoked.", e);
					
					res.setCharacterEncoding("utf8");
					res.setContentType("text/html; charset=utf8");
					
					@Cleanup PrintWriter out = res.getWriter();
					
					out.println("<h3 style='color: blue;'>403 - Forbidden (%s) </h3>".formatted(req.getRequestURI()));
					out.println("%s<br><hr>".formatted(e));
					
					out.println("<ol style='font-size: 16px; color: red;'>");
					
					for(StackTraceElement stack : e.getStackTrace()) {
						out.println("<li>at " + stack + "</li>");
					} // enhanced for
					
					out.println("</ol>");
					
					out.flush();
				}));	// .exceptionHandling
*/
		
		// -------------------
		// (5) Set Custom UserDetailsService Implementation Bean
		// -------------------
		http.userDetailsService(this.securityUserDetailsService);
		
		
		return http.build();
	} // securityFilterChain
	
	
	@Bean
	UserDetailsService userDetailsService() {
		log.trace("userDetailsService() invoked.");

		// -----------
		PasswordEncoder bCryptPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		// -----------
		UserDetails user = 
				User.withUsername("user")
						.passwordEncoder(bCryptPasswordEncoder::encode)
						.password("12345")
						.roles("USER")
						.accountExpired(false)
						.accountLocked(false)
						.credentialsExpired(false)
						.disabled(false)
						.build();

		// -----------
		UserDetails admin =
				User.withUsername("admin")
						.passwordEncoder(bCryptPasswordEncoder::encode)
						.password("67890")
						.authorities("ROLE_USER", "ROLE_ADMIN")
						.build();
		
		
		return new InMemoryUserDetailsManager(user, admin);
	} // userDetailsService
	
	
	@Autowired
	void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		log.trace("authenticate({}) invoked.", auth);

		// -----------
		PasswordEncoder bCryptPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		// -----------
		// (1) In-Memory Authentication Configuration
		// -----------
		auth.inMemoryAuthentication()
				.passwordEncoder(bCryptPasswordEncoder)
				.withUser("user2")
//				.password("{bcrypt}$2a$10$Ha4RkwRhqw0O/yIWLccTbeDiFQMp2CV51WwbwauymiJRV9I2yEUO2")		// 1234, Encrypted Password
//				.password("5678")					// Encrypted Password
				.password("{noop}5678")		// No Encrypted Password
				.roles("USER");

		// -----------
		auth.inMemoryAuthentication()
				.passwordEncoder(bCryptPasswordEncoder)
				.withUser("admin2")
//				.password("{bcrypt}$2a$10$Ha4RkwRhqw0O/yIWLccTbeDiFQMp2CV51WwbwauymiJRV9I2yEUO2")		// 1234, Encrypted Password
//				.password("5678")					// Encrypted Password
				.password("{noop}5678")		// No Encrypted Password
				.authorities("ROLE_USER", "ROLE_ADMIN");
		
		// -----------
		// (2) JDBC Authentication Configuration
		// -----------
/*
		String usersQuery = "SELECT username, password, enabled FROM users WHERE username = ?";
		String authoritiesQuery = "SELECT username, authority FROM authorities WHERE username = ?";
		
		auth.jdbcAuthentication()
				.dataSource(this.dataSource)
				.passwordEncoder(bCryptPasswordEncoder)
				.rolePrefix("ROLE_")														// Default is "".
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(authoritiesQuery);
*/
		
		// -----------
		// (3) JDBC Authentication Configuration With Custom Access Control Tables
		// -----------
/*
		usersQuery = "SELECT name as username, concat('{noop}', password) as password, true as enabled FROM member WHERE name = ?";
		authoritiesQuery = "SELECT name as username, upper(role) as authority FROM member WHERE name = ?";
		
		auth.jdbcAuthentication()
				.dataSource(this.dataSource)
				
				// Allows specifying the PasswordEncoder to use with the `DaoAuthenticationProvider`.
				// The default is to use Plain Text.		(***)
				
				// Instead, You have entered a password with No PasswordEncoder.
				//			   If that is your intent, it should be prefixed with `{noop}`.		(***)
//				.passwordEncoder(null)
				
				.rolePrefix("ROLE_")														// Default is "".
				
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(authoritiesQuery);
*/
	} // authenticate
	

} // end class

