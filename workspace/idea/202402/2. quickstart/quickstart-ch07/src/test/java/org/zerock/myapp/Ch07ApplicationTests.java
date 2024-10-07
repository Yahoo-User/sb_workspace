package org.zerock.myapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2

// Class contains `required fields`, You have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

// With mocking Tomcat servlet container.
//@SpringBootTest
// With Tomcat servlet container
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class Ch07ApplicationTests {

	
//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("contextLoads")
	@Timeout(1L)
	void contextLoads() {
		log.trace("contextLoads() invoked.");
		
	} // contextLoads

} // end class
