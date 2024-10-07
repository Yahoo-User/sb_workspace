package org.zerock.myapp.exception;


public class ControllerException extends Exception {


    public ControllerException(String message) {
        super(message);
    } // Constructor

    public ControllerException(Exception e) {
        super(e);
    } // Constructor

} // end class
