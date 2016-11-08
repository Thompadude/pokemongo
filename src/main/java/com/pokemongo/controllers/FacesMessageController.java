package com.pokemongo.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Handles all the faces messages throughout the application.
 */
public class FacesMessageController {

    private static final Logger logger = LogManager.getLogger(FacesMessageController.class);

    public static void displayErrorMessage(String message) {
        logger.warn("Displaying error message: {}", message);
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    
    public static void displaySuccessMessage(String message) {
        logger.debug("Displaying message: {}", message);
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "", message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

}