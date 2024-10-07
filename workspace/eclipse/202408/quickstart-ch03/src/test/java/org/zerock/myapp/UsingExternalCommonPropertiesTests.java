package org.zerock.myapp;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.zerock.myapp.controller.BoardController;
import org.zerock.myapp.domain.BoardVOWithRecordType;
import org.zerock.myapp.properties.ApplicationProperties;
import org.zerock.myapp.properties.SpringBootProperties;

import jakarta.annotation.Resource;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

/**
 * ----------------------------------------------
 * 1. @EnableConfigurationProperties
 * ----------------------------------------------
 * Convenient way to quickly register `@ConfigurationProperties` annotated beans with Spring.
 * Standard Spring Beans will also be scanned regardless of this value.
 * 
 * 	Returns: `@ConfigurationProperties` annotated beans to register.
 */

//--------------------
//	OK: Be GOOD under (1) @SpringBootTest (2) @WebMvcTest (3) @AutoConfigureMockMvc & @SpringBootTest
//--------------------
//@EnableConfigurationProperties({ ApplicationProperties.class, SpringProperties.class } )											// OK

/**
 * ----------------------------------------------
 * 2. @ConfigurationPropertiesScan
 * ----------------------------------------------
 * Configures the base packages used when scanning for `@ConfigurationProperties` classes. 
 * One of basePackageClasses(), basePackages() or its alias value() may be specified to define specific packages to scan.
 * If specific packages are not defined, scanning will occur from the package of the class with this annotation. 
 * 
 * Note: Classes annotated or meta-annotated with `@Component` will NOT be picked up by this annotation.
 */

//--------------------
//XX: Be NO good under (1) @WebMvcTest, 
//OK: but be GOOD under (2) @SpringBootTest (3) @AutoConfigureMockMvc & @SpringBootTest. 
//--------------------
@ConfigurationPropertiesScan(basePackageClasses = { ApplicationProperties.class, SpringBootProperties.class })			// OK

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest(
	classes= {			// The declared classes created as bean object and registed into Spring Context when testing.
		BoardController.class,
		BoardVOWithRecordType.class
	},
	properties = {		// The declared properties will update legacy properties or append new properties ONLY in memory.
								// This declared properties CANNOT cause `application.properties` to be changed.
			
		"user-defined.name=PYRAMIDE",		// Update external properties defined 	in `application.properties`.
		"user-defined.age=22",						// Update external properties defined 	in `application.properties`.
		"user-defined.NEW=true"					// Append new external property 	   		in `application.properties`.
	}
)
public class UsingExternalCommonPropertiesTests {
	@Autowired private Environment env;
	
	@Autowired
	private BoardController controller;
	@Resource
	private BoardVOWithRecordType vo;
	
	@Autowired
	private ApplicationProperties appProperties;
	@Autowired
	private SpringBootProperties springProperties;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
//		assertNotNull(this.env);						// 1st. method
//		assert this.env != null;							// 2nd. method
		Objects.requireNonNull(this.env);			// 3rd. method
		log.info("\t+ this.env: {}", this.env);	
	} // beforeAll
	
	@BeforeEach
	void beforeEach() {
		log.trace("beforeEach() invoked.");
		
		log.info(this.controller);
		log.info(this.vo);
		log.info(this.appProperties);
		log.info(this.springProperties);
	} // beforeEach
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(2)
	@DisplayName("1. testGetWebApplicationAndSystemPrpertiesWithEnvironmentBean")
	@Timeout(1L)
	void testGetWebApplicationAndSystemPrpertiesWithEnvironmentBean() {
		log.trace("testGetWebApplicationAndSystemPrpertiesWithEnvironmentBean() invoked.");
		
		// Note: In env.getProperty(key), The key is case-sensitive. (***)
		
		log.info(env.getProperty("server.ssl.key-store"));				// 1. Spring Boot properties declared in application.properties
		log.info(env.getProperty("user-defined.name"));				// 2. User-defined properties defined in application.properties
		log.info(env.getProperty("user-defined.age"));					// 2. User-defined properties defined in application.properties
		log.info(env.getProperty("java.vendor.version"));				// 3. System properties
		log.info(env.getProperty("JAVA_HOME"));						// 4. System environment variables
		log.info(env.getProperty("Path"));
	} // testGetWebApplicationAndSystemPrpertiesWithEnvironmentBean
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(2)
	@DisplayName("2. testOverrideApplicationPropertiesWithSpringBootTestAnnotation")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testOverrideApplicationPropertiesWithSpringBootTestAnnotation() {
		log.trace("testOverrideApplicationPropertiesWithSpringBootTestAnnotation() invoked.");

		// Note: In env.getProperty(key), The key is case-sensitive. (***)
		
		log.info(env.getProperty("user-defined.name"));				// Updated external property defined in application.properties.
		log.info(env.getProperty("user-defined.age"));					// Updated external property defined in application.properties.
		log.info(env.getProperty("user-defined.NEW"));				// Defined new external property into application.properties.
	} // testOverrideApplicationPropertiesWithSpringBootTestAnnotation
	
} // end class



