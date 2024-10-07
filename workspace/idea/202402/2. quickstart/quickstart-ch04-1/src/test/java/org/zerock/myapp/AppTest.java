package org.zerock.myapp;

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

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
	} // beforeAll
	
	@BeforeEach
	void beforeEach() {
		log.trace("beforeEach() invoked.");
		
	} // beforeEach
	
	
	@AfterEach
	void afterEach() {
		log.trace("afterEach() invoked.");
		
	} // afterEach
	
	@AfterAll
	void afterAll() {
		log.trace("afterAll() invoked.");
		
	} // afterAll
	
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(2)
	@DisplayName("contextLoads1")
	@Timeout(1L)
	void contextLoads1() {
		log.trace("contextLoads1() invoked.");
		
	} // contextLoads1
	
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(2)
	@DisplayName("contextLoads2")
	@Timeout(1L)
	void contextLoads2() {
		log.trace("contextLoads2() invoked.");
		
	} // contextLoads2

} // end class

