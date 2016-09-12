package com.pokemongo.EJB;

import com.pokemongo.DAO.PostDAO;
import com.pokemongo.EJB.interfaces.LocalPost;
import com.pokemongo.model.Post;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PostEJB implements LocalPost {
    
    @EJB
    private PostDAO postDAO;
    
    @Override
    public void savePost(Post post) {
        postDAO.savePost(post);
    }
    
    @Override
    public Post getPost(long postId) {
        return postDAO.getPost(postId);
    }
}
