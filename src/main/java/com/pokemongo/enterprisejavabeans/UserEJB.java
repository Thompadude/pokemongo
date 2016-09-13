package com.pokemongo.enterprisejavabeans;

import com.pokemongo.dataaccessobjects.UserDAO;
import com.pokemongo.enterprisejavabeans.interfaces.LocalUser;
import com.pokemongo.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserEJB implements LocalUser {

    @EJB
    private UserDAO userDAO;

    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User fetchUser(long userId) {
        return userDAO.fetchUser(userId);
    }

    @Override
    public List<User> fetchAllUsers() {
        return userDAO.fetchAllUsers();
    }

}