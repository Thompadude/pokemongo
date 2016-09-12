package com.pokemongo.EJB.interfaces;

import javax.ejb.Local;

import com.pokemongo.model.User;

@Local
public interface LocalUser {

    void storeUser(User user);
    
    User getUser(long userId);
}