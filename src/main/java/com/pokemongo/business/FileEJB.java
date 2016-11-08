package com.pokemongo.business;

import com.pokemongo.business.interfaces.FileHandler;
import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.controllers.PostController;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.exceptions.FileTypeException;
import com.pokemongo.models.User;
import com.pokemongo.services.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Stateful
public class FileEJB implements FileHandler {
    
    private static final int FILE_SIZE_LIMIT = 1024 * 1024; // 1Mb
    
    @EJB
    UserHandler userHandler;

    @Inject
    private PostController postController;
    @EJB
    private PostService postService;
    
    private static Logger logger = LogManager.getLogger(FileEJB.class);

    /**
     * Handles uploading of the user profile image.
     *
     * @param upload is the file the user want to upload.
     * @throws FileTypeException if the file is too large, not an image or if no file is chosen.
     */
    @Override
    public void uploadImage(Part upload) throws FileTypeException {
        
        if(upload != null) {
    
            User currentUser = userHandler.getLoggedInUser();
    
            logger.debug("Uploading image to user " + currentUser);
    
            try {
        
                //Make the uploaded file into a stream
                InputStream inputStream = upload.getInputStream();
                
                //Check the size
                if (!verifySize(inputStream)) {
                    throw new FileTypeException("File is too large.");
                }
    
                inputStream = upload.getInputStream();
        
                //Make sure it's an image
                if (!verifyImage(inputStream)) {
                    throw new FileTypeException("Uploaded file is not an image!");
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

                postController.setPosts(postService.fetchPostsWithoutParent());

                logger.debug("Image uploaded successfully");
        
            } catch (IOException e) {
                logger.error("File write error! Check existence of data directory");
            } catch (DatabaseException e) {
                logger.error("Error saving picture to user!");
            }
        } else {
            throw new FileTypeException("No file has been selected");
        }
        
    }

    /**
     * Verifies file size.
     *
     * @param inputStream is the input stream which we read the size from.
     * @return false if the file size is too large, else true.
     */
    private boolean verifySize(InputStream inputStream) {
        
        int size = 0;
    
        try {
            while(inputStream.read() != -1) {
                size ++;
                
                if (size > FILE_SIZE_LIMIT) {
                    logger.debug("File exceeds limit (" + FILE_SIZE_LIMIT + " bytes)");
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        logger.debug("Size of file: " + size);
        return true;
    }

    /**
     * Verifies that the file is an image.
     *
     * @param image is the image to verify.
     * @return true if the file is an image, else false.
     */
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


    /**
     * Determines the file extension.
     *
     * @param upload is the file to check.
     * @return the file extension.
     */
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
