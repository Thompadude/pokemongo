package com.pokemongo.business.interfaces;

import com.pokemongo.models.Post;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PostHandler {

    void savePost(Post post);

    Post fetchPost(long postId);

    List<Post> fetchAllPosts();

    void addChildPost(long postId, Post childPost);

    List<Post> fetchPostsWithoutParent();

}