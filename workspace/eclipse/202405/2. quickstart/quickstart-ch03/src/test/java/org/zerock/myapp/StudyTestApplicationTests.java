package org.zerock.myapp;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

/*
---------------------------------------
	1. WebEnvironment.MOCK (Default)
---------------------------------------
		Creates a WebApplicationContext with a mock servlet environment if servlet APIs are on the classpath,
		a ReactiveWebApplicationContext ifSpring WebFlux is on the classpath or a regular ApplicationContext otherwise.
---------------------------------------
	2. WebEnvironment.NONE
---------------------------------------
		Creates an ApplicationContext and 
		sets SpringApplication.setWebApplicationType(WebApplicationType) to WebApplicationType.NONE.
---------------------------------------
	3. WebEnvironment.RANDOM_PORT
---------------------------------------
		Creates a web application context (reactive or servlet based) and
		sets a server.port=0 Environment property (which usually triggerslistening on a random port).

		Often used in conjunction with a @LocalServerPort injected field on the test.
---------------------------------------
	4. WebEnvironment.DEFINED_PORT
---------------------------------------
		Creates a (reactive) web application context without defining any server.port=0 Environment property.
---------------------------------------
	5. UseMainMethod.NEVER (Default)
---------------------------------------
		Never use the main method, creating a test-specific SpringApplication instead.
---------------------------------------
	6. UseMainMethod.ALWAYS
---------------------------------------
		Always use the main method.
		A failure will occur if there is no @SpringBootConfiguration-annotated class or that class does not have a main method.
---------------------------------------
	7. UseMainMethod.WHEN_AVAILABLE
---------------------------------------
		Use the main method when it is available.

		If there is no @SpringBootConfiguration-annotated class or that class does not have a main method, 
		a test-specific SpringApplication will be used.
---------------------------------------
 */

//	@SpringBootTest = @BootstrapWith(SpringBootTestContextBootstrapper.class) + @ExtendWith(SpringExtension.class)
//	@SpringBootTest(webEnvironment=WebEnvironment.MOCK) 					/* Default */

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class StudyTestApplicationTests {
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
	} // beforeAll
	
	@BeforeEach
	void beforeEach() {
		log.trace("beforeEach() invoked.");
	} // beforeEach

//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(3)
	@DisplayName("1. testMethodOrder1")
	@Timeout(value=1000L, unit=TimeUnit.MILLISECONDS)
	void testMethodOrder1() {
		log.trace("testMethodOrder1() invoked.");
	} // testMethodOrder1

//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(2)
	@DisplayName("2. testMethodOrder2")
	@Timeout(value=1000L, unit=TimeUnit.MILLISECONDS)
	void testMethodOrder2() {
		log.trace("testMethodOrder2() invoked.");
	} // testMethodOrder2
	
	@AfterEach
	void afterEach() {
		log.trace("afterEach() invoked.");
	} // afterEach
	
	@AfterAll
	void afterAll() {
		log.trace("afterAll() invoked.");
	} // afterAll
	
} // end class


