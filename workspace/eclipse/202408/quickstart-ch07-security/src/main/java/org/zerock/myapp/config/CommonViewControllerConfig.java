package org.zerock.myapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zerock.myapp.common.CommonBeanCallbacks;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Configuration("CommonViewControllerConfig")
public class CommonViewControllerConfig
	extends CommonBeanCallbacks implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		log.trace("addViewControllers({}) invoked.", registry);
		
		// (1) Request Mapping: "/security/login" -> "security/login" 
		registry.addViewController("/security/login")
					 .setStatusCode(HttpStatusCode.valueOf(200))
					 .setViewName("security/login");
	} // addViewControllers

} // end class
