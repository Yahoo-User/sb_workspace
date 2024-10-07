package org.zerock.myapp.common;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@AutoConfigureMockMvc

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public abstract class CommonJUnitTestCallbacks extends CommonBeanCallbacks {
	@Autowired(required = false) protected  MockMvc mockMvc;
	

	@BeforeAll
	protected
	void beforeAll() {
		log.trace("(BA) beforeAll() invoked.");
		log.info("  + this.mockMvc: {}", this.mockMvc);
	} // beforeAll
	
	@BeforeEach
	protected
	void beforeEach() {
		log.trace("(BE) beforeEach() invoked.");
	} // beforeEach
	
	@AfterEach
	protected
	void afterEach() {
		log.trace("(AE) afterEach() invoked.");
	} // afterEach
	
	@AfterAll
	protected
	void afterAll() {
		log.trace("(AA) afterAll() invoked.");
	} // afterAll
	
} // end class

