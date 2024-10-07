package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
class Chap01ApplicationTests {
	@Autowired
	private MockMvc mockMvc;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assertThat(this.mockMvc).isNotNull();
		log.info("\t+ this.mockMvc: {}", this.mockMvc);
	} // beforeAll

	@BeforeEach
	void beforeEach() {
		log.trace("beforeEach() invoked.");

	} // beforeEach

	@AfterAll
	void afterAll() {
		log.trace("afterAll() invoked.");

	} // afterAll

	@AfterEach
	void afterEach() {
		log.trace("afterEach() invoked.");

	} // afterEach


//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("contextLoads")
	@Timeout(1L)
//	@Timeout(value=1L, unit = TimeUnit.SECONDS)
	void contextLoads() {
		log.trace("contextLoads() invoked.");

	} // contextLoads

} // end class
