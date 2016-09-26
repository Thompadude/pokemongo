package com.pokemongo.business;

import com.pokemongo.business.interfaces.PokemonDataHandler;
import com.pokemongo.models.PokemonData;
import com.pokemongo.services.PokemonDataService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PokemonDataEJB implements PokemonDataHandler {

    @EJB
    private PokemonDataService pokemonDataService;

    @Override
    public List<PokemonData> fetchAllPokemonData() {
        return pokemonDataService.fetchAllPokemonData();
    }

}