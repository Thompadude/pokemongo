package com.pokemongo.rest;

import com.pokemongo.models.Pokemon;
import com.pokemongo.models.User;
import com.pokemongo.services.UserService;
import com.pokemongo.utilities.RestLinkBuilder;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/user")
public class UserRestProvider {

    @EJB
    private UserService userService;
    private RestLinkBuilder<UserRestProvider> userRestLinkBuilder = new RestLinkBuilder<>(UserRestProvider.class);
    private RestLinkBuilder<PokemonRestProvider> pokemonRestLinkBuilder = new RestLinkBuilder<>(PokemonRestProvider.class);

    @GET
    @Path("/{id}/pokemon")
    @Produces("application/json")
    public Response getAllUserPokemon(@PathParam("id") int id, @Context UriInfo uriInfo) {
        User user = userService.fetchUser(id);

        if (!(user == null) && !user.getPokemons().isEmpty()) {
            setPokemonLinks(uriInfo, user);
            for (Pokemon pokemon : user.getPokemons()) {
                pokemon.addRestLink(userRestLinkBuilder.getUserLink(pokemon, uriInfo, "Owner"));
            }
            return Response.status(Response.Status.OK).entity(user.getPokemons()).build();
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getUser(@PathParam("id") int id, @Context UriInfo uriInfo) {
        User user = userService.fetchUser(id);

        if (!(user == null)) {
            user.addRestLink(userRestLinkBuilder.getSelfLink(user.getId(), uriInfo));
            user.addRestLink(userRestLinkBuilder.getPokemonLink(user.getId(), uriInfo));
            setPokemonLinks(uriInfo, user);

            return Response.status(Response.Status.OK).entity(user).build();
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    private void setPokemonLinks(@Context UriInfo uriInfo, User user) {
        for (Pokemon pokemon :
                user.getPokemons()) {
            pokemon.addRestLink(pokemonRestLinkBuilder.getSelfLink(pokemon.getId(), uriInfo));
        }
    }

}