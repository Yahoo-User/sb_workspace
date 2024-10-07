package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// ================================
// @AutoConfigureMockMvc
// ================================
// (Caution) If `spring.main.web-application-type = none` in `application.properties' file,
// Then, `MockMvc` Bean Not Created and Injection Failed.
@AutoConfigureMockMvc

@SpringBootTest                                                                 // Without Embedded WAS (Mocking).
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)  // With    Embedded WAS.
public class PagingAndSortingTests {
    @Autowired private BoardRepository boardRepository;
    @Autowired private MockMvc mockMvc;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assert this.boardRepository != null;
        log.info("\t+ this.boardRepository: {}", this.boardRepository);

        assertNotNull(this.mockMvc);
        log.info("\t+ this.mockMvc: {}", this.mockMvc);
    } // beforeAll

    //    @Enabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("contextLoads")
    @Timeout(1L)
    void testFindByTitleContaining() {
        log.trace("contextLoads() invoked.");


    } // contextLoads



} // end class
