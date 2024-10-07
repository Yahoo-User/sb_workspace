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

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//		webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,

//		properties = "spring.jpa.hibernate.ddl-auto=create"
		properties = "spring.jpa.hibernate.ddl-auto=update"
)
class StudyJwtAppTests {
	@Autowired(required = false) private MockMvc mockMvc;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		log.info("\t+ this.mockMvc: {}", this.mockMvc);
	} // beforeAll

	//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("contextLoads")
	@Timeout(value=1L, unit= TimeUnit.MINUTES)
	void contextLoads() {
		log.trace("contextLoads() invoked.");

	} // contextLoads

} // end class
