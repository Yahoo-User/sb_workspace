package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.zerock.myapp.util.JDBCConnectionManager;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;


@Log
@NoArgsConstructor

@Component
public class JDBCConnectionManagerRunner
	implements ApplicationRunner {
	
	@Setter(onMethod_= {@Autowired})
	private JDBCConnectionManager manager;

	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("run("+args+") invoked.");
		
		log.info("\t+ manager: " + manager);
	} // run
	
} // end class
