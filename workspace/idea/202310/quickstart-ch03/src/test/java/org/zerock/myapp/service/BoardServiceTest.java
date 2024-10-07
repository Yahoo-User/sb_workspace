package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

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
@SpringBootTest
class BoardServiceTest {
    @Autowired  private MockMvc mockMvc;
    @MockBean   private BoardService boardService;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assertNotNull(this.mockMvc);
        log.info("\t+ this.mockMvc: {}", this.mockMvc);

        Objects.requireNonNull(this.boardService);
        log.info("\t+ this.boardService: {}, type: {}",
                this.boardService, this.boardService.getClass().getName());
    } // beforeAll

    @Test
    @Order(1)
    void testHello() throws Exception {
        log.trace("testHello() invoked.");
        when(this.boardService.hello("Yoseph")).thenReturn("Hello, Yoseph");

        this.mockMvc.
                perform(get("/hello2").
                    param("name", "Yoseph")).
                    andExpectAll( status().isOk(), content().string("Hello, Yoseph") ).
                    andDo(print());
    } // testHello

} // end class