package org.zerock.myapp.mybatis.mapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.myapp.domain.City;

import static org.assertj.core.api.Assertions.assertThat;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
public class CityMapperTests {
    @Setter(onMethod_ = @Autowired)
    private CityMapper cityMapper;


    //	@Disabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("testFindByState1")
    @Timeout(1L)
    void testFindByState1() {
        log.trace("testFindByState1() invoked.");

        City city = this.cityMapper.findByState1("CA");

        assertThat(city).isNotNull();
        log.info("\t+ city: {}", city);
    } // testFindByState1

    //	@Disabled
    @Tag("fast")
    @Test
    @Order(2)
    @DisplayName("testFindByState2")
    @Timeout(1L)
    void testFindByState2() {
        log.trace("testFindByState2() invoked.");

        City city = this.cityMapper.findByState2(1);

        assertThat(city).isNotNull();
        log.info("\t+ city: {}", city);
    } // testFindByState2

} // end class
