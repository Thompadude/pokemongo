package com.pokemongo.business;

import com.pokemongo.business.interfaces.PostHandler;
import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.exceptions.FormException;
import com.pokemongo.exceptions.UserException;
import com.pokemongo.models.Post;
import com.pokemongo.models.User;
import com.pokemongo.services.PostService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PostEJB implements PostHandler {

    @EJB
    private PostService postService;
    @EJB
    private UserHandler userHandler;

    @Override
    public Post savePost(Post post) throws UserException, DatabaseException {
        User author = userHandler.getLoggedInUser();

        if (author != null) {
            post.setAuthor(author);
            post = postService.savePost(post);
            return post;
        }
        throw new UserException("You must be logged in to post.");
    }

    @Override
    public Post saveReply(Post reply, long parentId) throws UserException, DatabaseException {
        Post parentPost = postService.fetchPost(parentId);
        reply.setParentPost(parentPost);
        reply = savePost(reply);

        return reply;
    }

    @Override
    public List<Post> fetchPostsWithoutParent() {
        return postService.fetchPostsWithoutParent();
    }

    @Override
    public List<Post> fetchPostsOrderedByChildPostsLength() {
        return postService.fetchPostsOrderedByChildPostsLength();
    }

    @Override
    public List<Post> fetchPostsByKeyword(String keyword) throws FormException {
        if (keyword.length() < 3) {
            throw new FormException("Please type more than two characters.");
        } else {
            return postService.fetchPostsByKeyword(keyword);
        }
    }

}