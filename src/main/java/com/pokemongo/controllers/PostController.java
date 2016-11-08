package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.PostHandler;
import com.pokemongo.controllers.enums.SortOrder;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.exceptions.FormException;
import com.pokemongo.exceptions.UserException;
import com.pokemongo.models.Post;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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
    private SortOrder sortOrder;
    private List<Post> postSearchResults;
    private List<Post> posts;
    @EJB
    private PostHandler postHandler;
    private static final Logger logger = LogManager.getLogger(PostController.class);

    @PostConstruct
    public void init() {
        posts = postHandler.fetchPostsWithoutParent();
        sortOrder = SortOrder.DATE;
        logger.debug("@PostConstruct executed - posts loaded");
    }

    /**
     * Saves the post and reset the post fields.
     * Displays a feedback message.
     */
    public void savePost() {
        try {
            Post post = new Post(title, content);
            postHandler.savePost(post);
            resetPostFields();
            FacesMessageController.displaySuccessMessage("New post created.");
        } catch (UserException | DatabaseException | FormException e) {
            logger.error(e.getMessage());
            FacesMessageController.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Saves the reply.
     *
     * @param postId is the id of the post which is replied to.
     * @throws DatabaseException if something goes wrong while saving the post to the database.
     */
    public void saveReply(long postId) throws DatabaseException {
        try {
            Post reply = new Post(replyContent);
            reply = postHandler.saveReply(reply, postId);
            resetPostFields();
            FacesMessageController.displaySuccessMessage("You wrote a reply to post with title \""
                    + reply.getParentPost().getTitle() + "\".");
        } catch (UserException | FormException e) {
            logger.error(e.getMessage());
            FacesMessageController.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Changes the post list sort order.
     *
     * @param event is the value from the drop-down list.
     */
    public void setPostSortOrder(ValueChangeEvent event) {
        logger.debug("Changing post sort order");
        sortOrder = SortOrder.valueOf(event.getNewValue().toString().toUpperCase());
        FacesMessageController.displaySuccessMessage("Posts is now sorted by "
                + event.getNewValue().toString() + ".");
        fetchFreshPosts();
    }

    public void orderPostsByDate() {
        sortOrder = SortOrder.DATE;
        setPosts(postHandler.fetchPostsWithoutParent());
    }

    public void orderPostsByChildPostsLength() {
        sortOrder = SortOrder.COMMENTS;
        setPosts(postHandler.fetchPostsOrderedByChildPostsLength());
    }

    public String fetchPostsByKeyword() {
        try {
            setPostSearchResults(postHandler.fetchPostsByKeyword(searchWord));
            return "/index.xhtml?faces-redirect=true";
        } catch (FormException e) {
            logger.warn(e.getMessage());
            FacesMessageController.displayErrorMessage(e.getMessage());
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
        switch (sortOrder) {
            case COMMENTS:
                orderPostsByChildPostsLength();
                break;
            default:
                orderPostsByDate();
                break;
        }
        // Only refresh the searched posts section if the user previously searched for posts
        if (searchWord != null)
            fetchPostsByKeyword();
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

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}