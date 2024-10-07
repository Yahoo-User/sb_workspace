package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;


@Log4j2

// Class contains `required fields`,
// you have to `force` NoArgsConstructor. (*****)
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTests {
	@Autowired private MockMvc mockMvc;
	@Autowired private TestRestTemplate testRestTemplate;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assertThat(this.mockMvc).isNotNull();
		log.info("\t+ this.mockMvc: {}", this.mockMvc);

		assertThat(this.testRestTemplate).isNotNull();
		log.info("\t+ this.testRestTemplate: {}", this.testRestTemplate);
	} // beforeAll

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("testIndexPage")
	@Timeout(1L)
	void testIndexPage() {
		log.trace("testIndexPage() invoked.");

		String requestUrl = "/";
		Class<String> responseType = String.class;

		/*
		 * ================================================================
		 * TestRestTemplate.getForObject(url, responseType, urlVariable)
		 * ================================================================
		 * Retrieve a representation by doing a GET on the specified URL.
		 * The response (if any) is converted and returned.
		 *
		 */
		String response = this.testRestTemplate.getForObject(requestUrl, responseType);
		log.info("\t+ response: {}", response);

		assertThat(response).contains("index.mustache");
	} // testIndexPage

} // end class
