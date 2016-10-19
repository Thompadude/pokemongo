package com.pokemongo.models;

import com.pokemongo.utilities.RestLink;

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
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private Set<Pokemon> pokemons;
    private String userImageName;
    private String team;
    @Transient
    private List<RestLink> restLinks = new ArrayList<>();

    public User(String userName, String email, String tokenId) {
        this.userName = userName;
        this.email = email;
        team = "none";
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
                return p1.getPokedexNumber() - p2.getPokedexNumber();
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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
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
    
    public List<RestLink> getRestLinks() {
        return restLinks;
    }
    
    public void setRestLinks(List<RestLink> restLinks) {
        this.restLinks = restLinks;
    }
    
    public void addRestLink(RestLink restLink) {
        this.restLinks.add(restLink);
    }
}