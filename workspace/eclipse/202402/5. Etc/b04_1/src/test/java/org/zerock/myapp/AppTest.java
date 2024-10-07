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

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

/**
 * Unit test for simple App.
 */
@TestInstance(Lifecycle.PER_CLASS)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestMethodOrder(MethodOrderer.Random.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestMethodOrder(MethodOrderer.Alphanumeric.class)		// Deprecated.
public class AppTest {
	
	
	@BeforeAll
	static void beforeAll() {
		log.debug("beforeAll() invoked.");
		
	} // beforeAll
	
	@AfterAll
	static void afterAll() {
		log.debug("afterAll() invoked.");
		
	} // afterAll
	
	@BeforeEach
	void beforeEach() {
		log.debug("beforeEach() invoked.");
		
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.debug("afterEach() invoked.");
		
	} // afterEach
	
//	=============================
	
	@Test
	@Order(1)
	@DisplayName("contexts")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void XXX() {
		log.debug("XXX() invoked.");
		
	} // XXX
	
	
} // end class
