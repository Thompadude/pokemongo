package com.pokemongo.soap;

import com.pokemongo.models.PokemonData;
import com.pokemongo.services.PokemonDataService;

import javax.ejb.EJB;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public class PokemonSoapProvider {

    @EJB
    private PokemonDataService pokemonDataService;

    @WebResult(name = "Pokemon")
    public List<PokemonData> getAllPokemon() {
        return pokemonDataService.fetchAllPokemonData();
    }

    @WebResult(name = "Pokemon")
    public PokemonData getPokemon(@WebParam(name = "pokedex") int pokedexNr) {
        return pokemonDataService.fetchPokemonData(pokedexNr);
    }



}