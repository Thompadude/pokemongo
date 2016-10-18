package com.pokemongo.rest;

import com.pokemongo.models.Post;
import com.pokemongo.models.User;
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
import java.util.Collection;
import java.util.List;

@Path("/post")
public class PostRestProvider {

    @EJB
    PostService postService;

    @GET
    @Produces("application/json")
    public Response getAllPosts(@Context UriInfo uriInfo) {
        List<Post> posts = postService.fetchPostsWithoutParent();

        if (posts.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();

        setChildPostsLinks(uriInfo, posts);
        return Response.status(Response.Status.OK).entity(posts).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getPostById(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        Post post = postService.fetchPost(id);

        if (post == null)
            return Response.status(Response.Status.NO_CONTENT).build();

        post.addLink(getLink(id, uriInfo));
        post.addLink(getAuthorLink(post, uriInfo));
        
        setChildPostsLinks(uriInfo, post.getChildPosts());
        return Response.status(Response.Status.OK).entity(post).build();
    }
    
    @GET
    @Path("/search/{searchWord}")
    @Produces("application/json")
    public Response getPostsByKeyword(@PathParam("searchWord") String searchWord, @Context UriInfo uriInfo) {
        List<Post> posts = postService.fetchPostsByKeyword(searchWord);

        if (posts.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();

        setChildPostsLinks(uriInfo, posts);
        return Response.status(Response.Status.OK).entity(posts).build();
    }
    
    private void setChildPostsLinks(UriInfo uriInfo, Collection<Post> posts) {
        for (Post post : posts) {
            post.addLink(getLink(post.getId(), uriInfo));
            post.addLink(getAuthorLink(post, uriInfo));
            if (!post.getChildPosts().isEmpty()) {
                for (Post childPost : post.getChildPosts()) {
                    childPost.addLink(getLink(childPost.getId(), uriInfo));
                    childPost.addLink(getAuthorLink(childPost, uriInfo));
                }
            }
        }
    }
    
    private Link getAuthorLink(Post post, UriInfo uriInfo) {
        String uri = uriInfo.getBaseUriBuilder()
                .path(UserRestProvider.class)
                .path(post.getAuthor().getId().toString())
                .build()
                .toString();
        return new Link(uri, "Author");
    }

    private Link getLink(Long id, UriInfo uriInfo) {
        String uri = uriInfo.getBaseUriBuilder()
                .path(PostRestProvider.class)
                .path(id.toString())
                .build()
                .toString();
        return new Link(uri, "Self");
    }

}