package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


@Log4j2
@NoArgsConstructor

@AutoConfigureMockMvc
@SpringBootTest
class QuickstartCh06JspApplicationTests {


	@Test
	void contextLoads() {
		log.trace("contextLoads() invoked.");

	} // contextLoads

} // end class
