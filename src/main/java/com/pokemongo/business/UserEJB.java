package com.pokemongo.business;

import com.pokemongo.services.UserService;
import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.models.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserEJB implements UserHandler {

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
    
    /**
     * Returns true if the user is already in the database
     */
    @Override
    public boolean isDuplicate(User loggedInUser) {
        boolean isDuplicatedUser = false;
        List<User> allUsers = fetchAllUsers();
        for (User user : allUsers) {
            if (user.getEmail() != null && user.getEmail().equals(loggedInUser.getEmail())) {
                isDuplicatedUser = true;
                break;
            }
        }
        return isDuplicatedUser;
    }
    
    @Override
    public User fetchUserByEmail(String email) {
        return userService.fetchUserByEmail(email);
    }
    
}