package com.pokemongo.exceptions;

public class FormException extends Exception {

    public FormException() {
        super("Form not properly filled out.");
    }

}