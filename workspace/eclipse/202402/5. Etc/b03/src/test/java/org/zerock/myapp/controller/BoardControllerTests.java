package org.zerock.myapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)


// Already this annotation have `@AutoConfigureMockMvc`.
// So it cannot be used with `@SpringBootTest`.
// If so, the below exception raised:
//    
// java.lang.IllegalStateException:
//		Configuration error: found multiple declarations of @BootstrapWith for test class
//@WebMvcTest


//XX : `@RunWith` not exists and this for JUnit 4.
//@RunWith(SpringRunner.class)


// This annotation cannot be used with `@WebMvcTest` annotation. (***)
@AutoConfigureMockMvc
@SpringBootTest
public class BoardControllerTests {
	
	// if @WebMvcTest absent, event though @SpringBootTest is present, the below exception raised:
	// 	org.springframework.beans.factory.NoSuchBeanDefinitionException:
	//		No qualifying bean of type 'org.springframework.test.web.servlet.MockMvc' available.
	@Autowired
	private MockMvc mockMvc;
	
	
	
	@BeforeAll
	static void beforeAll() {
		log.info("beforeAll() invoked.");
		
	} // beforeAll
	
	@AfterAll
	static void afterAll() {
		log.info("afterAll() invoked.");
		
	} // afterAll
	
	@BeforeEach
	void beforeEach() {
		log.info("beforeEach() invoked.");
		
		assert this.mockMvc != null;
		log.info("\t+ mockMvc: {}", this.mockMvc);
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.info("afterEach() invoked.");
		
	} // afterEach
	
//	----------------------------------------- //
	
	@Test
	@Order(1)
	@DisplayName("testHello1")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testHello1() throws Exception {
		log.info("testHello1() invoked.");
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/hello");
//		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/hello");
		
		// *NOT* recommended
//		reqBuilder.param("name", "Yoseph");
//		reqBuilder.param("age", "23");

		// *Recommended*
		reqBuilder.queryParam("name", "Yoseph");
		reqBuilder.queryParam("age", "23");
		
		log.info("\t+ reqBuilder: {}", reqBuilder.toString());
		
//		-------------------------
		
		ResultActions resultActions = this.mockMvc.perform(reqBuilder);
		log.info("\t+ resultActions: {}", resultActions);
		
//		-------------------------
		
		ResultHandler resultHandler = new ResultHandler() {

			@Override
			public void handle(MvcResult result) throws Exception {
				log.info("handle({})", result);
				
				MockHttpServletRequest request = result.getRequest();
				MockHttpServletResponse response = result.getResponse();
				
				// ------------------------------ //
				
				log.info("\t+ Request  : {}", request);
				log.info("\t\t- 1. Method : {}", request.getMethod());
				log.info("\t\t- 2. URI : {}", request.getRequestURI());
				log.info("\t\t- 3. URL : {}", request.getRequestURL());
				log.info("\t\t- 4. Query String : {}", request.getQueryString());
				
				// ------------------------------ //
				
				log.info("\t+ Response : {}", response);
				log.info("\t\t- 1. status : {}", response.getStatus());
				log.info("\t\t- 2. content : {}", response.getContentAsString());
			} // handle
			
		}; // anonymous implementation object
		
		resultActions.andDo(resultHandler);
		
//		-------------------------
		
		resultActions.andDo(print());
	} // testHello1
	
	
	@Test
	@Order(2)
	@DisplayName("testHello2")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testHello2() throws Exception {
		log.info("testHello2() invoked.");
		
//		ResultActions resultActions = 
//				this.mockMvc.perform(get("/hello").queryParam("name", "Yoseph").queryParam("age", "23"));
		
		ResultActions resultActions = 
				this.mockMvc.perform(post("/hello").queryParam("name", "Yoseph").queryParam("age", "23"));
		
		resultActions = resultActions.andExpect(status().isOk());
		resultActions = resultActions.andExpect(content().string("name: Yoseph, age: 23"));
		
		resultActions.andDo(print());
	} // testHello2
	

} // end class
