package com.pokemongo.business.interfaces;

import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.User;

import javax.ejb.Local;

@Local
public interface UserHandler {

    void saveUser(User user) throws DatabaseException;
    
    /**
     * @param user is the user who request the login
     * @return true when the user log in
     */
    boolean logIn(User user) throws DatabaseException;
    /**
     * @return false when the user log out
     */
    boolean logOut();

    User getLoggedInUser();

    boolean setLoggedInUser(User loggedInUser);

    User fetchUserByEmail(String email);
}