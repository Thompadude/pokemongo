package com.pokemongo.business.interfaces;

import com.pokemongo.models.PokemonData;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PokemonDataHandler {

    PokemonData fetchPokemonData(long id);

    PokemonData fetchPokemonDataByPokedexNumber(String pokedexNumber);

    List<PokemonData> fetchAllPokemonData();

}