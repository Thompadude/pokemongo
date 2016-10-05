package com.pokemongo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
public class PokemonClient {
    
    @GET
    @Produces("text/plain")
    public String test() {
        return "Hello";
    }
}
