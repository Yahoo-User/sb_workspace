package org.zerock.myapp;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.zerock.myapp.controller.BoardController;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@TestInstance(Lifecycle.PER_CLASS)
//@TestInstance(Lifecycle.PER_METHOD)

@TestMethodOrder(OrderAnnotation.class)
//@TestMethodOrder(MethodName.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
//@TestMethodOrder(Random.class)


@Log4j2
@NoArgsConstructor


@SpringBootTest(
	classes= { BoardController.class },
	properties= { "author.name=Trinity", "author.age=13", "author.nation=korea" }
)
public class Junit5Template {
	
	@Setter(onMethod_= {@Autowired})
	private Environment env;
	
	@Setter(onMethod_= @Autowired)
	private BoardController controller;
	
	
	
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
		
		assert this.env != null;
		log.info("\t+ env: {}", env);
		
		Objects.requireNonNull(this.controller);
		log.info("\t+ controller: {}", this.controller);
	} // beforeEach
	
	
	@AfterEach
	void afterEach() {
		log.info("afterEach() invoked.");
		
	} // afterEach
	
	

	@Test
	@Order(4)
	@DisplayName("getPropertiesTest")
	@Timeout(value=10, unit=TimeUnit.MILLISECONDS)
	public void getPropertiesTest() {
		log.info("getPropertiesTest() invoked.");
		
		log.info("author name: {}, age: {}, nation: {}", 
				env.getProperty("author.name"), env.getProperty("author.age"), env.getProperty("author.nation"));
	} // getPropertiesTest
	

	@Test
	@Order(2)
	@DisplayName("testUnit1")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	public void testUnit1() {
		log.info("testUnit1() invoked.");
		log.info("\t+ this: {}", this);
		
	} // testUnit1
	

	@Test
	@Order(1)
	@DisplayName("testUnit3")
	@Timeout(value=100, unit=TimeUnit.MILLISECONDS)
	public void testUnit3() {
		log.info("testUnit3() invoked.");
		log.info("\t+ this: {}", this);
		
	} // testUnit3
	

	@Test
	@Order(3)
	@DisplayName("testUnit2")
	@Timeout(value=5000, unit=TimeUnit.MICROSECONDS)
	public void testUnit2() {
		log.info("testUnit2() invoked.");
		log.info("\t+ this: {}", this);
		
	} // testUnit2
	
	

} // end class
