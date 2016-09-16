package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.models.Pokemon;
import com.pokemongo.models.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    private static final long serialVersionUID = 288653009259087195L;

    private String userName;
    private String email;
    private String tokenId;
    private List<Pokemon> pokemons;
    @EJB
    private UserHandler userHandler;

    public void fetchUser() {
        //TODO write dis
    }
    
    public void saveUser() {
        User user = new User(userName, email, tokenId);
        if (!userHandler.isDuplicate(user)) {
            userHandler.saveUser(user);
        }
        setLoggedInUser(user);
    }
    
    private void setLoggedInUser(User loggedInUser) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("loggedInUser", loggedInUser);
    }
    
    public User fetchLoggedInUser () {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        return (User) sessionMap.get("loggedInUser");
    }
    
    /* Getters and Setters */
    
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
        return pokemons;
    }
    
    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

}