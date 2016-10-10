package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.PokemonDataHandler;
import com.pokemongo.business.interfaces.PokemonHandler;
import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.Pokemon;
import com.pokemongo.models.PokemonData;
import com.pokemongo.models.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "pokemonController")
@SessionScoped
public class PokemonController implements Serializable {

    private static final long serialVersionUID = -4300941170573356047L;

    private String pokedexNumber;
    private Integer cp;
    private Integer hp;
    private String lng;
    private String lat;
    private List<PokemonData> pokemonDataList;

    @EJB
    private PokemonHandler pokemonHandler;
    @EJB
    private PokemonDataHandler pokemonDataHandler;
    @EJB
    private UserHandler userHandler;

    @PostConstruct
    public void init() {
        pokemonDataList = pokemonDataHandler.fetchAllPokemonData();
    }

    public void savePokemon() throws DatabaseException {
        // TODO add loggers and error handlers
        PokemonData pokemonData = pokemonDataHandler.fetchPokemonDataByPokedexNumber(pokedexNumber);
        Pokemon pokemon = new Pokemon(pokedexNumber, pokemonData.getName(), lng, lat, cp, hp);

        // TODO maybe move this logic?
        User user = userHandler.getLoggedInUser();
        pokemon.setOwner(user);

        pokemonHandler.savePokemon(pokemon);
        // TODO clear fields after adding pokemon
    }

    /* Getters and Setters */

    public String getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(String pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public List<PokemonData> getPokemonDataList() {
        return pokemonDataList;
    }

    public void setPokemonDataList(List<PokemonData> pokemonDataList) {
        this.pokemonDataList = pokemonDataList;
    }

}