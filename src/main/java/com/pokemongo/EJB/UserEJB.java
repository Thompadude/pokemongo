package com.pokemongo.EJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pokemongo.DAO.UserDAO;
import com.pokemongo.EJB.interfaces.LocalUser;
import com.pokemongo.model.User;

@Stateless
public class UserEJB implements LocalUser {

    @EJB
    private UserDAO userDAO;

    @Override
    public void storeUser(User user) {
        userDAO.storeUser(user);
    }
    
    @Override
    public User getUser(long userId) {
        return userDAO.getUser(userId);
    }
    
}