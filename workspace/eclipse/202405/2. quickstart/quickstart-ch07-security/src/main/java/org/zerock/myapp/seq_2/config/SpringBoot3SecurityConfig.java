package org.zerock.myapp.seq_2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@EnableWebSecurity								// Security Debugging Is Disabled (debug = false (Default)) 
//@EnableWebSecurity(debug = true)		// Security debugging Is Enabled.

//@Configuration(proxyBeanMethods = false)
public class SpringBoot3SecurityConfig {
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		log.trace("webSecurityCustomizer() invoked.");
		
//		return web -> {};																																		// 1. Forbidden Any Request.
//		return web -> web.debug(true);																												// 2. Forbidden Any Request With Debugging Mode.
//		return web -> web.ignoring().anyRequest();																							// 3. Allowed Any Request.
//		return web -> web.ignoring().requestMatchers("/", "/hello", "/*.jpg", "/imgs/**");						// 4. Allowed Only Matched URIs.
//		return web -> web.ignoring().requestMatchers(HttpMethod.POST).and()											// 5. In conjunction with `and()`.
//									   .ignoring().requestMatchers("/index.html", "/hello", "/*.jpg", "/imgs/**");
//		return web -> web.debug(true).ignoring().requestMatchers("/login");
		
		// At lease, allowing access to the `/error`.
		return web -> web.debug(false).ignoring().requestMatchers("/error", "/api/mappings");
	} // webSecurityCustomizer
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		log.trace("securityFilterChain({}) invoked.", http);
		
		/**
		 * Incorrect (***) :
		 
			(1) http.authorizeHttpRequests();																																// Deprecated.
			(2) http.authorizeRequests();																																		// Deprecated.
			(3) http.authorizeRequests(Customizer authorizeRequestsCustomizer);																// Deprecated.
		 */
		
		// ----------------------------------
		// Case 1	- *Only return http.build();				
		// ----------------------------------					
		// Permitted Anyone For All Any Requests Without Logged In.

		// ----------------------------------
		// Case 2 - *Only the following code.
		// ----------------------------------
		// Ditto. for Case 1
/*
		http.formLogin(Customizer.withDefaults());
*/

		// ----------------------------------
		// Case 3	- Permitted Anyone Only For The Request URIs ( "/ ", "/index.html") If Logged In.
		// ----------------------------------
/*
		http
			.authorizeHttpRequests(customizer -> customizer.requestMatchers("/", "/index.html").permitAll())
			.formLogin(Customizer.withDefaults());
*/

		// ----------------------------------
		// Case 4 - Permitted Anyone For All Request URIs (/**) If Logged In.
		// ----------------------------------
/*
		http
			.authorizeHttpRequests(customizer -> customizer.requestMatchers("/**").permitAll())
			.formLogin(Customizer.withDefaults());
*/		

		// ----------------------------------
		// Case 5 - Permitted Any Authenticated Users For All Request URIs (/**) If Logged In.
		// ----------------------------------
/*
		http
			.authorizeHttpRequests(customizer -> customizer.requestMatchers("/**").authenticated())
			.formLogin(Customizer.withDefaults());
*/		

		// ----------------------------------
		// Case 6 - Permitted Any Authenicated + Only Having "ROLE_USER" Role Users For All Request URIs (/**) If Logged In.
		// ----------------------------------
/*
		http
			.authorizeHttpRequests(customizer -> customizer.requestMatchers("/**").hasRole("USER"))
			.formLogin(Customizer.withDefaults());
*/

		// ----------------------------------
		// Case 7 - Permitted Any Authenicated + Only Having "ROLE_ADMIN" Role Users For All Request URIs (/**) If Logged In.
		// ----------------------------------
