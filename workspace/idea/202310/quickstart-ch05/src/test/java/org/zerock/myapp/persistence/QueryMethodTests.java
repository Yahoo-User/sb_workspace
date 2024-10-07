package org.zerock.myapp.persistence;


import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.domain.Board;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// (Caution)
// If `spring.main.web-application-type = servlet` in `application.properties' file,
// Then, `MockMvc` Bean Not Created and Injection Failed.
@AutoConfigureMockMvc

@SpringBootTest                                                                 // Without Embedded WAS (Mocking).
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)  // With    Embedded WAS.
public class QueryMethodTests {
    @Autowired private BoardRepository boardRepository;
    @Autowired private MockMvc mockMvc;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assert this.boardRepository != null;
        log.info("\t+ this.boardRepository: {}", boardRepository);

        assertNotNull(this.mockMvc);
        log.info("\t+ this.mockMvc: {}", this.mockMvc);
    } // beforeAll

//    @BeforeAll
//    void beforeAll() {
//        log.trace("beforeAll() invoked.");
//
//        for(int i=1; i <= 200; i++) {
//            Board b = new Board();          // NEW state
//            b.setTitle("TITLE_"+i);
//            b.setWriter("Yoseph");
//            b.setContent("CONTENT_"+i);
//            b.setCreateDate(new Date());
//            b.setCnt(0L);
//
//            this.boardRepository.<Board>save(b);
//        } // for
//    } // beforeAll

    @BeforeEach
    void beforeEach() {
        log.trace("beforeEach() invoked.");

    } // beforeEach

    @AfterAll
    void afterAll() {
        log.trace("afterAll() invoked.");

    } // afterAll

    @AfterEach
    void afterEach() {
        log.trace("afterEach() invoked.");

    } // afterEach

//    @Enabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("testFindByTitle")
    @Timeout(1L)
    void testFindByTitle() {
        log.trace("testFindByTitle() invoked.");

        List<Board> list = this.boardRepository.findByTitle("TITLE");

        Objects.requireNonNull(list);
        list.forEach(log::info);
    } // testFindByTitle

    //    @Enabled
    @Tag("fast")
    @Test
    @Order(2)
    @DisplayName("testFindByContentContaining")
    @Timeout(1L)
    void testFindByContentContaining() {
        log.trace("testFindByContentContaining() invoked.");

        List<Board> list = this.boardRepository.findByContentContaining("17");

        assert list != null;
        list.forEach(log::info);
    } // testFindByContentContaining

    //    @Enabled
    @Tag("fast")
    @Test
    @Order(3)
    @DisplayName("testFindByTitleContainingOrContentContaining")
    @Timeout(1L)
    void testFindByTitleContainingOrContentContaining() {
        log.trace("testFindByTitleContainingOrContentContaining() invoked.");

        String searchTitle = "_1";
        String searchContent = "CONTENT";

        List<Board> list = this.boardRepository.findByTitleContainingOrContentContaining(searchTitle, searchContent);
        Objects.requireNonNull(list);
        list.forEach(log::info);
    } // testFindByTitleContainingOrContentContaining

    //    @Enabled
    @Tag("fast")
    @Test
    @Order(4)
    @DisplayName("testFindByTitleContainingOrderBySeqDesc")
    @Timeout(1L)
    void testFindByTitleContainingOrderBySeqDesc() {
        log.trace("testFindByTitleContainingOrderBySeqDesc() invoked.");

        String searchTitle = "17";

        List<Board> list = this.boardRepository.findByTitleContainingOrderBySeqDesc(searchTitle);

        Objects.requireNonNull(list);
        list.forEach(log::info);
    } // testFindByTitleContainingOrderBySeqDesc


} // end class
