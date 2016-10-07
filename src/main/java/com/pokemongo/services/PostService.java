package com.pokemongo.services;

import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.Post;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("unchecked")
@Stateful
public class PostService {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LogManager.getLogger(PostService.class);

    public Post savePost(Post post) throws DatabaseException {
        if (em.merge(post) != null) {
            logger.info("Post saved. Title: {}, author id: {}", post.getTitle(), post.getAuthor().getId());
            return post;
        } else {
            logger.error("Error saving post");
        }
        throw new DatabaseException("Error saving post");
    }

    public Post fetchPost(long postId) {
        try {
            logger.debug("Fetching post by id");
            return em.find(Post.class, postId);
        } catch (NoResultException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<Post> fetchPostsWithoutParent() {
        List<Post> postsWithoutParents = em.createNamedQuery("Post.fetchPostsWithoutParent").getResultList();

        logger.debug("Fetched {} posts (without parents)", postsWithoutParents.size());

        return postsWithoutParents;
    }

    public List<Post> fetchPostsOrderedByChildPostsLength() {
        List<Post> postsOrderedByChildPostsLength = em.createNamedQuery("Post.fetchPostsOrderedByChildPostsLength").getResultList();

        logger.debug("Fetched {} posts (without parents) ordered by amount of comments", postsOrderedByChildPostsLength.size());

        return postsOrderedByChildPostsLength;
    }

    public List<Post> fetchPostsByKeyword(String keyword) {
        logger.debug("Fetching posts by keyword: {}", keyword);

        return em.createNamedQuery("Post.fetchPostsByKeyWord")
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }

}