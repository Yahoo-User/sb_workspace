package org.zerock.myapp.exception;


public class BoardNotFoundException extends BoardException {


    public BoardNotFoundException(String message) {
        super(message);
    } // Constructor1

    public BoardNotFoundException(Exception e) {
        super(e);
    } // Constructor2

} // end class
