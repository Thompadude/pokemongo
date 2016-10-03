package com.pokemongo.business.interfaces;

import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.Pokemon;

import javax.ejb.Local;

@Local
public interface PokemonHandler {

    Pokemon savePokemon(Pokemon pokemon) throws DatabaseException;

}