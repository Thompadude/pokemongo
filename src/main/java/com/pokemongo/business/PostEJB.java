package com.pokemongo.business;

import com.pokemongo.business.interfaces.PostHandler;
import com.pokemongo.controllers.UserController;
import com.pokemongo.exceptions.UserNotLoggedInException;
import com.pokemongo.models.Post;
import com.pokemongo.models.User;
import com.pokemongo.services.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Stateless
public class PostEJB implements PostHandler {

    @EJB
    private PostService postService;
    @Inject
    private UserController userController;
    private static final  Logger logger = LogManager.getLogger(PostEJB.class);
    
    @Override
    public void savePost(Post post) throws UserNotLoggedInException {
    
        User author = fetchLoggedInUser();
        
        if (author != null) {
            post.setAuthor(author);
            postService.savePost(post);
        } else {
            throw new UserNotLoggedInException("You must be logged in order to post.");
        }
    }
    
    private User fetchLoggedInUser() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
    
        return (User) sessionMap.get("loggedInUser");
    }
    
    @Override
    public void saveReply(Post reply, long parentId) throws UserNotLoggedInException {
        
        Post parentPost = postService.fetchPost(parentId);
        reply.setParentPost(parentPost);
        
        savePost(reply);
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