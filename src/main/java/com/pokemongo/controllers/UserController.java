package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.models.Pokemon;
import com.pokemongo.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named(value = "userController")
@ManagedBean
@SessionScoped
public class UserController implements Serializable {

    private static final long serialVersionUID = 288653009259087195L;

    private boolean isUserLoggedIn;
    private String userName;
    private String email;
    private String tokenId;
    private Set<Pokemon> pokemons;
    @EJB
    private UserHandler userHandler;
    private static Logger logger = LogManager.getLogger(UserController.class);
    
    @PostConstruct
    public void init() {
        isUserLoggedIn = false;
        User currentUser = userHandler.getLoggedInUser();
        if (currentUser != null) {
            isUserLoggedIn = true;
            
            this.userName = currentUser.getUserName();
            this.email = currentUser.getEmail();
            this.pokemons = currentUser.getPokemons();
        }
    }
    
    public void logIn() {
        User user = new User(userName, email, tokenId);
        isUserLoggedIn = userHandler.logIn(user);
    }

    public void logOut() {
        userHandler.logOut();
        
        setIsUserLoggedIn(false);
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
        List<Pokemon> sortedPokemons = new ArrayList<Pokemon>();
        
        sortedPokemons.addAll(userHandler.getLoggedInUser().getPokemons());
        
        sortedPokemons.sort(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p1.getPokedexNumber() - p2.getPokedexNumber();
            }
        });
        
        return sortedPokemons;
    }

    public void setPokemons(Set<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}