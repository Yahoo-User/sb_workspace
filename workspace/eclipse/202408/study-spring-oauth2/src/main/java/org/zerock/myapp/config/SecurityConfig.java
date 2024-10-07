package org.zerock.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;

import lombok.NoArgsConstructor;


//@Log4j2
@NoArgsConstructor

@Configuration("securityConfig")
@EnableWebSecurity
public class SecurityConfig {
	
 	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 		http
 			.authorizeHttpRequests((authorizeRequests) ->
 				authorizeRequests.anyRequest().authenticated()
 			)
 			.oauth2Login(Customizer.withDefaults());
 		
 		return http.build();
 	}

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
//		return new InMemoryClientRegistrationRepository(this.githubClientRegistration());	// OK, Github
		return new InMemoryClientRegistrationRepository(this.googleClientRegistration());	// XX, Google
	}
	
	private ClientRegistration githubClientRegistration() {
		return ClientRegistration
						.withRegistrationId("github")
						.clientName("LoginTest")
						.clientId("Ov23li2CJUrLlBCxXCps")
						.clientSecret("d9701bca1b59e6964f09b9f1dc45abd9d7d659ca")
						.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
						.authorizationUri("https://github.com/login/oauth/authorize")
			 			.redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
						.tokenUri("https://github.com/login/oauth/access_token")
						.userInfoUri("https://api.github.com/user")
						.userNameAttributeName("login")
						.scope("user")
						.build();
	}

 	private ClientRegistration googleClientRegistration() {
 		return ClientRegistration
 						.withRegistrationId("google")
	 					.clientName("Yoseph")
			 			.clientId("543020721770-lvf1u0ffn2u9ioctt346oju2r10rg7j2.apps.googleusercontent.com")
			 			.clientSecret("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")
			 			.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
			 			.authorizationUri("https://accounts.google.com/o/oauth2/auth")
			 			.redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
			 			.tokenUri("https://oauth2.googleapis.com/token")
			 			.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
			 			.scope("profile", "email")
//			 			.scope("openid", "profile", "email", "address", "phone")
//						.userNameAttributeName("email")
						.userNameAttributeName("name")
//						.userNameAttributeName("sub")
//						.userNameAttributeName("given_name")
//						.userNameAttributeName("family_name")
//						.userNameAttributeName("picture")
			 			.build();
	}
 	
 } // end class


