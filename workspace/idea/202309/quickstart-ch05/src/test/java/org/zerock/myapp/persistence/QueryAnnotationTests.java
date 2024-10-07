package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.domain.Board;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
public class QueryAnnotationTests {
    @Autowired private MockMvc mockMvc;
    @Autowired private QueryAnnotationRepository queryAnnotationRepository;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

    } // beforeAll


//    @Disabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("testQueryAnnotation1")
    @Timeout(1L)
    void testQueryAnnotation1() {
        log.trace("testQueryAnnotation1() invoked.");

        List<Board> list = this.queryAnnotationRepository.queryAnnotation1("_10");

        Assertions.assertNotNull(list);
        list.forEach(log::info);
    } // testQueryAnnotation1


    //    @Disabled
    @Tag("fast")
    @Test
    @Order(2)
    @DisplayName("testQueryAnnotation2")
    @Timeout(1L)
    void testQueryAnnotation2() {
        log.trace("testQueryAnnotation2() invoked.");

        List<Board> list = this.queryAnnotationRepository.queryAnnotation2("_10");

        Assertions.assertNotNull(list);
        list.forEach(log::info);
    } // testQueryAnnotation2


    //    @Disabled
    @Tag("fast")
    @Test
    @Order(3)
    @DisplayName("testQueryAnnotation3")
    @Timeout(1L)
    void testQueryAnnotation3() {
        log.trace("testQueryAnnotation3() invoked.");

        List<Object[]> list = this.queryAnnotationRepository.queryAnnotation3("_10");

        Objects.requireNonNull(list);
        list.forEach(arr -> log.info(Arrays.toString(arr)));
    } // testQueryAnnotation3


    //    @Disabled
    @Tag("fast")
    @Test
    @Order(4)
    @DisplayName("testQueryAnnotation4")
    @Timeout(1L)
    void testQueryAnnotation4() {
        log.trace("testQueryAnnotation4() invoked.");

        List<Object[]> list = this.queryAnnotationRepository.queryAnnotation4("_10");

        Objects.requireNonNull(list);
        list.forEach(arr -> log.info(Arrays.toString(arr)));
    } // testQueryAnnotation4


    //    @Disabled
    @Tag("fast")
    @Test
    @Order(5)
    @DisplayName("testQueryAnnotation5")
    @Timeout(1L)
    void testQueryAnnotation5() {
        log.trace("testQueryAnnotation5() invoked.");

        Pageable paging = PageRequest.of(3, 20);
//        Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
        List<Object[]> list = this.queryAnnotationRepository.queryAnnotation5("seph", paging);

        Objects.requireNonNull(list);
        list.forEach(arr -> log.info(Arrays.toString(arr)));
    } // testQueryAnnotation5


} // end class
