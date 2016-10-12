package com.pokemongo.exceptions;

public class UserException extends Exception {

    private static final long serialVersionUID = 575286978745880021L;

    public UserException(String message) {
        super(message);
    }

}