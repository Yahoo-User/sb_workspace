package org.zerock.other;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;



@Log4j2
//@NoArgsConstructor

@Component
public class OtherController {
	
	
	public OtherController() {
		log.info("Default Constructor invoked.");
	} // default constructor

} // end class
