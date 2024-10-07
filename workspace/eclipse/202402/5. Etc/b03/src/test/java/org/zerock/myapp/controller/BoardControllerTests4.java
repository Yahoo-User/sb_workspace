package org.zerock.myapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.service.BoardService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)


@AutoConfigureMockMvc

@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
public class BoardControllerTests4 {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BoardService service;
	
	
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
		
		assert this.mockMvc != null;
		log.info("\t+ mockMvc: {}", this.mockMvc);
		
		assert this.service != null;
		log.info("\t+ service: {}", this.service);
		log.info("\t\t- type: {}", this.service.getClass().getName());
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.info("afterEach() invoked.");
		
	} // afterEach
	
//	------------------------
	
	@Test
	@Order(1)
	@DisplayName("testHello")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testHello() throws Exception {
		log.info("testHello() invoked.");
		
		when(service.hello("Yoseph", 23)).thenReturn("Yoseph, 23");
		log.info("\t+ service.hello: {}", this.service.hello("Yoseph", 23));
		
//		-----------------------
		
		this.mockMvc.
			perform(
				get("/hello").
					queryParam("name", "Yoseph").
					queryParam("age", "23")
			).
			andExpect(status().is(200)).
			andExpect(content().string("Yoseph, 23")).
			andDo(print());
	} // testHello
	
	
	@Test
	@Order(2)
	@DisplayName("testGetBoard")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testGetBoard() throws Exception {
		log.info("testGetBoard() invoked.");
		
		BoardVO vo = new BoardVO();
		vo.setCnt(0);
		vo.setSeq(1);
		vo.setTitle("TITLE");
		vo.setWriter("WRITER");
		vo.setContent("CONTENT");
		
		when(this.service.getBoard()).thenReturn(vo);
		log.info("\t+ service.getBoard: {}", this.service.getBoard());
		
//		--------------------------------
		
		this.mockMvc.
			perform(get("/getBoard")).
			andExpect(status().isOk()).
			andDo(print());
	} // testGetBoard
	

} // end class
