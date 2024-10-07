package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log4j2
@NoArgsConstructor

// To inject `MockMvc` Bean Object.
@AutoConfigureMockMvc				// OK
//@WebMvcTest						// XX

@SpringBootTest(
	properties={ "author.name=Trinity", "author.age=21" },

//	webEnvironment = SpringBootTest.WebEnvironment.MOCK					// XX - MockMvc (XX), TestRestTemplate (XX)
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT			// OK - MockMvc (OK), TestRestTemplate (OK)
//	webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT			// OK - MockMvc (OK), TestRestTemplate (XX)
)

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ch02ApplicationTests {
	// ApplicationServletEnvironment prefers to the properties defined in the `application.properties` file
	// unless redefining the required properties in the @SpringBootTest 's `properties` attribute.
	@Autowired private Environment env;

	// If @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT), It cannot be injected.
	// If @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK), It cannot be injected.
	// So when the above case, `TestRestTemplate` must be injected.
	@Autowired private TestRestTemplate restTemplate;

	// If just @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT), It can be injected. (***)
	@Autowired private MockMvc mockMvc;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assertNotNull(this.env);
		log.info("\t+ this.env: {}", this.env);

		if(this.restTemplate != null)
			log.info("\t+ this.restTemplate: {}", this.restTemplate);

		if(this.mockMvc != null)
			log.info("\t+ this.mockMvc: {}", this.mockMvc);
	} // beforeAll

	@BeforeEach
	void beforeEach() {
		log.trace("beforeEach() invoked.");

		log.info("\t+ author.name: {}", env.getProperty("author.name"));
		log.info("\t+ author.age: {}", env.getProperty("author.age"));
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
	@Test
	@Order(1)
	@DisplayName("testMockMvc")
	@Timeout(1L)
	void testMockMvc() throws Exception {
		log.trace("testMockMvc() invoked.");

		this.mockMvc.perform( get("/hello").param("name", "Yoseph") )
				.andExpect( status().isOk() )
				.andExpect( content().string("Hello, Yoseph") )
				.andDo( print() );
	} // testMockMvc


	//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testRestTemplate")
	@Timeout(1L)
	void testRestTemplate() throws Exception {
		log.trace("testRestTemplate() invoked.");

		String response = this.restTemplate.<String>getForObject("/hello?name=Yoseph", String.class);
		assertEquals("Hello, Yoseph", response);

		log.info("\t+ response: {}", response);
	} // testRestTemplate

} // end class
