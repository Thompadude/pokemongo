package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    
    private static Logger logger = LogManager.getLogger(UserController.class);
    
    @EJB
    private UserHandler userHandler;
    private Part upload;
    
    public void uploadImage() {
    
        User currentUser = userHandler.getLoggedInUser();
    
        try {
            //Make the uploaded file into a stream
            InputStream inputStream = upload.getInputStream();
            
            // Get info to build the file name
            String id = currentUser.getId().toString();
            String extension = determineExtension(upload);

            // Create a file with the correct, calculated path
            File file = new File(System.getProperty("jboss.server.data.dir") + "/images");
            File finalFile = new File(file, id + extension);

            // Write the stream to disk
            Files.copy(inputStream, finalFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            //Save the filename with the user for later use
            currentUser.setUserImageName(id + extension);
            userHandler.saveUser(currentUser);

        } catch (IOException | DatabaseException e) {
            logger.error(e.getMessage());
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
