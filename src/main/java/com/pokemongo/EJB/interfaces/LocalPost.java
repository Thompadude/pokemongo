package com.pokemongo.EJB.interfaces;

import com.pokemongo.model.Post;

import javax.ejb.Local;
import java.util.List;

@Local
public interface LocalPost {
    void savePost(Post post);
    
    Post getPost(long postId);
    
    List<Post> getAllPosts();
}
