package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.service.BoardService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardControllerTests {
    @Autowired private MockMvc mockMvc;
    @MockBean private BoardService service;



    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assertNotNull(this.mockMvc);
        log.info("\t+ this.mockMvc: {}", this.mockMvc);

        assert this.service != null;
        log.info("\t+ this.service: {}, type: {}", this.service, this.service.getClass().getName());
    } // beforeAll


    @Test
    void contextLoads() throws Exception {
        log.trace("contextLoads() invoked.");

        // Mocking of @MockBean using when(methodCall).thenReturn(value)
        when(this.service.hello("Yoseph")).thenReturn("Hello : Yoseph");

        this.mockMvc.perform( get("/hello").param("name", "Yoseph") )
                .andExpect( status().isOk() )
                .andExpect( content().string("Hello : Yoseph") )
                .andDo( print() );
    } // contextLoads


} // end class
