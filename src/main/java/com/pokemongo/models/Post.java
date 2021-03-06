package com.pokemongo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pokemongo.models.Interfaces.Ownable;
import com.pokemongo.utilities.RestLink;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Post.fetchAll", query = "SELECT p FROM Post p"),
        @NamedQuery(name = "Post.fetchPostsWithoutParent", query = "SELECT p FROM Post p WHERE p.parentPost = NULL ORDER BY p.postTime DESC"),
        @NamedQuery(name = "Post.fetchPostsByKeyWord", query = "SELECT p FROM Post p WHERE p.parentPost = NULL AND (p.content LIKE :keyword OR p.title LIKE :keyword) ORDER BY p.postTime DESC"),
        @NamedQuery(name = "Post.fetchPostsOrderedByChildPostsLength", query = "SELECT p FROM Post p WHERE p.parentPost = NULL ORDER BY p.childPosts.size DESC")
})
public class Post implements Serializable, Comparator<Post>,Ownable {

    private static final long serialVersionUID = 6787577451747845441L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "authorId")
    @JsonIgnore
    private User author;
    private LocalDateTime postTime;
    @ManyToOne
    @JoinColumn(name = "parentPostId")
    @JsonIgnore
    private Post parentPost;
    @OneToMany(mappedBy = "parentPost", fetch = FetchType.EAGER)
    private Set<Post> childPosts;
    @Transient
    private List<RestLink> restLinks = new ArrayList<>();
    @Transient
    private Long authorId;
    @Transient
    private String authorImageURL;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post(String content) {
        this.content = content;
    }

    public Post() {
    }

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

    public String getPostTimeAsString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.postTime.format(dateTimeFormatter);
    }

    @XmlTransient
    public Post getParentPost() {
        return parentPost;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }

    public Set<Post> getChildPosts() {

        return childPosts;
    }

    public void setChildPosts(Set<Post> childPosts) {
        this.childPosts = childPosts;
    }

    public List<RestLink> getRestLinks() {
        return restLinks;
    }

    public void setRestLinks(List<RestLink> restLinks) {
        this.restLinks = restLinks;
    }

    public void addLink(RestLink restLink) {
        restLinks.add(restLink);
    }

    public Long getAuthorId() {
        authorId = author.getId();
        return authorId;
    }

    @Override
    public int compare(Post o1, Post o2) {
        return o1.getPostTime().compareTo(o2.getPostTime());
    }

    public String getAuthorImageURL() {
        authorImageURL = "/images/" + author.getUserImageName();
        return authorImageURL;
    }

    public void setAuthorImageURL(String authorImageURL) {
        this.authorImageURL = authorImageURL;
    }

    @Override
    @JsonIgnore
    public User getOwner() {
        return this.author;
    }

    @Override
    public void setOwner(User user) {
        this.author = user;
    }
}