package com.pokemongo.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private Set<Pokemon> pokemons;
    private String userImageName;

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
        // TODO maybe replace this sort with a query?
        List<Pokemon> pokemon = new ArrayList<Pokemon>();
        pokemon.addAll(pokemons);

        pokemon.sort(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return Integer.parseInt(p1.getPokedexNumber()) - Integer.parseInt(p2.getPokedexNumber());
            }
        });

        return pokemon;
    }

    public void setPokemons(Set<Pokemon> pokemons) {
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

    @Override
    public String toString() {
        return "[" + id + "] " + userName;
    }
    
    public String getUserImageName() {
        return userImageName;
    }
    
    public void setUserImageName(String userImageName) {
        this.userImageName = userImageName;
    }
}