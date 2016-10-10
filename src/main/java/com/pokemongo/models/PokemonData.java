package com.pokemongo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Serves as a model for 'hardcoded' pokemons.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "PokemonData.fetchAll", query = "SELECT p FROM PokemonData p"),
        @NamedQuery(name = "PokemonData.fetchPokemonDataByPokedexNumber", query = "SELECT p FROM PokemonData p WHERE p.pokedexNumber=:pokedexNumber")

})
public class PokemonData {

    @Id
    private Long id;
    private String pokedexNumber;
    private String name;

    public Long getId() {
        return id;
    }

    public String getPokedexNumber() {
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