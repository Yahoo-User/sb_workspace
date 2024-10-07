package org.zerock.myapp;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@SpringBootTest
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ex02ApplicationTests {
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
	} // beforeAll
	
	@BeforeEach
	void beforeEach() {
		log.trace("beforeEach() invoked.");
	} // beforeEach
	
	@AfterAll
	void afterAll() {
		log.trace("afterAll() invoked.");
	} // afterAll
	
	@AfterEach
	void afterEach() {
		log.trace("afterEach() invoked.");
	} // afterEach

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





