package com.pokemongo.enterprisejavabeans.interfaces;

import com.pokemongo.model.Post;

import javax.ejb.Local;
import java.util.List;

@Local
public interface LocalPost {

    void savePost(Post post);

    Post fetchPost(long postId);

    List<Post> fetchAllPosts();

    void addChildPost(long postId, Post childPost);

}