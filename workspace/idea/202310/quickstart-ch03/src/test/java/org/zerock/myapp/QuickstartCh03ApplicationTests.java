package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


/*
 * ==============================
 * @AutoConfigureMockMvc
 * ==============================
 * Annotation that can be applied to a test class
 * to enable and configure auto-configuration of `MockMvc`.
 */
@AutoConfigureMockMvc

/*
 * ==============================
 * org.springframework.test.context.junit.jupiter.SpringExtension
 * ==============================
 * `SpringExtension` integrates the Spring TestContext Framework into JUnit 5's Jupiter programming model.
 *
 * To use this extension, simply annotate a JUnit Jupiter based test class
 * with `@ExtendWith(SpringExtension.class)`, `@SpringJUnitConfig`, or `@SpringJUnitWebConfig`.
 *
 * (Notice) `@SpringBootTest` Annotation already includes `@ExtendWith(SpringExtension.class)`. (***)
 *          So, there's no needed any more.
 *
 * // 1st. Annotation
 * @ExtendWith(SpringExtension.class)
 *
 * // 2nd. Annotation == `@ExtendWith(SpringExtension.class)` +  `@ContextConfiguration`
 * @SpringJUnitConfig
 *
 * // 3rd. Annotation == `@ExtendWith(SpringExtension.class)` + `@ContextConfiguration` + `@WebAppConfiguration`
 * //@SpringJUnitWebConfig
 */

// @SpringBootTest == `@BootstrapWith(SpringBootTestContextBootstrapper.class)` + `@ExtendWith(SpringExtension.class)` (***)
@SpringBootTest

// 1. Mocking Embedded Servlet Container without Servlet Container. (Default)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
// 2. Run without Servlet Container.
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
// 3. Run with Embedded Tomcat Servlet Container with Listen Port defined in `application.properties` file.
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
// 4. Run with Embedded Tomcat Servlet Container with Random Listen Port not defined in `application.properties` file.
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuickstartCh03ApplicationTests {
	@Autowired private MockMvc mockMvc;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		Objects.requireNonNull(this.mockMvc);
		log.info("\t+ this.mockMvc: {}", this.mockMvc);
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
