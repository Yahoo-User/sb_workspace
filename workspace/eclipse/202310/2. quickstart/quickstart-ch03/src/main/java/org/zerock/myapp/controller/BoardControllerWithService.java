package org.zerock.myapp.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.BoardVOWithLombokValue;
import org.zerock.myapp.domain.BoardVOWithRecordType;
import org.zerock.myapp.service.BoardService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor 											// DI @Since 4.2

@RestController
@ConditionalOnProperty(name = "test-http-protocol", havingValue = "HTTPs")
public class BoardControllerWithService {
	private BoardService service;						// DI @Since 4.2
	
	
	@GetMapping("/helloWithService")
	String helloWithService(String name) {
		log.trace("helloWithService({}) invoked.", name);
		return this.service.hello(name);
	} // helloWithService
	
	@GetMapping("/getBoardWithService")
	BoardVOWithRecordType getBoardWithService() {
		log.trace("getBoardWithService() invoked.");
		return this.service.getBoard();
	} // getBoardWithService
	
	@GetMapping("/getBoard2WithService")
	BoardVOWithLombokValue getBoard2WithService() {
		log.trace("getBoard2WithService() invoked.");
		return this.service.getBoard2();
	} // getBoard2WithService
	
	@GetMapping("/getBoardListWithService")
	List<BoardVOWithRecordType> getBoardListWithService() {
		log.trace("getBoardListWithService() invoked.");
		return this.service.getBoardList();
	} // getBoardListWithService
	
	@GetMapping("/getBoardList2WithService")
	List<BoardVOWithLombokValue> getBoardList2WithService() {
		log.trace("getBoardList2WithService() invoked.");
		return this.service.getBoardList2();
	} // getBoardList2WithService
	
	
	/*
	 * ------------------------------
	 * @PostConstruct
	 * ------------------------------
	 * The annotation is used on a method that needs to be executed after dependency injection is done to performany initialization.
	 * This method must be invoked before the class is put into service.
	 * This annotation must be supported on all classes that support dependency injection.
	 * The method annotated with PostConstruct must be invoked even if the class does not request any resources to be injected.
	 */
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
		
		assert !Objects.isNull(this.service);
		log.info("\t+ this.service: {}", this.service);
	} // postConstruct
	
	@PreDestroy
	void preDestroy() {
		log.trace("preDestroy() invoked.");
	} // preDestroy

} // end class

