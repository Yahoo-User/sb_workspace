package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;

/**
 * Hello world!
 *
 */

@Log4j2
public class App 
{
    public static void main( String[] args )
    {
//    	log.info( "Hello World!" );
    	log.trace("trace");
    	log.debug("debug");
    	log.info("info");
    	log.warn("warn");
    	log.error("error");
    }
    
} // end class
