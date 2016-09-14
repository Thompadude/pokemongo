package com.pokemongo.business;

import com.pokemongo.services.PostService;
import com.pokemongo.business.interfaces.LocalPost;
import com.pokemongo.models.Post;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PostHandler implements LocalPost {

    @EJB
    private PostService postService;

    @Override
    public void savePost(Post post) {
        postService.savePost(post);
    }

    @Override
    public Post fetchPost(long postId) {
        return postService.fetchPost(postId);
    }

    @Override
    public List<Post> fetchAllPosts() {
        return postService.fetchAllPosts();
    }

    @Override
    public void addChildPost(long postId, Post childPost) {
        Post parentPost = postService.fetchPost(postId);
        childPost.setParentPost(parentPost);
        postService.savePost(childPost);
    }

    @Override
    public List<Post> fetchPostsWithoutParent() {
        return postService.fetchPostsWithoutParent();
    }

}