package com.pokemongo.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.fetchAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.fetchByEmail", query = "SELECT u FROM User u WHERE u.email=:email")
})
public class User implements Serializable {

    private static final long serialVersionUID = -5911276687433073942L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String tokenId;
    @OneToMany(mappedBy = "owner")
    private List<Pokemon> pokemons;

    public User(String userName, String email, String tokenId) {
        this.userName = userName;
        this.email = email;
        this.tokenId = tokenId;
    }

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public User() {
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public Long getId() {
        return id;
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

    // TODO For testing purposes. Remove later!
    public void setId(Long id) {
        this.id = id;
    }

}