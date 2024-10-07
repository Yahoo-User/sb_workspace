package org.zerock.myapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.web.client.RestTemplate;
import org.zerock.myapp.domain.BoardVOWithRecordType;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class BoardControllerWithServiceTests {
	@Autowired private RestTemplate restTemplate;
	@Autowired private BoardControllerWithService controller;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(this.restTemplate);
		log.info("\t+ this.restTemplate: {}", this.restTemplate);
		
		Objects.requireNonNull(this.controller);
		log.info("\t+ this.controller: {}", this.controller);
	} // beforeAll
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(2)
	@DisplayName("1. testHelloWithService")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testHelloWithService() {
		log.trace("testHelloWithService() invoked.");
		
		String response = this.restTemplate.getForObject("https://vfx-asus:443/helloWithService?name=trinity", String.class);
		assertEquals("Hello, trinity", response);
	} // testHelloWithService
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(2)
	@DisplayName("2. testGetBoardWithService")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testGetBoardWithService() {
		log.trace("testGetBoardWithService() invoked.");
		
		BoardVOWithRecordType vo = 
				this.restTemplate.getForObject(
						"https://vfx-asus:443/getBoardWithService", 
						BoardVOWithRecordType.class
				);	// .getForObject
		
		assertNotNull(vo);
		log.info("\t+ vo: {}", vo);
	} // testGetBoardWithService
	
//	@Disabled
	@Order(3)
	@Test
//	@RepeatedTest(2)
	@DisplayName("3. testGetBoard2WithService")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testGetBoard2WithService() {
		log.trace("testGetBoard2WithService() invoked.");
		
		String json = 
				this.restTemplate.getForObject(
						"https://vfx-asus:443/getBoard2WithService", 
						String.class
				);	// .getForObject

		assertNotNull(json);
		log.info("\t+ json: {}", json);
	} // testGetBoard2WithService
	
//	@Disabled
	@Order(4)
	@Test
//	@RepeatedTest(2)
	@DisplayName("4. testGetBoardListWithService")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testGetBoardListWithService() {
		log.trace("testGetBoardListWithService() invoked.");
		
		String json = 
				this.restTemplate.<String>getForObject(
						"https://vfx-asus:443/getBoardListWithService", 
						String.class
				);	// .getForObject
		
		assertNotNull(json);
		log.info("\t+ json: {}", json);
	} // testGetBoardListWithService

//	@Disabled
	@Order(5)
	@Test
//	@RepeatedTest(2)
	@DisplayName("5. testGetBoardList2WithService")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testGetBoardList2WithService() {
		log.trace("testGetBoardList2WithService() invoked.");
		
		String json = 
				this.restTemplate.<String>getForObject(
						"https://vfx-asus:443/getBoardList2WithService", 
						String.class
				);	// .getForObject
		
		assertNotNull(json);
		log.info("\t+ json: {}", json);
	} // testGetBoardList2WithService

} // end class


