package org.zerock.myapp;

import java.util.concurrent.TimeUnit;

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

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
class Ex03ApplicationTests {
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("1. beforeAll() invoked.");
	} // beforeAll
	
	@BeforeEach
	void beforeEach() {
		log.trace("2. beforeEach() invoked.");
	} // beforeEach
	
	@AfterAll
	void afterAll() {
		log.trace("3. afterAll() invoked.");
	} // afterAll
	
	@AfterEach
	void afterEach() {
		log.trace("4. afterEach() invoked.");
	} // afterEach

//	@Disabled
	@Order(1)
//	@Test
	@RepeatedTest(3)
	@DisplayName("contextLoads")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void contextLoads() {
		log.trace("5. contextLoads() invoked.");
	} // contextLoads

} // end class

