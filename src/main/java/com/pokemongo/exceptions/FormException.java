package com.pokemongo.exceptions;

public class FormException extends Exception {

    private static final long serialVersionUID = -2067015717984717887L;

    public FormException() {
        super("Form not properly filled out.");
    }

}