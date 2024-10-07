package org.zerock.myapp.exception;


public class BoardException extends RuntimeException {


    public BoardException(String message) {
        super(message);
    } // Constructor1

    public BoardException(Exception e) {
        super(e);
    } // Constructor2

} // end class
