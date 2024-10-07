package org.zerock.myapp.seq_3.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component("customApplicationRunner")
public class CustomApplicationRunner implements ApplicationRunner {
	@Value(value = "${server.port}")
	private Integer serverPort;
	
	@Value(value = "${server.address}")
	private String serverAddress;
	

//	@Deprecated
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.trace("run({}) invoked.", args);
		log.info("\t+ server.port: {}, server.address: {}", this.serverPort, this.serverAddress);
	} // run

} // end class


