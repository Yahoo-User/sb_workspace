package org.zerock.myapp.mybatis.mapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
public class DateTimeMapperTests {
    @Setter(onMethod_ = @Autowired)
    private DateTimeMapper dateTimeMapper;


    //	@Disabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("testDateTimeMapper")
    @Timeout(1L)
    void testDateTimeMapper() {
        log.trace("testDateTimeMapper() invoked.");

        LocalDateTime now = this.dateTimeMapper.now();

        assertThat(now).isNotNull();
        log.info("\t+ now: {}", now);
    } // testDateTimeMapper

} // end class
