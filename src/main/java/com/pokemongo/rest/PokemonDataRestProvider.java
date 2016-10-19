package com.pokemongo.rest;

import com.pokemongo.models.PokemonData;
import com.pokemongo.services.PokemonDataService;
import com.pokemongo.utilities.RestLinkBuilder;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/pokemondata")
public class PokemonDataRestProvider {

    @EJB
    private PokemonDataService pokemonDataService;
    private RestLinkBuilder<PokemonDataRestProvider> pokemonDataLinkBuilder = new RestLinkBuilder<>(PokemonDataRestProvider.class);

    @GET
    @Produces("application/json")
    public Response getAllPokemon(@Context UriInfo uriInfo) {
        List<PokemonData> pokemonDataList = pokemonDataService.fetchAllPokemonData();

        if (pokemonDataList.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();
    
        for (PokemonData pokemon :
                pokemonDataList) {
            pokemon.addRestLink(pokemonDataLinkBuilder.getSelfLink(pokemon.getId(), uriInfo));
        }

        return Response.status(Response.Status.OK).entity(pokemonDataList).build();
    }

    @GET
    @Path("/{pokedexNr}")
    @Produces("application/json")
    public Response getPokemon(@PathParam("pokedexNr") Integer pokedexNr, @Context UriInfo uriInfo) {
        PokemonData pokemon = pokemonDataService.fetchPokemonDataByPokedexNumber(pokedexNr);

        if (pokemon == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        
        pokemon.addRestLink(pokemonDataLinkBuilder.getSelfLink(pokemon.getId(), uriInfo));

        return Response.status(Response.Status.OK).entity(pokemon).build();
    }

}