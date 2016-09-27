package com.pokemongo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * Serves as a model for 'hardcoded' pokemons.
 */
@Entity
@NamedQuery(name = "PokemonData.fetchAll", query = "SELECT p FROM PokemonData p")
public class PokemonData {

    @Id
    private Long id;
    private Integer pokedexNumber;
    private String name;
    private String imageUrl;
    private String spriteUrl;

    public Long getId() {
        return id;
    }

    public Integer getPokedexNumber() {
        return pokedexNumber;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

}