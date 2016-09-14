package com.pokemongo.business;

import com.pokemongo.services.PokemonService;
import com.pokemongo.business.interfaces.LocalPokemon;
import com.pokemongo.models.Pokemon;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PokemonHandler implements LocalPokemon {

    @EJB
    private PokemonService pokemonService;

    @Override
    public void savePokemon(Pokemon pokemon) {
        pokemonService.savePokemon(pokemon);
    }

}