package org.zerock.myapp.config;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * 
 * 	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.web.SecurityFilterChain;
	
	@Configuration(proxyBeanMethods = false)
	@EnableWebSecurity
	public class MyOAuthClientConfiguration {
	
	  @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		  http
			  .authorizeHttpRequests( (requests) -> requests.anyRequest().authenticated() )
			  .oauth2Login((login) -> login.redirectionEndpoint((endpoint) -> endpoint.baseUri("/login/oauth2/callback/*") ) );
			  
		  return http.build();
	  } // securityFilterChain
	  
	} // end class

 */


@Log4j2
@NoArgsConstructor

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfig {
//		implements InitializingBean {

//	@Autowired private DataSource dataSource;

	@Autowired
	private BoardUserDetailsService boardUserDetailsService;



//	@Override
//	public void afterPropertiesSet() throws Exception {
//		log.trace("afterPropertiesSet() invoked.");
//
//		Objects.requireNonNull(this.dataSource);
//		log.info("\t+ this.dataSource: {}", this.dataSource);
//	} // afterPropertiesSet


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		log.trace("securityFilterChain({}) invoked.", http);
		
		/* --------------------------
		 * Example1 :
		 * --------------------------
	     *
		 * 	http.authorizeRequests((authorizeRequests) ->
		 * 		authorizeRequests.requestMatchers("/**").hasRole("USER")
		 * 	)
		 * 	.formLogin(
		 * 		(formLogin) -> 
		 * 				formLogin.
		 * 					usernameParameter("username").
		 * 					passwordParameter("password").
		 * 					loginPage("/authentication/login").
		 * 					failureUrl("/authentication/login?failed").
		 * 					loginProcessingUrl("/authentication/login/process")
		 * 	);
		 * 
		 * 	return http.build();
		*/
		
		// Parameter of authorizeHttpRequests(Customizer) :
		//
		//	@FunctionalInterface
		//	interface Customizer<AuthorizationManagerRequestMatcherRegistry> {
		//		void customize(AuthorizationManagerRequestMatcherRegistry registry);
		//	}
		
//		http.authorizeHttpRequests( registry -> registry.requestMatchers("/").permitAll() );					// OK
//		http.authorizeHttpRequests( registry -> registry.requestMatchers("/member").authenticated() );			// OK
//		http.authorizeHttpRequests( registry -> registry.requestMatchers("/manager").hasRole("MANAGER") );		// OK
//		http.authorizeHttpRequests( registry -> registry.requestMatchers("/admin").hasRole("ADMIN") );			// OK
		
		// For User-defined Login Page and Login Success Page.
//		http.authorizeHttpRequests( registry -> registry.requestMatchers("/authLogin").permitAll() );			// OK
//		http.authorizeHttpRequests( registry -> registry.requestMatchers("/authLoginSuccess").authenticated());	// OK
		
		
		http.authorizeHttpRequests(
			registry -> {
				registry.requestMatchers("/").permitAll();
				registry.requestMatchers("/authLogin").permitAll();
		
				registry.requestMatchers("/member").authenticated();
				registry.requestMatchers("/authLoginSuccess").authenticated();
		
//				registry.requestMatchers("/manager").hasRole("MANAGER");
				registry.requestMatchers("/manager").hasAnyRole("MANAGER", "ADMIN");

				registry.requestMatchers("/admin").hasRole("ADMIN");
			}
		);																												// OK

//		-- Example2 : ------------------
//		
//		http.csrf( (csrf) -> csrf.disable() );
//		
//		-----------------------------
		http.csrf( conf -> conf.disable() );
		
		
		/* --------------------------
		 * Example3 :
		 * --------------------------
		 * 
		 * 	http.formLogin(
		 * 		(formLogin) -> 
		 * 				formLogin
		 * 					.usernameParameter("username")
		 * 					.passwordParameter("password")
		 * 					.loginPage("/authentication/login")
		 * 					.failureUrl("/authentication/login?failed")
		 * 					.loginProcessingUrl("/authentication/login/process")
		 * 	);
		 * 
		 */
		
//		http.formLogin( Customizer.withDefaults() );		// OK
		
		http.formLogin( conf -> conf.loginPage("/authLogin").defaultSuccessUrl("/authLoginSuccess", true) );	// OK
		
		
		/* --------------------------
		 * Example4 :
		 * --------------------------
		 * 인증수행 시 오류발생 했을 때, 이동할 페이지 설정(오류페이지 설정)
		 * 
		 *  // sample exception handling customization
		 * 	http.exceptionHandling( (exceptionHandling) -> exceptionHandling.accessDeniedPage("/errors/access-denied") );
		 * 
		 */
		
//		http.exceptionHandling( conf -> conf.accessDeniedPage("/error") );
		
		
		/* --------------------------
		 * Example5 :
		 * --------------------------
		 * 로그아웃
		 * 
		 * 	http.logout(
		 * 		(logout) ->
		 * 				logout.
		 * 					deleteCookies("remove").
		 * 					invalidateHttpSession(false).
		 * 					logoutUrl("/custom-logout").
		 * 					logoutSuccessUrl("/logout-success")
		 * 	);
		 * 
		 */
		
		http.logout( conf -> conf.invalidateHttpSession(true).logoutSuccessUrl("/authLogin") );

//		----


// -----------------------------------------------------------------
//	3. Use `User-defined UserDetailsService` to provide User Details from the Database
// -----------------------------------------------------------------
		http.userDetailsService(this.boardUserDetailsService);

//		---


		return http.build();	// return `DefaultSecurityFilterChain`
	} // securityFilterChain


// -----------------------------------------------------------------
//	1. In-Memory Users Registration without Password Encoding.
// -----------------------------------------------------------------

//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//		log.trace("authenticate({}) invoked.", auth);
//
//		auth.inMemoryAuthentication().withUser("manager").password("{noop}manager123").roles("MANAGER");
//		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin123").roles("MANAGER", "ADMIN");
//	} // authenticate


// -----------------------------------------------------------------
//	2. Using Database.
// -----------------------------------------------------------------

//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//		log.trace("authenticate({}) invoked.", auth);
//
//		String query1 = """
//				SELECT id username, concat('{noop}', password) password, enabled
//				FROM member
//				WHERE id = ?
//				""";
//
//		String query2 = """
//				SELECT id, role
//				FROM member
//				WHERE id = ?
//				""";
//
//		auth.
//			jdbcAuthentication().
//			dataSource(this.dataSource).
//			usersByUsernameQuery(query1).
//			authoritiesByUsernameQuery(query2);
//	} // authenticate


	@Bean
	public PasswordEncoder passwordEncoder() {
		log.trace("passwordEncoder() invoked.");

		return new BCryptPasswordEncoder();
	} // passwordEncoder

} // end class