/*
		http
			.authorizeHttpRequests(customizer -> customizer.requestMatchers("/**").hasAuthority("ROLE_ADMIN"))
			.formLogin(Customizer.withDefaults());
*/

		// ----------------------------------
		// Case 8 - Permitted Any Authenicated + Having "ROLE_ADMIN" or "ROLE_USER" Role Users For All Request URIs (/**) If Logged In.
		// ----------------------------------
/*
		http
			.authorizeHttpRequests(customizer -> customizer.requestMatchers("/**").hasAnyRole("USER", "ADMIN"))
//			.authorizeHttpRequests(customizer -> customizer.requestMatchers("/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN"))
			.formLogin(Customizer.withDefaults());
*/

		// ----------------------------------
		// Case 9 - 	Permitted Only "ROLE_USER" User For The Request URI ("/2.jpg") &
		//			    Permitted Only "ROLE_ADMIN" User For The Request URI ("/3.jpg", "/4.jpg")
		//				If Logged In.
		// ----------------------------------
/*
		http
			.authorizeHttpRequests(customizer -> 
					customizer
							// If The Following All Codes Are Commentted Out, All Roles Are Unioned All.	(***)
							// 	Ex1) /2.jpg				-> Permitted for role "ROLE_USER" + "ROLE_ADMIN"
							// 	Ex2)	/3.jpg, /4.jpg 	-> Permitted for role "ROLE_USER" + "ROLE_ADMIN"
							.requestMatchers("/2.jpg").hasRole("USER")
							.requestMatchers("/3.jpg", "/4.jpg").hasAuthority("ROLE_ADMIN")
							
							.requestMatchers("/2.jpg").hasAnyRole("ADMIN")
							.requestMatchers("/3.jpg", "/4.jpg").hasAnyAuthority("ROLE_USER")
			).formLogin(Customizer.withDefaults());
*/

		// ----------------------------------
		// Case 10 - 	Permitted Only "ROLE_USER" User For The Request URI ("/2.jpg") &
		//			    	Permitted Only "ROLE_ADMIN" User For The Request URI ("/3.jpg", "/4.jpg")
		//					Without Logged In.
		//				- **Result: Cannot Access Any Request URIs ("/2.jpg, /3.jpg, /4.jpg")		(***)
		// ----------------------------------
/*
		http
			.authorizeHttpRequests(customizer -> 
					customizer
							.requestMatchers("/2.jpg").hasRole("USER")
							.requestMatchers("/3.jpg", "/4.jpg").hasRole("ADMIN")
			);
*/		

		// ----------------------------------
		// Case 11 - To use RESTful Service, Disable CSRF.
		// ----------------------------------
/*
		http.csrf(customizer -> customizer.disable());
*/

		// ----------------------------------
		// Case 12 - When logged out, processing a series of logout tasks.
		// ----------------------------------
		//	(1) Delete All Named Cookies
		//	(2) Invalidate Http Session
		//	(3) The URL to process logout
		//	(4) After success of logout, the URL to be redirected
		//	(5) Clear Authentication
		// ----------------------------------
/*
		http
			.authorizeHttpRequests(customizer -> customizer.requestMatchers("/**").authenticated())
			.formLogin(Customizer.withDefaults())
			.logout(customizer -> 
								customizer
										.deleteCookies("JSESSIONID")					// (1)
										.invalidateHttpSession(true)						// (2)
										.logoutUrl("/security/logout")					// (3)
										.logoutSuccessUrl("/index.html")				// (4)
										.clearAuthentication(true));						// (5)
*/

		// ----------------------------------
		// Case 13 - Log-in & Log-out Customization
		// ----------------------------------
