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
    public Response getAllPokemon(@Context UriInfo uriInfo) {
        List<Pokemon> pokemonList = pokemonService.fetchAllPokemons();

        if (setPokemonListRestLinks(uriInfo, pokemonList))
            return Response.status(Response.Status.OK).entity(pokemonList).build();

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getPokemonById(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        Pokemon pokemon = pokemonService.fetchPokemonById(id);

        if (!(pokemon == null)) {
            pokemon.addRestLink(pokemonRestLinkBuilder.getSelfLink(pokemon.getId(), uriInfo));
            pokemon.addRestLink(pokemonRestLinkBuilder.getUserLink(pokemon, uriInfo, "Owner"));

            return Response.status(Response.Status.OK).entity(pokemon).build();
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/newest")
    @Produces("application/json")
    public Response getNewestPokemon(@Context UriInfo uriInfo) {
        List<Pokemon> pokemonList = pokemonService.fetchNewestPokemon();

        if (setPokemonListRestLinks(uriInfo, pokemonList))
            return Response.status(Response.Status.OK).entity(pokemonList).build();

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    private boolean setPokemonListRestLinks(@Context UriInfo uriInfo, List<Pokemon> pokemonList) {
        if (!pokemonList.isEmpty()) {
            for (Pokemon pokemon : pokemonList) {
                pokemon.addRestLink(pokemonRestLinkBuilder.getSelfLink(pokemon.getId(), uriInfo));
                pokemon.addRestLink(pokemonRestLinkBuilder.getUserLink(pokemon, uriInfo, "Owner"));
            }
            return true;
        }
        return false;
    }

}