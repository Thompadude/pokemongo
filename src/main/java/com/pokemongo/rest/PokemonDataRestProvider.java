package com.pokemongo.rest;

import com.pokemongo.models.PokemonData;
import com.pokemongo.services.PokemonDataService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pokemondata")
public class PokemonDataRestProvider {

    @EJB
    private PokemonDataService pokemonDataService;

    @GET
    @Produces("application/json")
    public Response getAllPokemon() {
        List<PokemonData> pokemonDataList = pokemonDataService.fetchAllPokemonData();

        if (pokemonDataList.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.status(Response.Status.OK).entity(pokemonDataList).build();
    }

    @GET
    @Path("/{pokedexNr}")
    @Produces("application/json")
    public Response getPokemon(@PathParam("pokedexNr") String pokedexNr) {
        PokemonData pokemon = pokemonDataService.fetchPokemonDataByPokedexNumber(pokedexNr);

        if (pokemon == null)
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.status(Response.Status.OK).entity(pokemon).build();
    }

}