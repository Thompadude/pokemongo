package com.pokemongo.controllers;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;

@Named(value = "fileController")
@SessionScoped
public class FileController implements Serializable{
    
    private static final long serialVersionUID = 876788777370374852L;
    
    private Part file;
    
    public void uploadImage() {
    
        try {
            InputStream input = file.getInputStream();
            Files.copy(input, new File("/Users/Tobias/test.png").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Part getFile() {
        return file;
    }
    
    public void setFile(Part file) {
        this.file = file;
    }
}
