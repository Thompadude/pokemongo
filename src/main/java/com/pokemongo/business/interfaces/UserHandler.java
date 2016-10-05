package com.pokemongo.business.interfaces;

import com.pokemongo.models.User;

import javax.ejb.Local;

@Local
public interface UserHandler {

    void saveUser(User user);

    /**
     * @param user is the user who request the login
     * @return true when the user log in
     */
    boolean logIn(User user);

    /**
     * @return false when the user log out
     */
    boolean logOut();

}