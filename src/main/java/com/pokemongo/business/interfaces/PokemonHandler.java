package com.pokemongo.business.interfaces;

import com.pokemongo.models.Pokemon;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PokemonHandler {

    void savePokemon(Pokemon pokemon);
    
    List<Pokemon> fetchAllPokemons();

}