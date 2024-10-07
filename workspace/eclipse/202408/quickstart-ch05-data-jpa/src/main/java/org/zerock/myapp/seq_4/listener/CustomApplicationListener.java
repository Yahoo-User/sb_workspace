package org.zerock.myapp.seq_4.listener;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletContext;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component("customApplicationListener")
public class CustomApplicationListener
	implements ApplicationListener<ApplicationEvent> {
	@Autowired(required = false)
	private ServletContext sc;
	
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		log.trace("onApplicationEvent({}) invoked.", event);
		
		if(event instanceof ApplicationReadyEvent) {
			log.info("\t+ this.sc: {}", this.sc);
			
			sc.setAttribute("NAME", new Object());
			sc.setAttribute("NAME", new ArrayList<>());
			sc.removeAttribute("NAME");
		} // if
	} // onApplicationEvent

} // end class
