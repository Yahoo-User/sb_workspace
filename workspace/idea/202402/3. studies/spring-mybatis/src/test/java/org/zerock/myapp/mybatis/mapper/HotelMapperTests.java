package org.zerock.myapp.mybatis.mapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.myapp.domain.Hotel;

import static org.assertj.core.api.Assertions.assertThat;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
public class HotelMapperTests {
    @Setter(onMethod_ = @Autowired)
    private HotelMapper hotelMapper;


    //	@Disabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("testSelectByCityId")
    @Timeout(1L)
    void testSelectByCityId() {
        log.trace("testSelectByCityId() invoked.");

        Hotel hotel = this.hotelMapper.selectByCityId(1);

        assertThat(hotel).isNotNull();
        log.info("\t+ hotel: {}", hotel);
    } // testSelectByCityId

} // end class
