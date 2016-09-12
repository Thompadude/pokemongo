package com.pokemongo.backingbeans;

import com.pokemongo.EJB.interfaces.LocalUser;
import com.pokemongo.model.Pokemon;
import com.pokemongo.model.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

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

    public LocalUser getLocalUser() {
        return localUser;
    }

    public void setLocalUser(LocalUser localUser) {
        this.localUser = localUser;
    }

    public void saveUser() {
        User user = new User(userName, email);
        localUser.storeUser(user);
    }

}