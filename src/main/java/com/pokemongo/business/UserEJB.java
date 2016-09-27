package com.pokemongo.business;

import com.pokemongo.services.UserService;
import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

@Stateless
public class UserEJB implements UserHandler {

    @EJB
    private UserService userService;
    
    private static final Logger logger = LogManager.getLogger(UserEJB.class);

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
    
    @Override
    public boolean logIn(User user) {
        if (!isDuplicate(user)) {
            saveUser(user);
        }
    
        return setLoggedInUser(user);
    }
    
    private boolean setLoggedInUser(User loggedInUser) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        
        User sessionUser = fetchUserByEmail(loggedInUser.getEmail());
        
        sessionMap.put("loggedInUser", sessionUser);
        
        logger.info("Logged in user: " + sessionMap.get("loggedInUser"));
    
        return true;
    }
    
}