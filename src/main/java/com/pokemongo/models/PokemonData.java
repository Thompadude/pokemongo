package com.pokemongo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

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

    public Long getId() {
        return id;
    }

    public Integer getPokedexNumber() {
        return pokedexNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PokemonData"
                + "\nID: " + id
                + "\nPokedex Number: " + pokedexNumber
                + "\nName: " + name;
    }

}