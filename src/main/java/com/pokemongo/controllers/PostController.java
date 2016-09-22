package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.PostHandler;
import com.pokemongo.exceptions.UserNotLoggedInException;
import com.pokemongo.models.Post;
import com.pokemongo.models.User;

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
    private List<Post> posts;
    private List<Post> postsWithoutParent;
    private List<Post> childPosts;
    @EJB
    private PostHandler postHandler;

    public void savePost() {
        Post post = new Post(title, content);
        try {
            postHandler.savePost(post);
        } catch (UserNotLoggedInException e) {
            FacesMessage message = new FacesMessage("You must be logged in");
            FacesContext.getCurrentInstance().addMessage("formId:postForm", message);
        }
    }

    public String saveReply(long postId) {
        System.out.println("replyContent");
        Post reply = new Post(replyContent);
        postHandler.saveReply(reply, postId);
    
        replyContent = "";
        return "/index.xhtml?faces-redirect=true";
    }

    public void fetchPost(long postId) {
        //TODO Replace with production code
        Post post = postHandler.fetchPost(postId);
        System.out.println(post.getChildPosts().get(2).getTitle());
        System.out.println("*LOG* post.getTitle(): " + post.getTitle());
        System.out.println("*LOG* post.getContent(): " + post.getContent());
        System.out.println("*LOG* post.getPostTime(): " + post.getPostTime());
        System.out.println("*LOG* post.getChildPosts().size(): " + post.getChildPosts().size());
    }

    public void print(long id) {
        System.out.println("*LOG* ID of ROW: " + id);
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