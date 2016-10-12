package com.pokemongo.business.interfaces;

import javax.ejb.Local;
import javax.servlet.http.Part;

@Local
public interface FileHandler {
    
    void uploadImage(Part upload);
}
