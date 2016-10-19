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
    public PokemonData fetchPokemonData(long id) {
        return pokemonDataService.fetchPokemonData(id);
    }

    @Override
    public PokemonData fetchPokemonDataByPokedexNumber(Integer pokedexNumber) {
        return pokemonDataService.fetchPokemonDataByPokedexNumber(pokedexNumber);
    }

    @Override
    public List<PokemonData> fetchAllPokemonData() {
        return pokemonDataService.fetchAllPokemonData();
    }

}