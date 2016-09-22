package com.pokemongo.services;

import com.pokemongo.models.Post;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        System.out.println("keyword: " + keyword);
        Query namedQuery = em.createNamedQuery("Post.fetchPostsByKeyWord");
        // TODO this does not work as intended. Issue with %
        namedQuery.setParameter("keyword", "%" + keyword + "%");
        List result = namedQuery.getResultList();
        System.out.println("fetchPostsByKeyword.length: " + result.size());
        return result;
    }

}