/*
		http.formLogin(customizer -> 
				customizer
//						.loginPage("/security/login")						// User-defined Login URI
						.loginProcessingUrl("/security/login")			// User-defined Login Processing URI
						.defaultSuccessUrl("/security/root", true));
		
		http.csrf(customizer -> customizer.disable());
		
		http.logout(
				customizer -> 
						customizer
								.clearAuthentication(true)
								.invalidateHttpSession(true)
								.deleteCookies("JSESSIONID")
								
								.logoutUrl("/logout")								// Spring Security Default URI ( /logout )
//								.logoutUrl("/security/logout")					// User-defined Logout URI
								
								.logoutSuccessUrl("/security/root"));
*/
		
		// -------------------
		// Case 14 - Set 403 Forbidden (Access Denied) Page		-	1
		// -------------------
		// Important: Priority - accessDeniedHandler > accessDeniedPage
		
/*
		http.exceptionHandling(customizer -> customizer.accessDeniedPage("/security/accessDenied"));
*/
		
		// -------------------
		// Case 15 - Set 403 Forbidden, Access Denied Handler	-	2
		// -------------------
		// Important: Priority - accessDeniedHandler > accessDeniedPage
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
		
		
		return http.build();		// returns `DefaultSecurityFilterChain`
	} // securityFilterChain

	
/**
	-------------------------
	Create In-Memory Users
	-------------------------
	(*) If the following (1) + (2) used together : 	(2) AuthenticationProvider > (1) UserDetailsService
		- (1) + (2) all called back automatically.
		- "Global AuthenticationManager" configured with an (2) "AuthenticationProvider" bean.		(***)
		- (1) "UserDetailsService" beans will *Not be used(= applied) for username/password login.	(***)
		- Consider removing the (2) "AuthenticationProvider" bean. 														(***)
		- Alternatively, consider using the (1) "UserDetailsService"
		   in a manually instantiated "DaoAuthenticationProvider".														(***)
*/
	
// (1) Using "UserDetailsService" Bean
	
	@Bean
	UserDetailsService userDetailsService() {
		log.trace("userDetailsService() invoked.");
		
		String bcryptedPassword = "{bcrypt}$2a$10$Ha4RkwRhqw0O/yIWLccTbeDiFQMp2CV51WwbwauymiJRV9I2yEUO2";	// 1234
		
		UserDetails user = User.withUsername("user")
						// 1st. method - Already encrypted password with BCryptPasswordEncoder
						.password(bcryptedPassword)
						
						// Short-cut of .authorities("ROLE_USER")
						.roles("USER")

						.disabled(false)							// Default (false)
						.accountLocked(false)				// Default (false)
						.accountExpired(false)				// Default (false)
						.credentialsExpired(false)			// Default (false)
						
						.build();
		
		UserDetails admin = User.withUsername("admin")
						// 2nd. method - rawPassword and BCryptPasswordEncoder
						.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
						.password("5678")	
						
						// Full of .roles("USER", "ADMIN")
//						.authorities("ROLE_USER", "ROLE_ADMIN")
						.authorities("ROLE_ADMIN")
						
						.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	} // userDetailsService

	
// (2) Using "AuthenticationManagerBuilder" Bean

	@Autowired
	void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		log.trace("*authenticate({}) invoked.", auth);
		
		PasswordEncoder bCryptPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		auth.inMemoryAuthentication()
				.passwordEncoder(bCryptPasswordEncoder)
				.withUser("user2")
//				.password("{bcrypt}$2a$10$Ha4RkwRhqw0O/yIWLccTbeDiFQMp2CV51WwbwauymiJRV9I2yEUO2")		// 1234
//				.password("5678")					// Encrypted Password
				.password("{noop}5678")		// No Encrypted Password
				.roles("USER");
		
		auth.inMemoryAuthentication()
				.passwordEncoder(bCryptPasswordEncoder)
				.withUser("admin2")
//				.password("{bcrypt}$2a$10$Ha4RkwRhqw0O/yIWLccTbeDiFQMp2CV51WwbwauymiJRV9I2yEUO2")		// 1234
//				.password("5678")					// Encrypted Password
				.password("{noop}5678")		// No Encrypted Password
				.authorities("ROLE_USER", "ROLE_ADMIN");
	} // authenticate

} // end class
