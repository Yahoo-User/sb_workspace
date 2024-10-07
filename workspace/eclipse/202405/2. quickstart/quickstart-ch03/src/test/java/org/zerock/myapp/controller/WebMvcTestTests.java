package org.zerock.myapp.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
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
// XX: Be NO good under (1) @WebMvcTest,
// OK: but be GOOD under (2) @SpringBootTest (3) @AutoConfigureMockMvc & @SpringBootTest. 
//--------------------
//@ConfigurationPropertiesScan(basePackageClasses = {		
//	SpringBootProperties.class,
//	ApplicationProperties.class
//})

//--------------------
//	OK: Be GOOD under (1) @SpringBootTest (2) @WebMvcTest (3) @AutoConfigureMockMvc & @SpringBootTest
//--------------------
@EnableConfigurationProperties({										
	SpringBootProperties.class, 
	ApplicationProperties.class
})

/**
 * -----------------------------
 * (1) @SpringBootTest
 * -----------------------------
 * class WebMvcTestContextBootstrapper extends SpringBootTestContextBootstrapper
 * 
 * 		1. @BootstrapWith(SpringBootTestContextBootstrapper.class)		<--- 
 * 		2. @ExtendWith(SpringExtension.class)										<--- 
 * 
 * -----------------------------
 * (2) @WebMvcTest
 * -----------------------------
 * Search @Controller, @RestController attached classes and create & register an object as Spring bean in Spring Context.
 * But, @Service, @Repository attached classes are NOT created.
 * 
 * NOTE: Do NOT use @WebMvcTest and @SpringBootTest together. 	(***)
 * -----------------------------
		1. @BootstrapWith(WebMvcTestContextBootstrapper.class)			<--- 
		2. @ExtendWith(SpringExtension.class)										<--- 
		3. @OverrideAutoConfiguration(enabled = false)
		4. @TypeExcludeFilters(WebMvcTypeExcludeFilter.class)
		5. @AutoConfigureCache
		6. @AutoConfigureWebMvc
		7. @AutoConfigureMockMvc
		8. @ImportAutoConfiguration
*/

@WebMvcTest
public class WebMvcTestTests {
	@Autowired private Environment env;
	@Autowired private WebApplicationContext ctx;
	
	@Autowired private SpringBootProperties springProperties;
	@Autowired private ApplicationProperties AppProperties;
	
	@Autowired private MockMvc mockMvc;
	@Autowired private MockMvcBuilder mockMvcBuilder;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		// ----------------
		assertNotNull(this.env);
		log.info("   + this.env: {}", this.env);
		
		Objects.requireNonNull(this.ctx);
		log.info("   + this.ctx: {}", this.ctx);
		
		// ----------------
		assert this.springProperties != null;
		assert this.AppProperties != null;
		
		log.info("   + this.springProperties: {}", springProperties);
		log.info("   + this.AppProperties: {}", AppProperties);

		// ----------------
		Objects.requireNonNull(this.mockMvc);
		Objects.requireNonNull(this.mockMvcBuilder);
		
		log.info("   + this.mockMvc: {}", this.mockMvc);
		log.info("   + this.mockMvcBuilder: {}", this.mockMvcBuilder);
	} // beforeAll

	private int counter = 1;
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(2)
	@DisplayName("1. testWebApplicationContext")
	@Timeout(1L)
	void testWebApplicationContext() {
		log.trace("testWebApplicationContext() invoked.");
		
		int beanDefCnt = ctx.getBeanDefinitionCount();
		
		String[] beanDefNames = ctx.getBeanDefinitionNames();
		List<String> beanNames = Arrays.<String>asList(beanDefNames);
		
		log.info("\t+ beanDefCnt: {}", beanDefCnt);
		
		beanNames.forEach(name -> {
			log.info("\t{}. {}", this.counter++, name);
		});
		beanNames.clear();
	} // testWebApplicationContext
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(3)
	@DisplayName("2. testMockMvc")
	@Timeout(1L)
	void testMockMvc() throws Exception {
		log.trace("testMockMvc() invoked.");

		// ---------------------------------------
		// 1. Perform a request & Receive a response with static imports: MockMvcRequestBuilders.*
		// ---------------------------------------
		
		// --- 1st. method --------------
//		ResultActions resultActions = this.mockMvc.perform(servletContext -> get("/hello").param("name", "pyramide").buildRequest(servletContext));
//		ResultActions resultActions = this.mockMvc.perform(get("/hello").param("name", "pyramide")::buildRequest);

		// --- 2nd. method --------------
		ResultActions resultActions = this.mockMvc.perform(get("/hello").param("name", "trinity"));

		// ---------------------------------------
		// 2. Handling a Response with static imports: MockMvcResultMatchers.*
		// ---------------------------------------
		
		/**
		 * ------------------------------------------------------------
		 * ResultActions ResultActions.andExpect(ResultMatcher matcher) throws Exception
		 * ------------------------------------------------------------
		 * Perform an expectation.
		 * 
		 * Example:
		 
		 		- static imports: MockMvcRequestBuilders.*, MockMvcResultMatchers.*
				- You can invoke `andExpect()` multiple times as in the following example :
				
					 mockMvc.perform(get("/person/1"))
								   .andExpect(status().isOk())
								   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
								   .andExpect(jsonPath("$.person.name").value("Jason"));
		 */
		resultActions
			.andExpect(status().isOk())
			.andExpect(content().string("Hello, trinity"));
		
		/**
		 * ------------------------------------------------------------
		 * ResultActions ResultActions.andDo(ResultHandler handler) throws Exception
		 * ------------------------------------------------------------
		 * Perform a general action.
		 * 
		 * Example:
		 
					- static imports: MockMvcRequestBuilders.*, MockMvcResultHandlers.*
					
						mockMvc.perform(get("/form")).andDo(print());
		 */
		resultActions.andDo(print());
	} // testMockMvc
	
} // end class


