package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.PokemonDataHandler;
import com.pokemongo.models.PokemonData;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "pokemonDataController")
@SessionScoped
public class PokemonDataController implements Serializable {

    private static final long serialVersionUID = 6380435607133177451L;

    private Integer pokedexNumber;
    private String name;
    private String imageUrl;
    private String spriteUrl;
    private List<PokemonData> pokemonDataList;
    @EJB
    private PokemonDataHandler pokemonDataHandler;

    @PostConstruct
    public void init() {
        pokemonDataList = pokemonDataHandler.fetchAllPokemonData();
    }

    // TODO test method - remove later!
    public void selectedPokemonTEST() {
        PokemonData pokemonData = pokemonDataHandler.fetchPokemonDataByPokedexNumber(pokedexNumber);
        System.out.println(pokemonData);
    }

    /* Getters and Setters */

    public Integer getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(Integer pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    public List<PokemonData> getPokemonDataList() {
        return pokemonDataList;
    }

    public void setPokemonDataList(List<PokemonData> pokemonDataList) {
        this.pokemonDataList = pokemonDataList;
    }

    public PokemonDataHandler getPokemonDataHandler() {
        return pokemonDataHandler;
    }

    public void setPokemonDataHandler(PokemonDataHandler pokemonDataHandler) {
        this.pokemonDataHandler = pokemonDataHandler;
    }

}