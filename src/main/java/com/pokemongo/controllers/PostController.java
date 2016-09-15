package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.LocalPost;
import com.pokemongo.models.Post;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "postController")
@SessionScoped
public class PostController implements Serializable {

    private static final long serialVersionUID = -5930735123556838017L;

    private String title;
    private String content;
    private String childPostTitle;
    private String childPostContent;
    private List<Post> posts;
    private List<Post> postsWithoutParent;
    private List<Post> childPosts;
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
        posts = localPost.fetchAllPosts();
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPostsWithoutParent() {
        postsWithoutParent = localPost.fetchPostsWithoutParent();
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

    public String getChildPostTitle() {
        return childPostTitle;
    }

    public void setChildPostTitle(String childPostTitle) {
        this.childPostTitle = childPostTitle;
    }

    public String getChildPostContent() {
        return childPostContent;
    }

    public void setChildPostContent(String childPostContent) {
        this.childPostContent = childPostContent;
    }

    public void savePost() {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        localPost.savePost(post);
    }

    public void fetchPost() {
        Post post = localPost.fetchPost(1L);
        System.out.println(post.getChildPosts().get(2).getTitle());
        System.out.println("*LOG* post.getTitle(): " + post.getTitle());
        System.out.println("*LOG* post.getContent(): " + post.getContent());
        System.out.println("*LOG* post.getPostTime(): " + post.getPostTime());
        System.out.println("*LOG* post.getChildPosts().size(): " + post.getChildPosts().size());
    }

    public void print(long id) {
        System.out.println("*LOG* ID of ROW: " + id);
    }

    public void addChildPost(long id) {
        Post childPost = new Post();
        childPost.setTitle(childPostTitle);
        childPost.setContent(childPostContent);
        localPost.addChildPost(id, childPost);
    }

}