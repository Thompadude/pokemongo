package com.pokemongo.enterprisejavabeans;

import com.pokemongo.dataaccessobjects.PostDAO;
import com.pokemongo.enterprisejavabeans.interfaces.LocalPost;
import com.pokemongo.model.Post;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PostEJB implements LocalPost {

    @EJB
    private PostDAO postDAO;

    @Override
    public void savePost(Post post) {
        postDAO.savePost(post);
    }

    @Override
    public Post fetchPost(long postId) {
        return postDAO.fetchPost(postId);
    }

    @Override
    public List<Post> fetchAllPosts() {
        return postDAO.fetchAllPosts();
    }

    @Override
    public void addChildPost(long postId, Post childPost) {
        Post parentPost = postDAO.fetchPost(postId);
        childPost.setParentPost(parentPost);
        postDAO.savePost(childPost);
    }

    @Override
    public List<Post> fetchPostsWithoutParent() {
        return postDAO.fetchPostsWithoutParent();
    }

}