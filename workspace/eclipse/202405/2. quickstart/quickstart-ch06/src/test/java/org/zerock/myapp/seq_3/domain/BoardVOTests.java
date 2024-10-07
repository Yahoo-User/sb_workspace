package org.zerock.myapp.seq_3.domain;

import java.util.Date;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.zerock.myapp.seq_0.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_1.domain.BoardVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc

@SpringBootTest(
		webEnvironment = WebEnvironment.MOCK,
//		webEnvironment = WebEnvironment.DEFINED_PORT,
		
//		properties = "spring.jpa.hibernate.ddl-auto=create"
		properties = "spring.jpa.hibernate.ddl-auto=update"
	)
public class BoardVOTests extends CommonBeanCallbacks {
	private BoardVO boardVO;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		this.boardVO = new BoardVO(1L, "Title", "Yoseph", "Content", 0, new Date(), null);
		log.info("  + this.boardVO: {}", this.boardVO);
	} // beforeAll
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("contextLoads")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void contextLoads() {
		log.trace("contextLoads() invoked.");
		
		// Fluent-style Getter methods.
		this.boardVO.seq();
		this.boardVO.title();
		this.boardVO.writer();
		this.boardVO.content();
		this.boardVO.cnt();
		this.boardVO.createDate();
		this.boardVO.updateDate();
	} // contextLoads

} // end class
