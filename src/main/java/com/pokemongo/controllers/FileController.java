package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.FileHandler;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.Serializable;

@Named(value = "fileController")
@SessionScoped
public class FileController implements Serializable{
    
    private static final long serialVersionUID = 876788777370374852L;
    
    @EJB
    private FileHandler fileHandler;
    private Part upload;
    
    public void uploadImage() {
        
        if (upload != null) {
            fileHandler.uploadImage(upload);
        }
    }
    
    public Part getUpload() {
        return upload;
    }
    
    public void setUpload(Part upload) {
        this.upload = upload;
    }
}
