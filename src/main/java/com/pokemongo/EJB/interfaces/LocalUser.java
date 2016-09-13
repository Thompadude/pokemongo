package com.pokemongo.EJB.interfaces;

import com.pokemongo.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface LocalUser {

    void storeUser(User user);

    User getUser(long userId);

    List<User> getAllUsers();

}