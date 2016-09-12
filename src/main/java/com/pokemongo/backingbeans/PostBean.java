package com.pokemongo.backingbeans;

import com.pokemongo.EJB.PostEJB;
import com.pokemongo.EJB.interfaces.LocalPost;
import com.pokemongo.model.Post;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "postBean")
@SessionScoped
public class PostBean implements Serializable{
    
    private static final long serialVersionUID = -5930735123556838017L;
    
    private String title;
    private String content;
    
    @EJB
    private LocalPost localPost;
    
    public void savePost() {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        
        localPost.savePost(post);
    }
    
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
