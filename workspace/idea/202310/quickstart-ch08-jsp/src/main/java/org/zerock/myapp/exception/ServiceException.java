package org.zerock.myapp.exception;


public class ServiceException extends Exception {


    public ServiceException(String message) {
        super(message);
    } // Constructor

    public ServiceException(Exception e) {
        super(e);
    } // Constructor

} // end class
