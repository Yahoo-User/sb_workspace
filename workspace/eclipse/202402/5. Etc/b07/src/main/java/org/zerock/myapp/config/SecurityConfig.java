package org.zerock.myapp.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.debug("configure({}) invoked.", http);

//	-----------------------------------
//	0. ACL Configuration
//	-----------------------------------
//		
//		(1) Access Control configuration method #1
//		-----------------------------------
//		http.authorizeHttpRequests().antMatchers("/").permitAll();
//		http.authorizeHttpRequests().antMatchers("/member/**").authenticated();
//		http.authorizeHttpRequests().antMatchers("/manager/**").authenticated();
//		http.authorizeHttpRequests().antMatchers("/admin/**").authenticated();
//
//		(2) Access Control configuration method #2
//		-----------------------------------
//		http.authorizeHttpRequests().antMatchers("/").permitAll();
//		http.authorizeHttpRequests().antMatchers("/member/**", "/manager/**", "/admin/**").authenticated();
//
//		(3) Access Control configuration method #3
//		-----------------------------------
//		http.authorizeHttpRequests().antMatchers("/").permitAll();
//		http.authorizeHttpRequests().antMatchers("/member/**").authenticated();
//		http.authorizeHttpRequests().antMatchers("/manager/**").hasRole("MANAGER");
//		http.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN");
//
//		(4) Access Control configuration method #4
//		-----------------------------------
		http.authorizeHttpRequests().
		
			// 1. permitAll: Specify that URLs are allowed by anyone.
			antMatchers("/").permitAll().
			
			// 2. authenticated : Specify that URLs are allowed by any authenticated user.
			antMatchers("/member/**", "/logout").authenticated().
			
			// 3. hasRole: the role that should be required which is `prepended` with `ROLE_` automatically.
			//             (i.e. USER, ADMIN, etc)
			//             It should `NOT` start with `ROLE_`
			antMatchers("/manager/**").hasRole("MANAGER").
			antMatchers("/admin/**").hasRole("ADMIN");

//	-----------------------------------
//	1. Specify `Login` Page.
//	-----------------------------------
//		 If authentication required & so far NOT authenticated, 
//		 Move to the below specified login page.

//		-----------------------------------
//		 (1) Method 1: `Custom` Login Page specified. (***)
//		-----------------------------------
//		http.formLogin().loginPage("/login");
		
		http.
			formLogin().
			loginPage("/login").
//			defaultSuccessUrl("/loginSuccess", true);					// Always move to the `/loginSuccess` 	(***)
			defaultSuccessUrl("/loginSuccess", false);					// Move to the `original request URL` 	(***)

//		-----------------------------------
//		 (2) Method 2: `Spring Security` Login Page specified.
//		-----------------------------------
//		http.formLogin();
//		http.formLogin().defaultSuccessUrl("/loginSuccess", true);		// Always move to the `/loginSuccess` 	(***)
//		http.formLogin().defaultSuccessUrl("/loginSuccess", false);		// Move to the `original request URL` 	(***)

//	-----------------------------------
//	2. Specify Spring Security `Logout` Page.
//	-----------------------------------
		
//		(1) The URL that triggers `log out` to occur (default is `/logout`). (***)
//		-----------------------------------
//		a. If `CSRF protection` is `enabled (default)`, then the request must also be a `POST`.
//		   This means that `by default POST "/logout"` is required to trigger a log out.
//		b. If `CSRF protection` is `disabled`, then `ANY` HTTP method is `allowed`. 
//		-----------------------------------
		http.
			logout().
			invalidateHttpSession(true).
			logoutUrl("/logout");
		
//		(2) Explicitly invalidate session and delete all specified cookies. (***)
//		-----------------------------------
//		http.
//			logout().
//			invalidateHttpSession(true).
////			deleteCookies(String...cookieNames).
//			logoutSuccessUrl("/login");

//	----------------------------------
//	3. Disable Spring Security `CSRF Protection`.
//	-----------------------------------
		http.csrf().disable();	// CSRF protection disabled.

//	-----------------------------------
//	4. Specify `Access Denied` Page.
//	-----------------------------------
		http.
			exceptionHandling().
			accessDeniedPage("/accessDenied");
	} // configure
	
	
	// ---------------------------
	// Autowired Methods:
	// ---------------------------
	// 	Config methods may have an arbitrary name and any number of arguments; (***)
	//		- each of those arguments will be autowired with a matching bean in the Spring container. 
	//		- Bean property setter methods are effectively just a special case of such a general config method.
	//		- Such config methods do not have to be public. 
	// ---------------------------
	
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		log.debug("authenticate({}) invoked.", auth);
		
//	=============================================================================
//	1. Authentication Configuration based on Spring In-memory.
//	=============================================================================
		
		// *NOTE* : If `roles` NOT invoked, the below exception raised. (***)
		// java.lang.IllegalArgumentException: Cannot pass a null GrantedAuthority collection.
		
//		auth.
//			inMemoryAuthentication().		// InMemoryUserDetailsManagerConfigurer
//			withUser("member").				// UserDetailsBuilder
//			password("{noop}member").		// UserDetailsBuilder
			
			// ----------------------
			// Populates the roles.
			// ----------------------
			// This method is a shortcut for calling authorities(String), but `automatically` prefixes each entry with `ROLE_`.
			// This means the following: builder.roles("USER","ADMIN"); is equivalent to builder.authorities("ROLE_USER","ROLE_ADMIN");  
			// This attribute is required, but can also be populated with `authorities(String)`.
			// ----------------------
			// Parameters: 
			//	- roles the roles for this user (i.e. USER, ADMIN, etc).
			//  - Cannot be null, contain null values or start with "ROLE_".
//			roles("MEMBER");				// UserDetailsBuilder, `Empty` Roles defined -> `ANY` can be allowed.
		
//		----------------------
		
//		auth.
//			inMemoryAuthentication().		// InMemoryUserDetailsManagerConfigurer
//			withUser("manager").			// UserDetailsBuilder
//			password("{noop}manager").		// UserDetailsBuilder
//			roles("MANAGER");				// UserDetailsBuilder
		
//		----------------------
		
//		auth.
//			inMemoryAuthentication().		// InMemoryUserDetailsManagerConfigurer
//			withUser("admin").				// UserDetailsBuilder
//			password("{noop}admin").		// UserDetailsBuilder
//			roles("ADMIN");					// UserDetailsBuilder
		
//	=============================================================================
//	2. Authentication Configuration by using JDBC based on Oracle Cloud Database.
//	=============================================================================
		
		String query1 = "select id as username, concat('{noop}', password) as password, enabled from member where id = ?";
		String query2 = "select id, role from member where id = ?";
		
//		-----------------
		
		Objects.requireNonNull(this.dataSource);
		log.info("\t+ dataSource: {}, type: {}", this.dataSource, this.dataSource.getClass().getName());
		
		auth.
			jdbcAuthentication().
			dataSource(this.dataSource).
			usersByUsernameQuery(query1).
			authoritiesByUsernameQuery(query2);
	} // authenticate

} // end class
