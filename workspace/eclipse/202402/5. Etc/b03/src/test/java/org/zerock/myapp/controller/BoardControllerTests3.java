package org.zerock.myapp.controller;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class BoardControllerTests3 {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
//	org.springframework.beans.factory.NoSuchBeanDefinitionException:
//	No qualifying bean of type 'org.springframework.test.web.servlet.MockMvc' available
//	@Autowired
//	private MockMvc mockMvc;
	
	
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
		
		assert this.restTemplate != null;
		log.info("\t+ restTemplate: {}", this.restTemplate);
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.info("afterEach() invoked.");
		
	} // afterEach
	
//	-------------------------------
	
	@Test
	@Order(1)
	@DisplayName("testHello")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testHello() {
		log.info("testHello() invoked.");
		
		String hello=this.restTemplate.<String>getForObject("/hello?name=Yoseph&age=23", String.class);
		log.info("\t+ hello: {}", hello);
	} // testHello
	
	
	@Test
	@Order(2)
	@DisplayName("testGetBoard")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testGetBoard() {
		log.info("testGetBoard() invoked.");
		
		String json = this.restTemplate.<String>getForObject("/getBoard", String.class);
		log.info("\t+ json: {}", json);
	} // testGetBoard
	
	
	@Test
	@Order(3)
	@DisplayName("testGetBoardList")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testBoardList() {
		log.info("testGetBoardList() invoked.");
		
//		String json = this.restTemplate.<String>getForObject("/getBoardList", String.class);
		String json = this.restTemplate.postForObject("/getBoardList", null, String.class);
		
		log.info("\t+ json: {}", json);
	} // testBoardList

} // end class
