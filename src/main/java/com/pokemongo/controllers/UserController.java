package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.Pokemon;
import com.pokemongo.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    private static final long serialVersionUID = 288653009259087195L;

    private boolean isUserLoggedIn;
    private String userName;
    private String email;
    private String tokenId;
    private List<Pokemon> pokemons;
    private String profileImageUrl;
    @EJB
    private UserHandler userHandler;

    private static Logger logger = LogManager.getLogger(UserController.class);

    @PostConstruct
    public void init() {

        logger.debug("@PostConstruct executed");

        isUserLoggedIn = false;
    }

    public boolean logIn() throws DatabaseException {
        if (!isUserLoggedIn) {
            logger.debug("User is logging in");
            User user = new User(userName, email, tokenId);
            isUserLoggedIn = userHandler.logIn(user);
        }
        return isUserLoggedIn;
    }

    public boolean logOut() {
        logger.debug("User is logging out");
        setIsUserLoggedIn(userHandler.logOut());
        return isUserLoggedIn;
    }
    
    /* Getters and Setters */

    public boolean getIsUserLoggedIn() {
        return isUserLoggedIn;
    }

    public void setIsUserLoggedIn(boolean userLoggedIn) {
        isUserLoggedIn = userLoggedIn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public List<Pokemon> getPokemons() {
        // TODO ugly hack?
        this.pokemons = userHandler.getLoggedInUser().getPokemons();
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
    
    public String getProfileImageUrl() {
        profileImageUrl = "/images/" + userHandler.getLoggedInUser().getId() + ".png";
        return profileImageUrl;
    }
    
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}