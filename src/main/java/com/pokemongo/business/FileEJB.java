package com.pokemongo.business;

import com.pokemongo.business.interfaces.FileHandler;
import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Stateful
public class FileEJB implements FileHandler {
    
    @EJB
    UserHandler userHandler;
    
    private static Logger logger = LogManager.getLogger(FileEJB.class);
    
    @Override
    public void uploadImage(Part upload) {
        
        User currentUser = userHandler.getLoggedInUser();
        
        logger.debug("Uploading image to user " + currentUser);
        
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
}
