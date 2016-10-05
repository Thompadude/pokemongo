package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.PokemonDataHandler;
import com.pokemongo.models.PokemonData;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@Named(value = "pokemonDataController")
@SessionScoped
@ManagedBean
@ViewScoped
public class PokemonDataController implements Serializable {

    private static final long serialVersionUID = 6380435607133177451L;
    private String pokedexNumber;
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

    public void selectPokemonFromDropDown(){
        setPokedexNumber(pokedexNumber);
    }

    public void displayPokemon(){
        FacesMessage msg = new FacesMessage("Selected", pokedexNumber );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /* Getters and Setters */

    public String getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(String pokedexNumber) {
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