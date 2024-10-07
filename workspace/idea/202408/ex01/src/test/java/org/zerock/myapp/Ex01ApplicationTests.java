package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// @SpringBootTest =
// 		1. @ExtendWith(SpringExtension.class) +
// 		2. @BootstrapWith(SpringBootTestContextBootstrapper.class)

@SpringBootTest
class Ex01ApplicationTests {


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
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
	@Order(1)
	@Test
//	@RepeatedTest(3)
	@DisplayName("contextLoads")
	@Timeout(unit = TimeUnit.SECONDS, value = 1L)
	void contextLoads() {
		log.trace("contextLoads() invoked.");
	} // contextLoads

} // end class



