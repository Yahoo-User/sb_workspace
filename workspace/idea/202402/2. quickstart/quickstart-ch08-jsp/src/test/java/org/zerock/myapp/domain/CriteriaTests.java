package org.zerock.myapp.domain;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Commit
@AutoConfigureMockMvc
@SpringBootTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CriteriaTests {


    @Test
    void testCriteria() {
        log.trace("testCriteria() invoked.");

        Criteria cri = new Criteria();
        cri.setPageNumber(7);
        cri.setPageSize(10);
        cri.setPagesPerPage(7);

        log.info("\t+ cri: {}", cri);
        log.info("\t+ pagingUri: {}", cri.getPagingUri());
    } // testCriteria

} // end class
