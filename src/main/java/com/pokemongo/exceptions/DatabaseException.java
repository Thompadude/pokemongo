package com.pokemongo.exceptions;

public class DatabaseException extends Exception {

    private static final long serialVersionUID = -2789490247942189408L;

    public DatabaseException(String message) {
        super(message);
    }

}