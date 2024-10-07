package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
class Chap03ApplicationTests {
	@Autowired private MockMvc mockMvc;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assertThat(this.mockMvc).isNotNull();
		log.trace("\t+ this.mockMvc: {}", this.mockMvc);
	} // beforeAll

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
