package com.pokemongo.business;

import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.User;
import com.pokemongo.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

@Stateful
public class UserEJB implements UserHandler {

    @EJB
    private UserService userService;
    private Map<String, Object> sessionMap;

    private static final Logger logger = LogManager.getLogger(UserEJB.class);

    public UserEJB() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.sessionMap = externalContext.getSessionMap();
    }

    @Override
    public void saveUser(User user) throws DatabaseException {
        userService.saveUser(user);
    }
    
    private void setDefaultValues(User user) {
        //Default profile picture
        user.setUserImageName("default.png");
        //default team
        user.setTeam("none");
    }
    
    @Override
    public boolean logIn(User user) throws DatabaseException {
        if (!isDuplicate(user)) {
            setDefaultValues(user);
            saveUser(user);
        }
        return setLoggedInUser(user);
    }
    
    @Override
    public boolean logOut() {
        logger.debug("Logging out user {}", sessionMap.get("loggedInUser"));

        sessionMap.put("loggedInUser", null);

        logger.info("User logged out.");

        return false;
    }

    @Override
    public User getLoggedInUser() {
        return (User) sessionMap.get("loggedInUser");
    }

    @Override
    public boolean setLoggedInUser(User loggedInUser) {

        User sessionUser = fetchUserByEmail(loggedInUser.getEmail());

        sessionMap.put("loggedInUser", sessionUser);

        logger.info("Logged in user: " + sessionMap.get("loggedInUser"));

        return true;
    }

    private List<User> fetchAllUsers() {
        return userService.fetchAllUsers();
    }

    /**
     * Check if the user is already in the database.
     *
     * @param loggedInUser is the user to check.
     * @return true true if the user already exist, else false.
     */
    private boolean isDuplicate(User loggedInUser) {
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

    public User fetchUserByEmail(String email) {
        return userService.fetchUserByEmail(email);
    }
}