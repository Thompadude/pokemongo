package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.models.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Named(value = "fileController")
@SessionScoped
public class FileController implements Serializable{
    
    private static final long serialVersionUID = 876788777370374852L;
    
    @EJB
    private UserHandler userHandler;
    private Part upload;
    
    public void uploadImage() {
    
        User currentUser = userHandler.getLoggedInUser();
    
        try {
            InputStream inputStream = upload.getInputStream();
            
            String extension = determineExtension(upload);

            File filePath = new File(System.getProperty("jboss.server.data.dir") + "/images");

            File fileName = new File(filePath, currentUser.getId().toString() + extension);

            Files.copy(inputStream, fileName.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String determineExtension(Part upload) {
        switch (upload.getContentType()) {
            case "image/png":
                return ".png";
            case "image/jpeg":
                return ".jpg";
            case "image/gif":
                return ".gif";
            default:
                return null;
        }
    }
    
    private Path autoGeneratePath(Part file) {
        
        return null;
    }
    
    public Part getUpload() {
        return upload;
    }
    
    public void setUpload(Part upload) {
        this.upload = upload;
    }
}
