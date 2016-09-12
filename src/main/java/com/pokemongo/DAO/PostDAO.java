package com.pokemongo.DAO;

import com.pokemongo.model.Post;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class PostDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void savePost(Post post) {
        if (em.merge(post) != null) {
            System.out.println("Hoppla, baby!");
        }
    }
    
    public Post getPost(long postId) {
        
        return em.find(Post.class, postId);
    }
    
    public List<Post> getAllPosts() {
        return em.createNamedQuery("Post.findAll").getResultList();
    }
}
