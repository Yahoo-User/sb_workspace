package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;


@Slf4j
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

/*
 * ===================================
 * `@Commit` Annotation
 * ===================================
 * `@Commit` is a test annotation that is used to indicate that a test-managed transaction should be committed
 * after the test method has completed.
 *
 */
@Commit

/*
 * ===================================
 * `@AutoConfigureMockMvc` Annotation
 * ===================================
 * Annotation that can be applied to a test class to enable and configure auto-configuration of `MockMvc`.
 *
 */
@AutoConfigureMockMvc

// With mocking Tomcat servlet container.
//@SpringBootTest
// With Tomcat embedded servlet container.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class QuickstartCh08JspApplicationTests {
	@Autowired private MockMvc mockMvc;


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
