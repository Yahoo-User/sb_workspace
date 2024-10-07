package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.myapp.persistence.PersonRepository;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
class SpringDataJdbcApplicationTests {
	@Autowired
	private PersonRepository personRepository;


	@Test
	void contextLoads() {
		log.trace("contextLoads() invoked.");
		log.info("\t+ this.personRepository: {}", this.personRepository);
	} // contextLoads

} // end class
