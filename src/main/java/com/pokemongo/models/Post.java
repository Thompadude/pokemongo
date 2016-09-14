package com.pokemongo.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Post.fetchAll", query = "SELECT p FROM Post p"),
        @NamedQuery(name = "Post.fetchPostsWithoutParent", query = "SELECT p FROM Post p WHERE p.parentPost = NULL")
})
public class Post implements Serializable {

    private static final long serialVersionUID = 6787577451747845441L;

    @Id
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "authorId")
    private User author;
    private LocalDateTime postTime;
    @ManyToOne
    @JoinColumn(name = "parentPostId")
    private Post parentPost;
    @OneToMany(mappedBy = "parentPost")
    private List<Post> childPosts;

    public Long getId() {
        return id;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    public Post getParentPost() {
        return parentPost;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }

    public List<Post> getChildPosts() {
        return childPosts;
    }

    public void setChildPosts(List<Post> childPosts) {
        this.childPosts = childPosts;
    }

}