package org.zerock.myapp.servlet.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebListener
public class ApplicationScopeAttributeListener
	implements ServletContextAttributeListener {

    
	
    public void attributeAdded(ServletContextAttributeEvent event) {
    	if(
			event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")
		) {
    		return;
    	} // if
    	
    	log.trace("attributeAdded(event) invoked.");
    	
    	String name = event.getName();
    	Object value = event.getValue();
    	
    	log.info("\t+ name: " + name);
    	log.info("\t+ value: " + value);
		log.info("\t+ type: " + value.getClass().getName());
    } // attributeAdded
    

	public void attributeRemoved(ServletContextAttributeEvent event) {
    	if(
			event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")
		) {
    		return;
    	} // if
    	
    	log.trace("attributeRemoved(event) invoked.");
    	
    	String name = event.getName();
    	Object value = event.getValue();
    	
    	log.info("\t+ name: " + name);
    	log.info("\t+ value: " + value);
		log.info("\t+ type: " + value.getClass().getName());
    } // attributeRemoved
	

	public void attributeReplaced(ServletContextAttributeEvent event) {
    	if(
			event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")
		) {
    		return;
    	} // if
    	
    	log.trace("attributeReplaced(event) invoked.");
    	
    	String name = event.getName();
    	Object value = event.getValue();
    	
    	log.info("\t+ name: " + name);
    	log.info("\t+ value: " + value);
		log.info("\t+ type: " + value.getClass().getName());
    } // attributeReplaced
	
} // end class
