package com.pokemongo.business;

import com.pokemongo.services.PokemonService;
import com.pokemongo.business.interfaces.PokemonHandler;
import com.pokemongo.models.Pokemon;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PokemonEJB implements PokemonHandler {

    @EJB
    private PokemonService pokemonService;

    @Override
    public void savePokemon(Pokemon pokemon) {
        pokemonService.savePokemon(pokemon);
    }

}