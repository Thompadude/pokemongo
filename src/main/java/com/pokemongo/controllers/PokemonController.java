package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.PokemonHandler;
import com.pokemongo.models.Pokemon;
import com.pokemongo.models.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "pokemonController")
@SessionScoped
public class PokemonController implements Serializable {

    private static final long serialVersionUID = -4300941170573356047L;

    private String pokedexNumber;
    private String name;
    private Integer cp;
    private Integer hp;
    private User owner;
    @EJB
    private PokemonHandler pokemonHandler;


    public void savePokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setPokedexNumber(pokedexNumber);
        pokemon.setName(name);
        pokemon.setCombatPower(cp);
        pokemon.setHealthPoints(hp);
        pokemon.setOwner(owner);


        pokemonHandler.savePokemon(pokemon);
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
    
    public User getOwner() {
        return owner;
    }
    
    public void setOwner(User owner) {
        this.owner = owner;
    }

}
