package com.pokemongo.rest;

import com.pokemongo.models.Pokemon;
import com.pokemongo.services.PokemonService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pokemon")
public class PokemonRestProvider {

    @EJB
    private PokemonService pokemonService;

    @GET
    @Produces("application/json")
    public Response getPokemonService() {
        List<Pokemon> pokemonList = pokemonService.fetchAllPokemons();

        if (pokemonList.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.status(Response.Status.OK).entity(pokemonList).build();
    }

}