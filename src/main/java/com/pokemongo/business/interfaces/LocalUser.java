package com.pokemongo.business.interfaces;

import com.pokemongo.models.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface LocalUser {

    void saveUser(User user);

    User fetchUser(long userId);

    List<User> fetchAllUsers();

}