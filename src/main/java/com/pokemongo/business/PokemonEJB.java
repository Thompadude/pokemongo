package com.pokemongo.business;

import com.pokemongo.business.interfaces.PokemonHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.Pokemon;
import com.pokemongo.services.PokemonService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PokemonEJB implements PokemonHandler {

    @EJB
    private PokemonService pokemonService;

    @Override
    public Pokemon savePokemon(Pokemon pokemon) throws DatabaseException {
        return pokemonService.savePokemon(pokemon);
    }

    @Override
    public List<Pokemon> fetchAllPokemons() {
        return pokemonService.fetchAllPokemons();
    }

}