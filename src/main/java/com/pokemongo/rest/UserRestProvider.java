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
import java.util.List;

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
        
        if (user == null || user.getPokemons().isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();
    
        
        
        return Response.status(Response.Status.OK).entity(user.getPokemons()).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getUser(@PathParam("id") int id, @Context UriInfo uriInfo) {
        User user = userService.fetchUser(id);
        
        if (user == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        
        user.addRestLink(userRestLinkBuilder.getSelfLink(user.getId(), uriInfo));
        user.addRestLink(userRestLinkBuilder.getPokemonLink(user.getId(), uriInfo));
    
        for (Pokemon pokemon :
                user.getPokemons()) {
            pokemon.addRestLink(pokemonRestLinkBuilder.getSelfLink(pokemon.getId(), uriInfo));
        }
        
        return Response.status(Response.Status.OK).entity(user).build();
    }
    
}