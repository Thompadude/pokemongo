package com.pokemongo.backingbeans;

import com.pokemongo.enterprisejavabeans.interfaces.LocalPost;
import com.pokemongo.model.Post;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "postBean")
@SessionScoped
public class PostBean implements Serializable {

    private static final long serialVersionUID = -5930735123556838017L;

    private String title;
    private String content;
    private List<Post> posts;
    @EJB
    private LocalPost localPost;

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

    public List<Post> getPosts() {
        return localPost.fetchAllPosts();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void savePost() {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        localPost.savePost(post);
    }

    public void fetchPost() {
        Post post = localPost.fetchPost(3L);
        System.out.println(post.getTitle());
        System.out.println(post.getContent());
        System.out.println(post.getPostTime());
    }

}