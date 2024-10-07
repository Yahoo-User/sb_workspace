package org.zerock.myapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// This annotation is required to inject `MockMovc` object
// when WebEnvironment.MOCK & DEFINED_PORT & RANDOM_PORT
@AutoConfigureMockMvc

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class QuickstartCh05ApplicationTests {
	@Autowired private MockMvc mockMvc;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("1. beforeAll() invoked.");
		
		assertNotNull(this.mockMvc);
		log.info("\t+ this.mockMvc: {}", this.mockMvc);
	} // beforeAll
	
	@BeforeEach
	void beforeEach() {
		log.trace("2. beforeEach() invoked.");
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.trace("3. afterEach() invoked.");
	} // afterEach
	
	@AfterAll
	void afterAll() {
		log.trace("4. afterAll() invoked.");
	} // afterAll

	
//	@Disabled
	@Order(1)
//	@Test
	@RepeatedTest(3)
	@DisplayName("contextLoads")
	@Timeout(1L)
	void contextLoads() {
		log.trace("contextLoads() invoked.");
	} // contextLoads

} // end class


