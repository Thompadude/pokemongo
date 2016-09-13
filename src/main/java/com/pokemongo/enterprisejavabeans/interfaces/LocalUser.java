package com.pokemongo.enterprisejavabeans.interfaces;

import com.pokemongo.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface LocalUser {

    void saveUser(User user);

    User fetchUser(long userId);

    List<User> fetchAllUsers();

}