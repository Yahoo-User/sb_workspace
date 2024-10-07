package org.zerock.myapp.security.dao;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UserDAOTests {
	private UserDAO dao;
	

	/**
		Important: The @PostConstruct & @PreDestroy annotation attached to the general class except Java Servlet class does Not work.
	*/
	
	@BeforeAll
	void beforeAll() throws Exception {
		log.trace("beforeAll() invoked.");
		
		this.dao = new UserDAO();
		
		Objects.requireNonNull(this.dao);
		log.info("\t+ this.dao: {}", this.dao);
	} // beforeAll
	
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("contextLoads")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void contextLoads() {
		log.trace("contextLoads() invoked.");
		
	} // contextLoads
	

} // end class

