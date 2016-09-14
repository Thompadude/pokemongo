package com.pokemongo.dataaccessobjects;

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
            System.out.println("*LOG* " + post.getTitle() + " saved to database.");
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

}