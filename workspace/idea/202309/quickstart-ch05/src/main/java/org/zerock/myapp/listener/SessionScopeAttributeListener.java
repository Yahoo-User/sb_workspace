package org.zerock.myapp.listener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


@Log4j2
@NoArgsConstructor

@WebListener
public class SessionScopeAttributeListener implements HttpSessionAttributeListener {

    
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
    	if(
			event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")
		) {
    		return;
    	} // if
    	
    	log.trace("attributeAdded(event) invoked.");
    	
    	HttpSession session = event.getSession();
    	String name = event.getName();
    	Object value = event.getValue();
    	
    	log.info("\t+ session: " + session.getId());
    	log.info("\t+ name: " + name);
    	log.info("\t+ value: " + value);
		log.info("\t+ type: " + value.getClass().getName());
    } // attributeAdded


	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
    	if(
			event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")
		) {
    		return;
    	} // if
    	
    	log.trace("attributeRemoved(event) invoked.");
    	
    	HttpSession session = event.getSession();
    	String name = event.getName();
    	Object value = event.getValue();
    	
    	log.info("\t+ session: " + session.getId());
    	log.info("\t+ name: " + name);
    	log.info("\t+ value: " + value);
		log.info("\t+ type: " + value.getClass().getName());
    } // attributeRemoved


	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
    	if(
			event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")
		) {
    		return;
    	} // if
    	
    	log.trace("attributeReplaced(event) invoked.");
    	
    	HttpSession session = event.getSession();
    	String name = event.getName();
    	Object value = event.getValue();
    	
    	log.info("\t+ session: " + session.getId());
    	log.info("\t+ name: " + name);
    	log.info("\t+ value: " + value);
		log.info("\t+ type: " + value.getClass().getName());
    } // attributeReplaced
	
} // end class
