package com.pokemongo.business;

import com.pokemongo.business.interfaces.PostHandler;
import com.pokemongo.controllers.UserController;
import com.pokemongo.exceptions.UserNotLoggedInException;
import com.pokemongo.models.Post;
import com.pokemongo.models.User;
import com.pokemongo.services.PostService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class PostEJB implements PostHandler {

    @EJB
    private PostService postService;
    @Inject
    private UserController userController;

    @Override
    public void savePost(Post post) throws UserNotLoggedInException {
        if (userController.fetchLoggedInUser() != null) {
            User author = userController.fetchLoggedInUser();
            post.setAuthor(author);
            postService.savePost(post);
        } else {
            throw new UserNotLoggedInException();
        }
    }

    @Override
    public void saveReply(Post reply, long parentId) {
        // TODO add error handling
        User author = userController.fetchLoggedInUser();
        Post parentPost = postService.fetchPost(parentId);
        reply.setAuthor(author);
        reply.setParentPost(parentPost);
        postService.savePost(reply);
    }

    @Override
    public Post fetchPost(long postId) {
        return postService.fetchPost(postId);
    }

    @Override
    public List<Post> fetchAllPosts() {
        return postService.fetchAllPosts();
    }

    @Override
    public void addChildPost(long postId, Post childPost) {
        Post parentPost = postService.fetchPost(postId);
        childPost.setParentPost(parentPost);
        postService.savePost(childPost);
    }

    @Override
    public List<Post> fetchPostsWithoutParent() {
        return postService.fetchPostsWithoutParent();
    }

    @Override
    public List<Post> fetchPostsByKeyword(String keyword) {
        return postService.fetchPostsByKeyword(keyword);
    }

}