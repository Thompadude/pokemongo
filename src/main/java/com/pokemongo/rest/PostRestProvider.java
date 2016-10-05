package com.pokemongo.rest;

import com.pokemongo.models.Post;
import com.pokemongo.services.PostService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/post")
public class PostRestProvider {

    @EJB
    PostService postService;

    @GET
    @Produces("application/json")
    public List<Post> getAllPosts() {
        return postService.fetchPostsWithoutParent();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Post getPostById(@PathParam("id") int id) {
        return postService.fetchPost(id);
    }

    @GET
    @Path("/search/{searchWord}")
    @Produces("application/json")
    public List<Post> getPostsByKeyword(@PathParam("searchWord") String searchWord) {
        return postService.fetchPostsByKeyword(searchWord);
    }

}