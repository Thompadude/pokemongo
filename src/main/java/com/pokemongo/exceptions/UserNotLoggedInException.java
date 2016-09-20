package com.pokemongo.exceptions;

public class UserNotLoggedInException extends Exception {

    private static final long serialVersionUID = 575286978745880021L;

    public UserNotLoggedInException() {
        super("User not logged in!");
    }

}