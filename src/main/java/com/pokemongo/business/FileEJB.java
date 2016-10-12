package com.pokemongo.business;

import com.pokemongo.business.interfaces.FileHandler;
import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.exceptions.FileTypeException;
import com.pokemongo.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
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
            
            //Make sure it's an image
            if (!verifyImage(inputStream)) {
                throw new FileTypeException();
            }
            
            inputStream = upload.getInputStream();
            
            // Get info to build the file name
            String id = currentUser.getId().toString();
            String extension = determineExtension(upload);
            
            // Create a file with the correct, calculated path
            // NOTE: This makes the application WildFly only!
            File file = new File(System.getProperty("jboss.server.data.dir") + "/images");
            File finalFile = new File(file, id + extension);
            
            // Write the stream to disk
            Files.copy(inputStream, finalFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            //Save the filename with the user for later use
            currentUser.setUserImageName(id + extension);
            userHandler.saveUser(currentUser);
            
            logger.debug("Image uploaded successfully");
            
        } catch (IOException e) {
            logger.error("File write error! Check existence of data directory");
        } catch (DatabaseException e) {
            logger.error("Error saving picture to user!");
        } catch (FileTypeException e) {
            logger.warn("Uploaded file is not an image");
        }
        
    }
    
    private boolean verifyImage(InputStream image) {
        logger.debug("Verifying image...");
        try {
            BufferedImage testImage = ImageIO.read(image);
            if (testImage != null) {
                logger.debug("Uploaded file is an image");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private String determineExtension(Part upload) {
        switch (upload.getContentType()) {
            case "image/png":
                return ".png";
            case "image/jpeg":
                return ".jpg";
            case "image/gif":
                return ".gif";
            case "image/bmp":
                return ".bmp";
            default:
                return null;
        }
    }
}
