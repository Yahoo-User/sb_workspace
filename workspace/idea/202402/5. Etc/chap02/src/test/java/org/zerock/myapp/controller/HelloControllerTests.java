package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

//@RunWith(SpringRunner.class)          // in JUnit v4.x
//@WebMvcTest(controllers = { HelloController.class })  // replace with @SpringBootTest + @AutoConfigureMockMvc

@AutoConfigureMockMvc
@SpringBootTest
public class HelloControllerTests {
    @Autowired private MockMvc mockMvc;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assertThat(this.mockMvc).isNotNull();
        log.info("\t1. this.mockMvc: {}", this.mockMvc);
    } // beforeAll


//    @Disabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("testHello")
    @Timeout(1L)
    void testHello() throws Exception {
        log.trace("testHello() invoked.");

        this.mockMvc.
            perform( get("/hello") ).
                andExpect(status().isOk()).
                andExpect(content().string("Hello, Yoseph"));
    } // testHello


    //    @Disabled
    @Tag("fast")
    @Test
    @Order(2)
    @DisplayName("testHelloDto")
    @Timeout(1L)
    void testHelloDto() throws Exception {
        log.trace("testHelloDto() invoked.");

        String name = "Yoseph";
        Integer amount = 23;

        log.info("\t+ jsonPath('$.name', is(name)): {}", jsonPath("$.name", is(name)));
        log.info("\t+ jsonPath('$.amount', is(amount)): {}", jsonPath("$.amount", is(amount)));

        this.mockMvc.
            perform(
                get("/hello/dto").
                    param("name", name).
                    param("amount", String.valueOf(amount))
            ).
            andExpect(status().isOk()).
            andExpect(jsonPath("$.name", is(name))).
            andExpect(jsonPath("$.amount", is(amount)));
    } // testHelloDto

} // end class
