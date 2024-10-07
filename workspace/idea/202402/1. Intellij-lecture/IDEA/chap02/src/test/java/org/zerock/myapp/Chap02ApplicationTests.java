package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.TimeUnit;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
class Chap02ApplicationTests {
	@Autowired private MockMvc mockMvc;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		Assertions.assertNotNull(this.mockMvc);
		log.info("\t+ this.mockMvc: {}", this.mockMvc);
	} // beforeAll

//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("contextLoads")
	@Timeout(value=1L, unit= TimeUnit.SECONDS)
	void contextLoads() {
		log.trace("contextLoads() invoked.");

	} // contextLoads

} // end class
