package com.pokemongo.business.interfaces;

import com.pokemongo.exceptions.FileTypeException;

import javax.ejb.Local;
import javax.servlet.http.Part;

@Local
public interface FileHandler {
    
    void uploadImage(Part upload) throws FileTypeException;
}
