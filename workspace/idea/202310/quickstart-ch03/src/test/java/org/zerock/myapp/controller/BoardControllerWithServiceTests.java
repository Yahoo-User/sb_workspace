package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.service.BoardService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
class BoardControllerWithServiceTests {
    @Autowired private MockMvc mockMvc;
    @Autowired private BoardService boardService;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assert this.mockMvc != null;
        log.info("\t+ this.mockMvc: {}", this.mockMvc);

        assertNotNull(this.boardService);
        log.info("\t+ this.boardService: {}", this.boardService);
    } // beforeAll

    @Test
    @Order(1)
    void testHello2() throws Exception {
        log.trace("testHello2() invoked.");

        this.mockMvc.
                perform(get("/hello2").param("name", "Trinity")).
                andExpect(status().isOk()).
                andExpect(content().string("Hello, Trinity")).
                andDo(print());
    } // testHello2

    @Test
    @Order(2)
    void testGetBoard2() throws Exception {
        log.trace("testGetBoard2() invoked.");

        this.mockMvc.
                perform(get("/getBoard")).
                andExpect(status().is(200)).
                andDo(print());
    } // testGetBoard2

    @Test
    @Order(3)
    void testGetBoardList2() throws Exception {
        log.trace("testGetBoardList2() invoked.");

        this.mockMvc.
                perform(get("/getBoardList2")).
                andExpect(status().is(200)).
                andDo(print());
    } // testGetBoardList2

} // end class