package org.zerock.myapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.myapp.domain.BoardVOWithLombokValue;
import org.zerock.myapp.domain.BoardVOWithRecordType;
import org.zerock.myapp.properties.ApplicationProperties;
import org.zerock.myapp.properties.SpringBootProperties;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

//--------------------
//	XX: Be NO good under (1) @WebMvcTest, 
// OK: but be GOOD under (2) @SpringBootTest (3) @AutoConfigureMockMvc & @SpringBootTest. 
//--------------------
@ConfigurationPropertiesScan(basePackageClasses = {
	ApplicationProperties.class,
	SpringBootProperties.class
})

//--------------------
//	OK: Be GOOD under (1) @SpringBootTest (2) @WebMvcTest (3) @AutoConfigureMockMvc & @SpringBootTest
//--------------------
//@EnableConfigurationProperties({
//	ApplicationProperties.class,
//	SpringBootProperties.class
//})

//--------------------
//OK: Be GOOD under (1) @SpringBootTest with only WebEnvironment.DEFINED_PORT / RANDOM_PORT, 
//XX : Be NO good under 	(1) @WebMvcTest 
//										(2) @SpringBootTest with WebEnvironment.MOCK / NONE 
//										(3) @AutoConfigureMockMvc & @SpringBootTest.
//--------------------
@ServletComponentScan																				// OK
//@ServletComponentScan(basePackages = { "org.zerock.myapp" })				// OK

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class TestRestTemplateTests {
	@Autowired private WebApplicationContext ctx;
	
	@Autowired private ApplicationProperties applicationProperties;
	@Autowired private SpringBootProperties springBootProperties;
	
	// Rest Client For HTTP, but NOT HTTPs.
//	@Autowired private TestRestTemplate restTemplate;
	
	// Rest Client for HTTPs with SSL
	@Autowired private RestTemplate restTemplate;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		// ------
		assertNotNull(this.ctx);
		log.info("\t+ this.ctx: {}", this.ctx);

		// ------
		Objects.requireNonNull(this.applicationProperties);
		Objects.requireNonNull(this.springBootProperties);
		log.info("\t+ this.applicationProperties: {}", this.applicationProperties);
		log.info("\t+ this.springBootProperties: {}", this.springBootProperties);

		// ------
		assert this.restTemplate != null;
		log.info("\t+ this.restTemplate: {}", this.restTemplate);
	} // beforeAll
	
	@BeforeEach
	void beforeEach() {
		log.trace("beforeEach() invoked.");
		
		// ------
		int beanDefCount = this.ctx.getBeanDefinitionCount();
		log.info("\t+ beanDefCount: {}", beanDefCount);

		// ------
//		String[] beanDefNames = this.ctx.getBeanDefinitionNames();
//		List<String> list = Arrays.asList(beanDefNames);
//		list.forEach(log::info);
	} // beforeEach
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(2)
	@DisplayName("1. testHelloWithHTTPs")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testHelloWithHTTPs()  {
		log.trace("testHelloWithHTTPs() invoked.");
		
		String hello = this.restTemplate.getForObject("https://localhost/hello?name=trinity", String.class);
		log.info("\t+ hello: {}", hello);
	} // testHelloWithHTTPs
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(2)
	@DisplayName("2. testGetBoardWithHTTPs")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testGetBoardWithHTTPs()  {
		log.trace("testGetBoardWithHTTPs() invoked.");
		
		BoardVOWithRecordType vo = 
				this.restTemplate.<BoardVOWithRecordType>getForObject(
						"https://localhost:443/getBoard",
						BoardVOWithRecordType.class
				);	// .getForObject
		
		Objects.requireNonNull(vo);
		log.info("vo: {}", vo);
		
		assertEquals("writer", vo.writer());
	} // testGetBoardWithHTTPs
	
//	@Disabled
	@Order(3)
	@Test
//	@RepeatedTest(2)
	@DisplayName("3. testGetBoard2WithHTTPs")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testGetBoard2WithHTTPs()  {
		log.trace("testGetBoard2WithHTTPs() invoked.");
		
		BoardVOWithLombokValue vo = 
				this.restTemplate.<BoardVOWithLombokValue>getForObject(
						"https://localhost:443/getBoard2",
						BoardVOWithLombokValue.class
				);	// .getForObject
		
		Objects.requireNonNull(vo);
		log.info("vo: {}", vo);
		
		assertEquals("writer", vo.getWriter());
	} // testGetBoard2WithHTTPs
	
} // end class







