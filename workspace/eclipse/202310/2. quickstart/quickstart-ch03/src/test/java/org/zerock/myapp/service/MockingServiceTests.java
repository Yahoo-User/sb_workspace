package org.zerock.myapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class MockingServiceTests {
	@Autowired private MockMvc mockMvc;
	
	/**
	 * ------------------------------------
	 * @MockBean
	 * ------------------------------------
	 * Annotation that can be used to add `mocks` to a `Spring ApplicationContext`.
	 * 
	 * Can be used as 
	 * 		(1) a class level annotation or 
	 * 		(2) on fields in either `@Configuration` classes, or 
	 * 		(3) test classes that are `@RunWith` the SpringRunner.
	 *  
	 * `Mocks` can be registered by type or by bean name:
	 * 		(1) When registered by `type`, 		any existing single bean of a matching type (including subclasses) in the context will be replaced by the mock.
	 * 		(2) When registered by `name`, 	an existing bean can be specifically targeted for replacement by a mock.
	 *	 	(3) In either case, if no existing bean is defined a new one will be added.
	 * 
	 * Dependencies that are known to the application context but are not beans (such as those registered directly) will not be found 
	 * and a mocked bean will be added to the context along side the existing dependency. 
	 * 
	 * When `@MockBean` is used on a field, as well as being registered in the application context, 
	 * the mock will also be injected into the field. 
	 * 
	 * Typical usage might be:
	 
			 @RunWith(SpringRunner.class)
			 public class ExampleTests {
			     @MockBean private ExampleService service;
			     @Autowired private UserOfService userOfService;
			
			     @Test
			     public void testUserOfService() {
			         given(this.service.greet()).willReturn("Hello");
			         String actual = this.userOfService.makeUse();
			         assertEquals("Was: Hello", actual);
			     }
			
			     @Configuration
			     @Import(UserOfService.class) // A @Component injected with ExampleService
			     static class Config {}
			     
			 } // end class
			 
	 * If there is more than one bean of the requested type, qualifier meta data must be specified at field level:  

			@RunWith(SpringRunner.class)
			 public class ExampleTests {
			
			     @MockBean
			     @Qualifier("example")
			     private ExampleService service;
			     ...
			 }
			 
	 * This annotation is `@Repeatable` and may be specified multiple times
	 * when working with Java 8 or contained within an `@MockBean` s annotation.
	 */
	@MockBean
	private BoardService service;			// *** : Mocked Service Implementation Object by Mockito.
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.mockMvc);
		log.info("\t+ this.mockMvc: {}", this.mockMvc);
		
		assertNotNull(this.service);
		log.info("\t+ this.service: {}, type: {}", this.service, this.service.getClass().getName());
	} // beforeAll
	
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(2)
	@DisplayName("1. testMockingService")
	@Timeout(1L)
	void testMockingService() throws Exception {
		log.trace("testMockingService() invoked.");
		
		// Step1. Invoke a method of the Mocked Service Bean (BoardService) using Mockito's static `when()` method
		//			and make  the response you wish using Mockito's static `thenReturn()` method in advance.
		when(this.service.hello("pyramide")).thenReturn("Hello, pyramide");
		
		// Step2. Invoke a method of the Mocked Service Bean (BoardService)
		String response = this.service.hello("pyramide");
		log.info("\t+ response: {}", response);
		
		assertEquals("Hello, pyramide", response);
	} // contextLoads

} // end class

