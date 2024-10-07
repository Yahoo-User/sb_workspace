package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
public class LoginControllerTests {
    @Autowired
    private MockMvc mockMvc;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assertThat(this.mockMvc).isNotNull();
        log.info("\t+ this.mockMvc: {}", this.mockMvc);
    } // beforeAll

    //    @Disabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("testAuthenticate")
    @Timeout(1L)
    void testAuthenticate() throws Exception {
        log.trace("testAuthenticate() invoked.");

        ModelAndView result =
                this.mockMvc.
                        perform(
                            post("/login").
                                param("id", "member1").
                                param("password", "member111")
                        ).
                        andReturn().
                        getModelAndView();

        assertThat(result).isNotNull();
        log.info("\t+ result: {}", result);
    } // testAuthenticate

} // end class
