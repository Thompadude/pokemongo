package com.pokemongo.utilities;

import com.pokemongo.models.Post;
import com.pokemongo.rest.UserRestProvider;

import javax.ws.rs.core.UriInfo;

public class LinkBuilder<T> {
    
    private Class<T> t;
    
    public LinkBuilder(Class<T> t) {
        this.t = t;
    }
    
    public Link getSelfLink(Long id, UriInfo uriInfo) {
        String uri = uriInfo.getBaseUriBuilder()
                .path(t)
                .path(id.toString())
                .build()
                .toString();
        return new Link(uri, "Self");
    }
    
    public Link getAuthorLink(Post post, UriInfo uriInfo) {
        String uri = uriInfo.getBaseUriBuilder()
                .path(UserRestProvider.class)
                .path(post.getAuthor().getId().toString())
                .build()
                .toString();
        return new Link(uri, "Author");
    }
}
