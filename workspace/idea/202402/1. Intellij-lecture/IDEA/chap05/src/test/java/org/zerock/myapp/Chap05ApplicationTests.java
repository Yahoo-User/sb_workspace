package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

//@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Chap05ApplicationTests {
	@Autowired
	private WebApplicationContext context;

//	@Autowired
	private MockMvc mockMvc;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assertNotNull(this.context);
		log.info("\t+ this.context: {}", this.context);

		this.mockMvc =
			MockMvcBuilders.
				webAppContextSetup(this.context).
				apply( springSecurity() ).
				build();
		log.info("\t+ this.mockMvc: {}", this.mockMvc);
	} // beforeAll

//	@Disabled
	@Test
//	@WithAnonymousUser
	@WithMockUser(roles = {"USER"})
	@Order(1)
	@DisplayName("contextLoads")
	@Timeout(1L)
	void contextLoads() throws Exception {
		log.trace("contextLoads() invoked.");

		this.mockMvc.
			perform(get("/")).
				andExpect( status().isOk() );
	} // contextLoads

} // end class
