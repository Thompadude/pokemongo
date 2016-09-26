package com.pokemongo.business.interfaces;

import com.pokemongo.models.PokemonData;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PokemonDataHandler {

    public List<PokemonData> fetchAllPokemonData();

}