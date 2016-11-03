package com.pokemongo.rest;

import com.pokemongo.models.Post;
import com.pokemongo.services.PostService;
import com.pokemongo.utilities.RestLinkBuilder;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.List;

@Path("/")
public class PostRestProvider {

    @EJB
    PostService postService;
    private RestLinkBuilder<PostRestProvider> restLinkBuilder = new RestLinkBuilder<>(PostRestProvider.class);

    @GET
    @Path("/posts")
    @Produces("application/json")
    public Response getAllPosts(@Context UriInfo uriInfo) {
        List<Post> posts = postService.fetchPostsWithoutParent();

        if (!posts.isEmpty()) {
            setChildPostsLinks(uriInfo, posts);
            return Response.status(Response.Status.OK).entity(posts).build();
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("post/{id}")
    @Produces("application/json")
    public Response getPostById(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        Post post = postService.fetchPost(id);

        if (!(post == null)) {
            post.addLink(restLinkBuilder.getSelfLink(id, uriInfo));
            post.addLink(restLinkBuilder.getUserLink(post, uriInfo, "Author"));
            setChildPostsLinks(uriInfo, post.getChildPosts());

            return Response.status(Response.Status.OK).entity(post).build();
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("posts/search/{searchWord}")
    @Produces("application/json")
    public Response getPostsByKeyword(@PathParam("searchWord") String searchWord, @Context UriInfo uriInfo) {
        List<Post> posts = postService.fetchPostsByKeyword(searchWord);

        if (!posts.isEmpty()) {
            setChildPostsLinks(uriInfo, posts);
            return Response.status(Response.Status.OK).entity(posts).build();
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    private void setChildPostsLinks(UriInfo uriInfo, Collection<Post> posts) {
        for (Post post : posts) {
            post.addLink(restLinkBuilder.getSelfLink(post.getId(), uriInfo));
            post.addLink(restLinkBuilder.getUserLink(post, uriInfo, "Author"));
            if (!post.getChildPosts().isEmpty()) {
                for (Post childPost : post.getChildPosts()) {
                    childPost.addLink(restLinkBuilder.getSelfLink(childPost.getId(), uriInfo));
                    childPost.addLink(restLinkBuilder.getUserLink(childPost, uriInfo, "Author"));
                }
            }
        }
    }

}