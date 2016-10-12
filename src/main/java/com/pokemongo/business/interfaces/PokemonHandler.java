package com.pokemongo.business.interfaces;

import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.exceptions.FormException;
import com.pokemongo.exceptions.UserNotLoggedInException;
import com.pokemongo.models.Pokemon;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PokemonHandler {

    Pokemon savePokemon(Pokemon pokemon) throws DatabaseException, UserNotLoggedInException, FormException;

    List<Pokemon> fetchAllPokemons();

    List<Pokemon> fetchPokemonByOwnerId(Long id);

}