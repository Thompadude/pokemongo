package com.pokemongo.soap;

import com.pokemongo.models.PokemonData;
import com.pokemongo.services.PokemonDataService;

import javax.ejb.EJB;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public class PokemonSoapProvider {

    @EJB
    private PokemonDataService pokemonDataService;

    @WebResult(name = "Pokemon")
    public List<PokemonData> getAllPokemon() {
        System.out.println("SOAP method called: getAllPokemon()");
        List<PokemonData> pokemonDataList = pokemonDataService.fetchAllPokemonData();
        return pokemonDataService.fetchAllPokemonData();
    }

}