package com.pokemongo.backingbeans;

import com.pokemongo.EJB.interfaces.LocalUser;
import com.pokemongo.model.Pokemon;
import com.pokemongo.model.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

@Path("user")
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

    public void getUser() {
        User user = localUser.getUser(1);
        System.out.println(user.getUserName());
        System.out.println(user.getId());
        System.out.println(user.getPokemons().get(0).getName());
    }

    @POST
    @Path("login")
    @Produces("application/json")
    public Response saved(User user) {
        // Get list of all users in database.
        List<User> allUsers = localUser.getAllUsers();
        for (User u : allUsers) {
            // Check if user already exist in the database.
            if (u.getEmail() != null && u.getEmail().equals(user.getEmail())) {
                System.out.println("User already in database!");
                return Response.status(Response.Status.FOUND).build();
            }
        }
        localUser.storeUser(user);
        return Response.status(Response.Status.CREATED).build();
    }

}