package com.pokemongo.exceptions;

public class FileTypeException extends Exception {
    
    public FileTypeException(String message) {
        super(message);
    }
    
    public FileTypeException() {
        super("File type error");
    }
}
