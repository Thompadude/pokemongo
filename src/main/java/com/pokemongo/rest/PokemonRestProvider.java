package com.pokemongo.rest;

import com.pokemongo.models.PokemonData;
import com.pokemongo.services.PokemonDataService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/pokemon")
public class PokemonRestProvider {

    @EJB
    private PokemonDataService pokemonDataService;

    @GET
    @Produces("application/json")
    public Response getAllPokemon() {
        return Response.status(Response.Status.OK)
                .entity(pokemonDataService.fetchAllPokemonData())
                .build();
    }

    @GET
    @Path("/{pokedexNr}")
    @Produces("application/json")
    public Response getPokemon(@PathParam("pokedexNr") int pokedexNr) {
        PokemonData pokemon = pokemonDataService.fetchPokemonDataByPokedexNumber(pokedexNr);

        if (pokemon == null)
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.status(Response.Status.OK).entity(pokemon).build();
    }

}