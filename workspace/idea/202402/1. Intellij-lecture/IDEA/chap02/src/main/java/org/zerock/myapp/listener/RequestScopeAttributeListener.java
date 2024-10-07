package org.zerock.myapp.listener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

//=================================================
// 1. For Spring Boot 2.7.x
//=================================================
//import javax.servlet.ServletRequestAttributeEvent;
//import javax.servlet.ServletRequestAttributeListener;
//import javax.servlet.annotation.WebListener;

//=================================================
// 2. For Spring Boot 3.1.x
//=================================================
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.annotation.WebListener;


@Log4j2
@NoArgsConstructor

@WebListener
public class RequestScopeAttributeListener implements ServletRequestAttributeListener {



	@Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
    	if(
			event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")
		) {
    		return;
    	} // if

		log.trace("---------------------------------------");
    	log.trace("* attributeRemoved(event) invoked.");
		log.trace("---------------------------------------");
    	
    	String name = event.getName();
    	Object value = event.getValue();
    	
    	log.info("\t+ name: {}", name);
    	log.info("\t+ value: {}", value);
		log.info("\t+ type: {}", value.getClass().getName());
    } // attributeRemoved


	@Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
    	if(
			event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")
		) {
    		return;
    	} // if

		log.trace("---------------------------------------");
    	log.trace("* attributeAdded(event) invoked.");
		log.trace("---------------------------------------");
    	
    	String name = event.getName();
    	Object value = event.getValue();

		log.info("\t+ name: {}", name);
		log.info("\t+ value: {}", value);
		log.info("\t+ type: {}", value.getClass().getName());
    } // attributeAdded


	@Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
    	if(
			event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")
		) {
    		return;
    	} // if

		log.trace("---------------------------------------");
    	log.trace("* attributeReplaced(event) invoked.");
		log.trace("---------------------------------------");
    	
    	String name = event.getName();
    	Object value = event.getValue();

		log.info("\t+ name: {}", name);
		log.info("\t+ value: {}", value);
		log.info("\t+ type: {}", value.getClass().getName());
    } // attributeReplaced
	
} // end class
