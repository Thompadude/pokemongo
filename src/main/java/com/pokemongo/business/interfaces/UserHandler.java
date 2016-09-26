package com.pokemongo.business.interfaces;

import com.pokemongo.models.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserHandler {

    void saveUser(User user);

    User fetchUser(long userId);

    List<User> fetchAllUsers();
    
    boolean isDuplicate(User user);
    
    User fetchUserByEmail(String tokenId);
    
    /**
     * Returns true when the user is logged in
     */
    boolean logIn(User user);
}