package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.LocalUser;
import com.pokemongo.models.Pokemon;
import com.pokemongo.models.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.Path;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Path("user")
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    private static final long serialVersionUID = 288653009259087195L;

    private String userName;
    private String email;
    private String tokenId;
    private List<Pokemon> pokemons;
    @EJB
    private LocalUser localUser;

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

    public void saveUserFromForm() {
        User user = new User(userName, email);
        if (!checkForDuplicateUsers(user)) {
            localUser.saveUser(user);
        }
    }

    public void fetchUser() {
        User user = localUser.fetchUser(1);
        System.out.println(user.getUserName());
        System.out.println(user.getId());
        System.out.println(user.getPokemons().get(0).getName());
    }

    public void saveUser() {
        User user = new User(userName, email, tokenId);
        if (!checkForDuplicateUsers(user)) {
            localUser.saveUser(user);
        }
        setSessionObject(user);
    }

    private boolean checkForDuplicateUsers(User loggedInUser) {
        boolean isDuplicatedUser = false;
        List<User> allUsers = localUser.fetchAllUsers();
        for (User user : allUsers) {
            if (user.getEmail() != null && user.getEmail().equals(loggedInUser.getEmail())) {
                isDuplicatedUser = true;
                break;
            }
        }
        return isDuplicatedUser;
    }

    private void setSessionObject(User loggedInUser) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("loggedInUser", loggedInUser);
    }

}