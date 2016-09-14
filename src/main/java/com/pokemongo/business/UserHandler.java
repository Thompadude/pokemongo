package com.pokemongo.business;

import com.pokemongo.services.UserService;
import com.pokemongo.business.interfaces.LocalUser;
import com.pokemongo.models.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserHandler implements LocalUser {

    @EJB
    private UserService userService;

    @Override
    public void saveUser(User user) {
        userService.saveUser(user);
    }

    @Override
    public User fetchUser(long userId) {
        return userService.fetchUser(userId);
    }

    @Override
    public List<User> fetchAllUsers() {
        return userService.fetchAllUsers();
    }

}