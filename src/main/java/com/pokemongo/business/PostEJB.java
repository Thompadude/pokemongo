package com.pokemongo.business;

import com.pokemongo.business.interfaces.PostHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.exceptions.FormException;
import com.pokemongo.exceptions.UserNotLoggedInException;
import com.pokemongo.models.Post;
import com.pokemongo.models.User;
import com.pokemongo.services.PostService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

@Stateless
public class PostEJB implements PostHandler {

    @EJB
    private PostService postService;

    @Override
    public Post savePost(Post post) throws UserNotLoggedInException, DatabaseException {

        User author = fetchLoggedInUser();

        if (author != null) {
            post.setAuthor(author);
            post = postService.savePost(post);
            return post;
        }
        throw new UserNotLoggedInException("You must be logged in order to post.");
    }

    private User fetchLoggedInUser() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();

        return (User) sessionMap.get("loggedInUser");
    }

    @Override
    public Post saveReply(Post reply, long parentId) throws UserNotLoggedInException, DatabaseException {
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
            throw new FormException();
        } else {
            return postService.fetchPostsByKeyword(keyword);
        }
    }

}