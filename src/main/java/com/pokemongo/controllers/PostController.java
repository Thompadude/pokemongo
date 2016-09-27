package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.PostHandler;
import com.pokemongo.exceptions.UserNotLoggedInException;
import com.pokemongo.models.Post;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private List<Post> postsWithoutParent;
    private List<Post> childPosts;
    @EJB
    private PostHandler postHandler;
    private static final Logger logger = LogManager.getLogger(PostController.class);

    public void savePost() {
        
        Post post = new Post(title, content);
        try {
            postHandler.savePost(post);
        } catch (UserNotLoggedInException e) {
            FacesMessage message = new FacesMessage("You must be logged in");
            FacesContext.getCurrentInstance().addMessage("formId:postForm", message);
        }
        
        resetFields();
    }
    
    public String saveReply(long postId) {
        
        Post reply = new Post(replyContent);
        postHandler.saveReply(reply, postId);

        // If the user search for a post and comment on it this will update the search result list.
        fetchPostsByKeyword();

        resetFields();
        return "/index.xhtml?faces-redirect=true";
    }
    
    private void resetFields() {
        logger.debug("Resetting input fields.");
        
        title = "";
        content = "";
        replyContent = "";
    }
    
    public void fetchPostsByKeyword() {
        postSearchResults = postHandler.fetchPostsWithoutParentByKeyword(searchWord);
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
        posts = postHandler.fetchAllPosts();
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPostsWithoutParent() {
        postsWithoutParent = postHandler.fetchPostsWithoutParent();
        return postsWithoutParent;
    }

    public void setPostsWithoutParent(List<Post> postsWithoutParent) {
        this.postsWithoutParent = postsWithoutParent;
    }

    public List<Post> getChildPosts() {
        return childPosts;
    }

    public void setChildPosts(List<Post> childPosts) {
        this.childPosts = childPosts;
    }

}