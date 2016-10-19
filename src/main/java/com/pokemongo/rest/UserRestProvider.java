package com.pokemongo.rest;

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
    private RestLinkBuilder<UserRestProvider> restLinkBuilder = new RestLinkBuilder<>(UserRestProvider.class);
    
    @GET
    @Path("/{id}/pokemon")
    @Produces("application/json")
    public Response getAllUserPokemon(@PathParam("id") int id) {
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
        
        user.addRestLink(restLinkBuilder.getSelfLink(user.getId(), uriInfo));
        user.addRestLink(restLinkBuilder.getPokemonLink(user.getId(), uriInfo));
        
        return Response.status(Response.Status.OK).entity(user).build();
    }
    
}