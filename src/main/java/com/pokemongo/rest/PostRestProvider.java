package com.pokemongo.rest;

import com.pokemongo.models.Post;
import com.pokemongo.services.PostService;
import com.pokemongo.utilities.Link;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/post")
public class PostRestProvider {

    @EJB
    PostService postService;

    @GET
    @Produces("application/json")
    public Response getAllPosts() {
        List<Post> posts = postService.fetchPostsWithoutParent();

        if (posts.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.status(Response.Status.OK).entity(posts).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getPostById(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        String uri = uriInfo.getBaseUriBuilder()
                .path(PostRestProvider.class)
                .path(id.toString())
                .build()
                .toString();
        Link link = new Link(uri, "self");

        Post post = postService.fetchPost(id);

        if (post != null) {
            post.addLink(link);
            return Response.status(Response.Status.OK).entity(post).build();
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/search/{searchWord}")
    @Produces("application/json")
    public Response getPostsByKeyword(@PathParam("searchWord") String searchWord) {
        List<Post> posts = postService.fetchPostsByKeyword(searchWord);

        if (posts.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.status(Response.Status.OK).entity(posts).build();
    }

}