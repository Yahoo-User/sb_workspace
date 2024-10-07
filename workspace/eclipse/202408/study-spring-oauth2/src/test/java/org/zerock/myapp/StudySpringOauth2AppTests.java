package org.zerock.myapp;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
class StudySpringOauth2AppTests {

	
//		@Disabled
		@Order(1)
//		@Test
		@RepeatedTest(1)
		@DisplayName("contextLoads")
		@Timeout(value=1L, unit=TimeUnit.MINUTES)
		void contextLoads() {
			log.trace("contextLoads() invoked.");
		} // contextLoads

} // end class

