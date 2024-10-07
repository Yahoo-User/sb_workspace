package org.zerock.myapp.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.myapp.security.CustomUserDetailsService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor


// ************************************** //
// Must refer to the `SecurityConfig`
// ************************************** //

@EnableWebSecurity
public class SecurityConfigUsingCustomUserDetailsService
	extends WebSecurityConfigurerAdapter implements InitializingBean, DisposableBean {
	
	@Autowired
	private CustomUserDetailsService service;
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.debug("configure({}) invoked.", http);
		
//		----------------------
		
		http.
			authorizeHttpRequests().
			antMatchers("/").permitAll().
			antMatchers("/member/**", "/logout").authenticated().
			antMatchers("/manager/**").hasRole("MANAGER").
			antMatchers("/admin/**").hasRole("ADMIN");
		
//		----------------------
		
		http.
			formLogin().
			loginPage("/login").
			defaultSuccessUrl("/loginSuccess", false);
		
//		----------------------
		
		http.
			logout().
			invalidateHttpSession(true).
			logoutUrl("/logout");
		
//		----------------------
		
		http.
			csrf().
			disable();
		
//		----------------------
		
		http.
			exceptionHandling().
			accessDeniedPage("/accessDenied");
		
//		----------------------
		
		http.userDetailsService(this.service);
	} // configure
	

	// ************************************** //
	// For Spring Boot Security to act based on `BCrypted` password,
	// Required the below method to return a `PasswordEncoder` Beans object.
	// ************************************** //
	@Bean
	public PasswordEncoder passwordEncoder() {
		log.debug("passwordEncoder() invoked.");
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		log.info("\t+ encoder: {}", encoder);
		
		return encoder;
	} // passwordEncoder
	
	
//	============================

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
		
		assert this.service != null;
		log.info("\t+ service: {}, type: {}", this.service, this.service.getClass().getName());
	} // afterPropertiesSet

	@Override
	public void destroy() throws Exception {
		log.debug("destroy() invoked.");

	} // destroy

} // end class
