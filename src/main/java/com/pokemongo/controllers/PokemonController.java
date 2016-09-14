package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.LocalPokemon;
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

    private Integer pokedexNumber;
    private String name;
    private Integer cp;
    private Integer hp;
    private User owner;
    @EJB
    private LocalPokemon localPokemon;

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

    public void savePokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Pokemongo");
        pokemon.setCombatPower(12);
        pokemon.setHealthPoints(234);
        pokemon.setPokedexNumber(134124);

        User user = new User();
        user.setId(1L);

        pokemon.setOwner(user);

        localPokemon.savePokemon(pokemon);
    }

}
