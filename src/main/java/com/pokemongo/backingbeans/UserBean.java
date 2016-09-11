package com.pokemongo.backingbeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.OneToMany;

import com.pokemongo.EJB.UserEJB;
import com.pokemongo.EJB.interfaces.LocalUser;
import com.pokemongo.model.Pokemon;
import com.pokemongo.model.User;

@Named(value="userBean")
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
        User user = new User();
        user.setUserName("New Awesome User");
        user.setEmail("hemligt");
        user.setPokemons(null);
        user.setTokenId("Google TOKEN!!!");
        localUser.storeUser(user);
    }

}
