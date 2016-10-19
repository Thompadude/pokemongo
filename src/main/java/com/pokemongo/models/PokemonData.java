package com.pokemongo.models;

import com.pokemongo.utilities.RestLink;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Serves as a model for 'hardcoded' pokemons.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "PokemonData.fetchAll", query = "SELECT p FROM PokemonData p"),
        @NamedQuery(name = "PokemonData.fetchPokemonDataByPokedexNumber", query = "SELECT p FROM PokemonData p WHERE p.pokedexNumber=:pokedexNumber")

})
@XmlRootElement(name = "Pokemon")
@XmlAccessorType(XmlAccessType.FIELD)
public class PokemonData implements Serializable {

    private static final long serialVersionUID = 6195823249633841641L;

    @Id
    private Long id;
    private Integer pokedexNumber;
    private String name;
    @Transient
    private List<RestLink> restLinks = new ArrayList<>();
    
    public Long getId() {
        return id;
    }

    public Integer getPokedexNumber() {
        return pokedexNumber;
    }

    public String getName() {
        return name;
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
    
    @Override
    public String toString() {
        return "PokemonData"
                + "\nID: " + id
                + "\nPokedex Number: " + pokedexNumber
                + "\nName: " + name;
    }

}