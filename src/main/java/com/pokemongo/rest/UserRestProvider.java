package com.pokemongo.rest;

import com.pokemongo.models.User;
import com.pokemongo.services.UserService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserRestProvider {
    
    @EJB
    private UserService userService;
    
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
    public Response getUser(@PathParam("id") int id) {
        User user = userService.fetchUser(id);
        
        if (user == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        
        return Response.status(Response.Status.OK).entity(user).build();
    }
    
}