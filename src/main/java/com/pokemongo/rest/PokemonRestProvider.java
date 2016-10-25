package com.pokemongo.rest;

import com.pokemongo.models.Pokemon;
import com.pokemongo.services.PokemonService;
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

@Path("/pokemon")
public class PokemonRestProvider {

    @EJB
    private PokemonService pokemonService;
    private RestLinkBuilder<PokemonRestProvider> pokemonRestLinkBuilder = new RestLinkBuilder<>(PokemonRestProvider.class);

    @GET
    @Produces("application/json")
    public Response getAllPokemon() {
        List<Pokemon> pokemonList = pokemonService.fetchAllPokemons();

        if (pokemonList.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.status(Response.Status.OK).entity(pokemonList).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getPokemonById(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        Pokemon pokemon = pokemonService.fetchPokemonById(id);

        if (pokemon == null)
            return Response.status(Response.Status.NO_CONTENT).build();

        pokemon.addRestLink(pokemonRestLinkBuilder.getSelfLink(pokemon.getId(), uriInfo));
        pokemon.addRestLink(pokemonRestLinkBuilder.getUserLink(pokemon, uriInfo, "Owner"));

        return Response.status(Response.Status.OK).entity(pokemon).build();
    }

    @GET
    @Path("/newest")
    @Produces("application/json")
    public Response getNewestPokemon() {
        List<Pokemon> pokemonList = pokemonService.fetchNewestPokemon();

        if (pokemonList.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.status(Response.Status.OK).entity(pokemonList).build();
    }

}