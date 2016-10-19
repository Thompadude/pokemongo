package com.pokemongo.utilities;

import com.pokemongo.models.Post;
import com.pokemongo.rest.UserRestProvider;

import javax.ws.rs.core.UriInfo;

public class RestLinkBuilder<T> {
    
    private Class<T> t;
    
    public RestLinkBuilder(Class<T> t) {
        this.t = t;
    }
    
    public RestLink getSelfLink(Long id, UriInfo uriInfo) {
        String uri = uriInfo.getBaseUriBuilder()
                .path(t)
                .path(id.toString())
                .build()
                .toString();
        return new RestLink(uri, "Self");
    }
    
    public RestLink getAuthorLink(Post post, UriInfo uriInfo) {
        String uri = uriInfo.getBaseUriBuilder()
                .path(UserRestProvider.class)
                .path(post.getAuthor().getId().toString())
                .build()
                .toString();
        return new RestLink(uri, "Author");
    }
    
    public RestLink getPokemonLink(Long id, UriInfo uriInfo) {
        String uri = uriInfo.getBaseUriBuilder()
                .path(UserRestProvider.class)
                .path(id.toString())
                .path("pokemon")
                .build()
                .toString();
        return new RestLink(uri, "Pokemon");
    }
}
