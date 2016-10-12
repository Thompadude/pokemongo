package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.FileHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.Serializable;

@Named(value = "fileController")
@SessionScoped
public class FileController implements Serializable{
    
    private static final long serialVersionUID = 876788777370374852L;
    
    private static Logger logger = LogManager.getLogger(FileController.class);
    
    
    @EJB
    private FileHandler fileHandler;
    private Part upload;
    
    public void uploadImage() {
        
        logger.debug("Uploading image");
        
        if (upload != null) {
            fileHandler.uploadImage(upload);
        } else {
            logger.warn("No image for upload");
        }
    }
    
    public Part getUpload() {
        return upload;
    }
    
    public void setUpload(Part upload) {
        this.upload = upload;
    }
}
