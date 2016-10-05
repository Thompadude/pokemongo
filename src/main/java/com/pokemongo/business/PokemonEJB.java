package com.pokemongo.business;

import com.pokemongo.business.interfaces.PokemonHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.Pokemon;
import com.pokemongo.services.PokemonService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PokemonEJB implements PokemonHandler {

    @EJB
    private PokemonService pokemonService;

    @Override
    public Pokemon savePokemon(Pokemon pokemon) throws DatabaseException {
        return pokemonService.savePokemon(pokemon);
    }

}