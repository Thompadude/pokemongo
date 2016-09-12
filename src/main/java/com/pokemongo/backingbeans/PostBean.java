package com.pokemongo.backingbeans;

import com.pokemongo.EJB.interfaces.LocalPost;
import com.pokemongo.model.Post;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named(value = "postBean")
@SessionScoped
public class PostBean implements Serializable{
    
    private static final long serialVersionUID = -5930735123556838017L;
    
    private String title;
    private String content;
    private List<Post> posts;
    
    @EJB
    private LocalPost localPost;
    
    public void savePost() {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        
        localPost.savePost(post);
    }
    
    public void getPost() {
        Post post = localPost.getPost(3L);
    
        System.out.println(post.getTitle());
        System.out.println(post.getContent());
        System.out.println(post.getPostTime());
    }
    
    public void getAllPosts() {
        posts = localPost.getAllPosts();
    
        System.out.println(posts);
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
    
    
}
