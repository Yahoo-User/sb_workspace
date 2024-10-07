package org.zerock.myapp.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Objects;

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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.myapp.properties.ApplicationProperties;
import org.zerock.myapp.properties.SpringBootProperties;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

//--------------------
// OK: Be GOOD under (1) @SpringBootTest with only WebEnvironment.DEFINED_PORT / RANDOM_PORT, 
// XX : Be NO good under 	(1) @WebMvcTest 
//										(2) @SpringBootTest with WebEnvironment.MOCK / NONE 
//										(3) @AutoConfigureMockMvc & @SpringBootTest.
//--------------------
@ServletComponentScan																				// OK
//@ServletComponentScan(basePackages = { "org.zerock.myapp" })				// OK

//--------------------
//	XX: Be NO good under (1) @WebMvcTest,
//OK: but be GOOD under (2) @SpringBootTest (3) @AutoConfigureMockMvc & @SpringBootTest. 
//--------------------
@ConfigurationPropertiesScan(basePackageClasses = {
	ApplicationProperties.class,
	SpringBootProperties.class
})

//--------------------
//	OK: Be GOOD under (1) @SpringBootTest (2) @WebMvcTest (3) @AutoConfigureMockMvc & @SpringBootTest
//--------------------
//@EnableConfigurationProperties({
//	ApplicationProperties.class,
//	SpringBootProperties.class
//})

/**
 * -----------------------------
 * (1) @AutoConfigureMockMvc
 * -----------------------------
 *  Annotation that can be applied to a test class to enable and configure auto-configuration of `MockMvc`.
 *  
 *  Search @Controller, @RestController and also  @Service, @Repository attached classes
 *  and then create & register an object as Spring bean in Spring Context.
 * -----------------------------
		1. @ImportAutoConfiguration
		2. @PropertyMapping("spring.test.mockmvc")
*/
@AutoConfigureMockMvc

@SpringBootTest 				// Default: webEnvironment = WebEnvironment.MOCK
public class AutoConfigureMvcTests {
	@Autowired private WebApplicationContext ctx;
	
	@Autowired private ApplicationProperties applicationProperties;
	@Autowired private SpringBootProperties springBootProperties;
	
	@Autowired private MockMvc mockMvc;
	@Autowired private MockMvcBuilder mockMvcBuilder;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		// ----------------
		Objects.requireNonNull(this.ctx);
		
		log.info("\t+ this.ctx: {}", this.ctx);

		// ----------------
		assertNotNull(this.applicationProperties);
		assertNotNull(this.springBootProperties);
		
		log.info("\t+ this.applicationProperties: {}", this.applicationProperties);
		log.info("\t+ springBootProperties: {}", this.springBootProperties);

		// ----------------
		assert this.mockMvc != null;
		assert this.mockMvcBuilder != null;
		
		log.info("\t+ this.mockMvc: {}", this.mockMvc);
		log.info("\t+ this.mockMvcBuilder: {}", this.mockMvcBuilder);
	} // beforeAll
	
	@BeforeEach
	void beforeEach() {
		log.trace("beforeEach() invoked.");
		
		log.info("\t+ beanNamesCount: {}", this.ctx.getBeanDefinitionCount());
		
//		String[] beanDefNames = ctx.getBeanDefinitionNames();
//		List<String> list = Arrays.<String>asList(beanDefNames);
//		list.forEach(log::info);
	} // beforeEach
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(2)
	@DisplayName("1. testHttpStatusCode")
	@Timeout(1L)
	void testHttpStatusCode() throws Exception {
		log.trace("testHttpStatusCode() invoked.");
		
		this.mockMvc
			.perform(get("/hello").param("name", "trinity"))		// 1. returns ResultActions
//			.perform(get("/hello2").param("name", "trinity"))	// 2. raises HTTP Status Code 404
//			.perform(post("/hello").param("name", "trinity"))	// 3. raise HTTP Status Code 405
//			.perform(get("/hello"))											// 4. returns "Hello, null" body
			
			.andDo(print()) 														// Print HTTP response in detail
			.andExpect(status().isOk()) 									// Test HTTP status code:  200 OK
//			.andExpect(status().is(200)) 									// Test HTTP status code:  200 OK
//			.andExpect(status().is(404))									// Test HTTP status code:  404 Not Found.
//			.andExpect(status().is(405)) 									// Test HTTP status code:  405 Method 'POST' is not supported.
			.andExpect(content().string("Hello, trinity")); 			// Test HTTP response body whether it equals "Hello, null" or not
	} // testHttpStatusCode
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(2)
	@DisplayName("2. testViewName")
	@Timeout(2L)
	void testViewName() throws Exception {
		log.trace("testViewName() invoked.");
		
		this.mockMvc.perform(get("/returnViewName"))
		
			.andDo(print())											// Print HTTP response in detail
			.andExpect(view().name("viewName"));		// Test view name which controller returns
	} // testViewName
	
//	@Disabled
	@Order(3)
	@Test
//	@RepeatedTest(2)
	@DisplayName("3. testModelAttributes")
	@Timeout(2L)
	void testModelAttributes() throws Exception {
		log.trace("testModelAttributes() invoked.");
		
		this.mockMvc.perform(post("/returnModelAndView").param("key1", "value1").param("key2", "value2"))
		
			.andDo(print())															// Print HTTP response in detail
			.andExpect(status().isOk())											// Test HTTP status code.
			.andExpect(view().name("viewName"))						// Test view name which controller returns
		
			.andExpect(model().attributeExists("key1", "key2"))		// Test returned model attribute names
			.andExpect(model().attribute("key1", "value1"))			// Test returned model attribute name / value
			.andExpect(model().attribute("key2", "value2"));			// Test returned model attribute name / value
	} // testModelAttributes
	
//	@Disabled
	@Order(4)
	@Test
//	@RepeatedTest(2)
	@DisplayName("4. testRedirectUrl")
	@Timeout(1L)
	void testRedirectUrl() throws Exception {
		log.trace("testRedirectUrl() invoked.");
		
		this.mockMvc.perform(get("/redirectWithParameters").param("key1", "value1").param("key2", "value2"))
		
			.andDo(print())															// Print HTTP response in detail
			.andExpect(status().is(302))										// Test HTTP status code : 302 SC_MOVED_TEMPORARILY

			// Test redirected url
			.andExpect(redirectedUrl("/returnModelAndView?key1=value1&key2=value2"));
	} // testRedirectUrl
	
//	@Disabled
	@Order(5)
	@Test
//	@RepeatedTest(2)
	@DisplayName("5. returnedCookies")
	@Timeout(1L)
	void returnedCookies() throws Exception {
		log.trace("returnedCookies() invoked.");
		
		this.mockMvc.perform(get("/returnCookies"))

			.andDo(print())																		// Print HTTP response in detail
			
			.andExpect(cookie().exists("cookieName1"))							// Test whether the specified cookie exists or not
			.andExpect(cookie().value("cookieName1", "cookieValue1"))		// Test the specified cookie name & value
			.andExpect(cookie().value("cookieName2", "cookieValue2"))		// Test the specified cookie name & value
			.andExpect(cookie().maxAge("cookieName1", 1000))				// Test the specified cookie's maxAge attribute
			.andExpect(cookie().maxAge("cookieName2", 0));						// Test the specified cookie's maxage attribute
			
	} // returnedCookies

} // end class

