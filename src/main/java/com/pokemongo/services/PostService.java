package com.pokemongo.services;

import com.pokemongo.models.Post;
import com.pokemongo.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("unchecked")
@Stateful
public class PostService {
    
    @PersistenceContext
    private EntityManager em;
    
    private static final Logger logger = LogManager.getLogger(PostService.class);
    
    public void savePost(Post post) {
        
        logger.info("Saving post...");
        
        if (em.merge(post) != null) {
            logger.info("Post saved. Title: {}, author id: {}", post.getTitle(), post.getAuthor().getId());
        } else {
            logger.error("Error saving post!");
        }
    }
    
    public Post fetchPost(long postId) {
        return em.find(Post.class, postId);
    }
    
    public List<Post> fetchPostsWithoutParent() {
        List<Post> postsWithoutParents = em.createNamedQuery("Post.fetchPostsWithoutParent").getResultList();
        
        logger.debug("Fetched {} posts (without parents)", postsWithoutParents.size());
        
        return postsWithoutParents;
    }
    
    public List<Post> fetchPostsByKeyword(String keyword) {
        return em.createNamedQuery("Post.fetchPostsWithoutParentByKeyWord")
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }
    
}