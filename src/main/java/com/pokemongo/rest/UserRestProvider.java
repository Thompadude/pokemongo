package com.pokemongo.rest;

import com.pokemongo.models.Pokemon;
import com.pokemongo.models.User;
import com.pokemongo.services.UserService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/user")
public class UserRestProvider {

    @EJB
    private UserService userService;

    @GET
    @Path("/{id}/pokemon")
    @Produces("application/json")
    public List<Pokemon> getAllUserPokemon(@PathParam("id") int id) {
        User user = userService.fetchUser(id);
        return user.getPokemons();
    }

}