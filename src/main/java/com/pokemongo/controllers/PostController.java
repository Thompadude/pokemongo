package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.PostHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.exceptions.FormException;
import com.pokemongo.exceptions.UserNotLoggedInException;
import com.pokemongo.models.Post;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "postController")
@SessionScoped
public class PostController implements Serializable {

    private static final long serialVersionUID = -5930735123556838017L;

    private String title;
    private String content;
    private String replyContent;
    private String searchWord;
    private List<Post> postSearchResults;
    private List<Post> posts;
    @EJB
    private PostHandler postHandler;
    private static final Logger logger = LogManager.getLogger(PostController.class);

    @PostConstruct
    public void init() {
        posts = postHandler.fetchPostsWithoutParent();
        logger.debug("@PostConstruct executed - posts loaded");
    }

    public void savePost() {
        Post post = new Post(title, content);

        try {
            postHandler.savePost(post);
        } catch (UserNotLoggedInException | DatabaseException e) {
            logger.error(e.getMessage());
            displayPostFormMessage(e.getMessage());
        }

        resetPostFields();
    }

    public String saveReply(long postId) throws DatabaseException {
        Post reply = new Post(replyContent);

        try {
            postHandler.saveReply(reply, postId);
        } catch (UserNotLoggedInException e) {
            logger.error(e.getMessage());
        }

        resetPostFields();
        return "/index.xhtml?faces-redirect=true";
    }

    public void changePostSortOrder(ValueChangeEvent event) {
        String sortOrder = (String) event.getNewValue();
        if (sortOrder.equals("default")) {
            orderPostsInDefaultOrder();
        } else {
            orderPostsByChildPostsLength();
        }
    }

    public String orderPostsInDefaultOrder() {
        setPosts(postHandler.fetchPostsWithoutParent());
        return "/index?faces-redirect=true";
    }

    public String orderPostsByChildPostsLength() {
        // TODO fix: when the user sort by this and post a comment, the default sort order is loaded. Use enum? Boolean?
        setPosts(postHandler.fetchPostsOrderedByChildPostsLength());
        return "/index?faces-redirect=true";
    }

    public String fetchPostsByKeyword() {
        try {
            setPostSearchResults(postHandler.fetchPostsByKeyword(searchWord));
            return "/index.xhtml?faces-redirect=true";
        } catch (FormException e) {
            logger.error(e.getMessage());
            displayPostFormMessage("Please type more than two characters");
            return "/index.xhtml?faces-redirect=false";
        }
    }

    public void resetSearchedPosts() {
        logger.debug("Resetting searched posts section");
        setPostSearchResults(null);
        setSearchWord(null);
    }

    private void resetPostFields() {
        fetchFreshPosts();

        logger.debug("Resetting input fields.");
        title = "";
        content = "";
        replyContent = "";
    }

    private void fetchFreshPosts() {
        logger.debug("Fetching fresh posts");
        posts = postHandler.fetchPostsWithoutParent();

        // Only refresh the searched posts section if the user previously searched for posts
        if (searchWord != null)
            fetchPostsByKeyword();
    }

    private void displayPostFormMessage(String message) {
            logger.error("Displaying post form error message: {}", message);
            FacesMessage facesMessage = new FacesMessage(message);
            FacesContext.getCurrentInstance().addMessage("formId:postForm", facesMessage);
    }

    /* Getters and Setters */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public List<Post> getPostSearchResults() {
        return postSearchResults;
    }

    public void setPostSearchResults(List<Post> postSearchResults) {
        this.postSearchResults = postSearchResults;
    }

    public List<Post> getPosts() {
        fetchFreshPosts();
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}