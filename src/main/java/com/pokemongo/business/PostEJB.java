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

    /**
     * Handles logic before saving the post to the database.
     *
     * @param post is the post to save.
     * @return the saved post if save is successful.
     * @throws UserException if the user is not logged in while trying to save the pokemon.
     * @throws DatabaseException if something goes wrong while saving the pokemon to the database.
     * @throws FormException if some values are missing while trying to save the pokemon.
     */
    @Override
    public Post savePost(Post post) throws UserException, DatabaseException, FormException {
        User author = userHandler.getLoggedInUser();

        if (author != null) {
            post.setAuthor(author);
        } else {
            throw new UserException("You must be logged in to post.");
        }

        validatePost(post);

        post = postService.savePost(post);
        return post;
    }

    /**
     * Handles logic before saving the reply (to the post) to the database.
     *
     * @param reply is the reply to save.
     * @param parentId the post which was replied to.
     * @return the saved reply if save is successful.
     * @throws UserException if the user is not logged in while trying to save the pokemon.
     * @throws DatabaseException if something goes wrong while saving the pokemon to the database.
     * @throws FormException if some values are missing while trying to save the pokemon.
     */
    @Override
    public Post saveReply(Post reply, long parentId) throws UserException, DatabaseException, FormException {
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

    /**
     * Handles logic before the fetching of the list of posts, based on the keyword.
     *
     * @param keyword is the String which the search is based upon.
     * @return the list of posts containing the keyword (title or content).
     * @throws FormException if the keyword is shorter than three chars.
     */
    @Override
    public List<Post> fetchPostsByKeyword(String keyword) throws FormException {
        if (keyword.length() < 3) {
            throw new FormException("Search phrase must be more than two characters.");
        } else {
            return postService.fetchPostsByKeyword(keyword);
        }
    }

    /**
     * Validates the post or reply.
     *
     * @param post is the post or reply to validate.
     * @throws FormException is thrown if the title is null or shorter than five chars or longer than 45 chars,
     * or if content is shorter than eleven chars. The title validation is not executed if it is a reply.
     */
    private void validatePost(Post post) throws FormException {
        if (post.getParentPost() == null && post.getTitle().length() < 5) {
            throw new FormException("Title must be more than four characters.");
        } else if (post.getParentPost() == null && post.getTitle().length() > 45) {
            throw new FormException("Title must not be more than 45 characters.");
        } else if (post.getContent().length() < 11) {
            throw new FormException("Content must be more than 10 characters.");
        }
    }



}