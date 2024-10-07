package org.zerock.myapp.controller;


import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.domain.BoardVO;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

/*
 * ====================================
 * 1st. method: Without Servlet Container
 *      @WebMvcTest + @AutoConfigureMockMvc
 * ====================================
 * Annotation that can be used for a Spring MVC test that focuses only on Spring MVC components.
 * Using this annotation will disable full auto-configuration and instead apply only configuration relevant to MVC tests.
 *
 * (i.e.
 *       `@Controller`, `@ControllerAdvice`, `@JsonComponent`, `Converter/GenericConverter`,
 *       `Filter`, `WebMvcConfigurer` and `HandlerMethodArgumentResolver` beans
 *        but *NOT* `@Component`, `@Service` or `@Repository` beans.
 * )
 *
 * @BootstrapWith(WebMvcTestContextBootstrapper.class)
 * @ExtendWith(SpringExtension.class)
 * @OverrideAutoConfiguration(enabled = false)
 * @TypeExcludeFilters(WebMvcTypeExcludeFilter.class)
 * @AutoConfigureCache
 * @AutoConfigureWebMvc
 * @AutoConfigureMockMvc
 * @ImportAutoConfiguration
 */
//@WebMvcTest
//@WebMvcTest(controllers = BoardController.class)

/*
 * ====================================
 * 2nd. method: Test without Servlet Container
 *      @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) + @AutoConfigureMockMvc
 * ====================================
 */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)    // Default
//@AutoConfigureMockMvc

/*
 * ====================================
 * 3rd. method: Test with Servlet Container
 *      @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) + TestRestTemplate
 *      @SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT) + TestRestTemplate
 * ====================================
 */
// With random servlet listen port.
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// With servlet listen port defined in the `application.properties` file.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class BoardControllerTests {
    @Autowired
    private MockMvc mockMvc;                    // Using When without Servlet Container.
    @Autowired
    private TestRestTemplate restTemplate;      // Using when with Servlet Container


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assertNotNull(this.mockMvc);
        log.info("\t+ this.mockMvc: {}", this.mockMvc);

        Objects.requireNonNull(this.restTemplate);
        log.info("\t+ this.restTemplate: {}", this.restTemplate);
    } // beforeAll

    @Test
    @Order(1)
    void testHelloWithoutEmbeddedServletContainer() throws Exception {
        log.trace("testHelloWithoutEmbeddedServletContainer() invoked.");

        MockHttpSession session = new MockHttpSession();
        log.info("\t+ MockHttpSession Id: {}", session.getId());

        this.mockMvc.
                perform(
                    // Parameter Type: `RequestBuilder`
                    // *** By using `MockMvcRequestBuilders` Helper Class Static Methods.
                    get("/hello").param("name", "Yoseph").session(session)
                ).
                //andExpect(status().isOk()).
                //andExpect(content().string("Hello, Yoseph")).
                andExpectAll(
                    // Parameter Type: `ResultMatcher`
                    // *** By using `MockMvcResultMatchers` Helper Class Static Methods.
                    status().isOk(),
                    content().string("Hello, Yoseph(1)")
                ).
                andDo(
                    // Parameter Type: `ResultHandler`
                    // *** By using `MockMvcResultHandlers` Helper Class Static Methods.
                    print()
                );
    } // testHelloWithoutEmbeddedServletContainer

    @Test
    @Order(2)
    void testHelloWithEmbeddedServletContainer() {
        log.trace("testHelloWithEmbeddedServletContainer() invoked.");

        BoardVO vo = this.restTemplate.getForObject("/getBoard", BoardVO.class);

        assertThat(vo).isNotNull().isInstanceOf(BoardVO.class);
        log.info("\t+ vo: {}", vo);
    } // testHelloWithEmbeddedServletContainer

} // end class
