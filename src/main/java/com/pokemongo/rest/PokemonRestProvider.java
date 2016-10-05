package com.pokemongo.rest;

import com.pokemongo.models.PokemonData;
import com.pokemongo.services.PokemonDataService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/pokemon")
public class PokemonRestProvider {
    
    @EJB
    private PokemonDataService pokemonDataService;
    
    @GET
    @Produces("application/json")
    public List<PokemonData> getAllPokemon() {
        return pokemonDataService.fetchAllPokemonData();
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public PokemonData getPokemon(@PathParam("id") int pokedexNr) {
        return pokemonDataService.fetchPokemonDataByPokedexNumber(pokedexNr);
    }
}
