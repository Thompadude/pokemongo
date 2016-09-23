package com.pokemongo.services;

import com.pokemongo.models.Post;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("unchecked")
@Stateful
public class PostService {

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

    public List<Post> fetchPostsByKeyword(String keyword) {
        return em.createNamedQuery("Post.fetchPostsWithoutParentByKeyWord")
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }

}