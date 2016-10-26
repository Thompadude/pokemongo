package com.pokemongo.soap;

import com.pokemongo.business.interfaces.PostHandler;
import com.pokemongo.models.Post;
import com.pokemongo.services.PostService;

import javax.ejb.EJB;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;



@WebService
public class PostSoapProvider{

    @EJB
    private PostService postService;

    @EJB
    private PostHandler postHandler;


    public List<Post> getAllPosts(){
        return postService.fetchPostsOrderedByChildPostsLength();
    }

    public Post getPost(@WebParam(name = "postId")long postId) {
        return postService.fetchPost(postId);
    }
}
