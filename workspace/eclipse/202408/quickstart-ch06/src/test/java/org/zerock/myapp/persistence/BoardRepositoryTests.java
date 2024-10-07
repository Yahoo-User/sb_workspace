package org.zerock.myapp.persistence;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.myapp.seq_4.domain.Board;
import org.zerock.myapp.seq_6.persistence.BoardRepository;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
public class BoardRepositoryTests {
	@Autowired private BoardRepository dao;
	
	
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
		
		Objects.requireNonNull(this.dao);
		log.info("\t+ this.dao: {}", this.dao);
	} // postConstruct

	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. prepareData")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void prepareData() {
		log.trace("prepareData() invoked.");
		
		LongStream.rangeClosed(1L, 170L).forEachOrdered(seq -> {
			log.info("\t+ forEachOrdered({}) invoked.", seq);
			
			Board transientBoard = new Board();
			
			transientBoard.setTitle("TITLE_"+seq);
			transientBoard.setWriter("Writer_"+seq);
			transientBoard.setContent("CONTENT_"+seq);
			
			this.dao.save(transientBoard);
		});	// .forEachOrdered
		
	} // prepareData
	
	
} // end class


