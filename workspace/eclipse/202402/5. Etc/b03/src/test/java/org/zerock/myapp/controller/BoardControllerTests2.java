package org.zerock.myapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Objects;
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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment=WebEnvironment.MOCK)   // Default
//@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
//@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
//@SpringBootTest(webEnvironment=WebEnvironment.NONE)
public class BoardControllerTests2 {
	
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
		
		Objects.requireNonNull(this.mockMvc);
		log.info("\t+ mockMvc: {}", this.mockMvc);
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.info("afterEach() invoked.");
		
	} // afterEach
	
//	==============================
	
	@Test
	@Order(1)
	@DisplayName("testHello")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testHello() throws Exception {
		log.info("testHello() invoked.");
		
		this.mockMvc.perform(
//			get("/hello").
//				queryParam("name", "Yoseph").
//				queryParam("age", "23")
				
			post("/hello").
				queryParam("name", "Yoseph").
				queryParam("age", "23")
		).andExpect(status().isOk()).andDo(print());
	} // testHello
	

} // end class
