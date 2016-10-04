package com.pokemongo.business.interfaces;

import com.pokemongo.models.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserHandler {

    void saveUser(User user);
    
    /**
     * Returns true when the user is logged in
     */
    boolean logIn(User user);
    
    void logOut();
}