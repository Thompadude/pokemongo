package com.pokemongo.rest;

import com.pokemongo.models.Pokemon;
import com.pokemongo.services.PokemonService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/pokemon")
public class PokemonClient {
    
    @EJB
    private PokemonService pokemonService;
    
    @GET
    @Produces("application/json")
    public List<Pokemon> test() {
        return pokemonService.fetchAllPokemons();
    }
}
