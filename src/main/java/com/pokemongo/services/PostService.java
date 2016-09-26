package com.pokemongo.services;

import com.pokemongo.models.Post;
import com.pokemongo.utilities.LogProvider;
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
    
    Logger logger = LogProvider.getLogger();

    public void savePost(Post post) {
        
        logger.info("Saving post...");
        
        if (em.merge(post) != null) {
            logger.info("Post with title " + post.getTitle() + " successfully saved.");
        } else {
            logger.error("Error saving post!");
        }
    }

    public Post fetchPost(long postId) {
        return em.find(Post.class, postId);
    }

    public List<Post> fetchAllPosts() {
        return em.createNamedQuery("Post.fetchAll").getResultList();
    }

    public List<Post> fetchPostsWithoutParent() {
        return em.createNamedQuery("Post.fetchPostsWithoutParent").getResultList();
    }

    public List<Post> fetchPostsByKeyword(String keyword) {
        return em.createNamedQuery("Post.fetchPostsWithoutParentByKeyWord")
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }

}