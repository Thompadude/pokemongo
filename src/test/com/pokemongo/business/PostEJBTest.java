package com.pokemongo.business;

import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.exceptions.FormException;
import com.pokemongo.exceptions.UserException;
import com.pokemongo.models.Post;
import com.pokemongo.models.User;
import com.pokemongo.services.PostService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class PostEJBTest {

    @Mock
    private PostService mockedPostService;
    @Mock
    private UserHandler userHandler;
    @InjectMocks
    private PostEJB postEJB;

    private User testLoggedInUser;
    private Post testPost, testReply;
    private List<Post> testPostList;

    @Before
    public void setUp() throws Exception {
        postEJB = new PostEJB();

        testLoggedInUser = new User("User Name", "User Email", "User Token");

        testReply = new Post(null, "Reply Content");

        testPost = new Post("Post Title", "Post Content");
        testPostList = new ArrayList<>();
        testPostList.add(testPost);
        testPostList.add(testReply);

        MockitoAnnotations.initMocks(this);

        // Mock the methods in PostService
        when(mockedPostService.savePost(testPost)).thenReturn(testPost);
        when(mockedPostService.savePost(testReply)).thenReturn(testReply);
        when(mockedPostService.fetchPost(1L)).thenReturn(testPost);
        when(mockedPostService.fetchPostsWithoutParent()).thenReturn(testPostList);
        when(mockedPostService.fetchPostsOrderedByChildPostsLength()).thenReturn(testPostList);
        when(mockedPostService.fetchPostsByKeyword("Pokemon")).thenReturn(testPostList);

        // Mock the methods in UserHandler
        when(userHandler.getLoggedInUser()).thenReturn(testLoggedInUser);
    }

    @After
    public void tearDown() throws Exception {
        postEJB = null;
        testPost = null;
        testReply = null;
        testLoggedInUser = null;
        testPostList = null;
    }

    @Test
    public void testSavePost() throws Exception {
        assertNotNull("The post returned from savePost() is NULL", postEJB.savePost(testPost));
        assertEquals("The post returned from savePost() is not equal to the post saved", testPost, postEJB.savePost(testPost));
    }

    @Test(expected = UserException.class)
    public void testSavePost_UserNotLoggedIn_UserNotLoggedInExceptionThrown() throws UserException, DatabaseException, FormException {
        when(userHandler.getLoggedInUser()).thenReturn(null);
        postEJB.savePost(testPost);
    }

    @Test
    public void testSaveReply() throws Exception {
        assertNotNull("The reply returned from saveReply() is NULL", postEJB.saveReply(testReply, 1L));
        assertEquals("The reply returned from saveReply() is not equal to the reply saved", testReply, postEJB.saveReply(testReply, 1L));
    }

    @Test(expected = UserException.class)
    public void testSaveReply_UserNotLoggedIn_UserNotLoggedInExceptionThrown() throws UserException, DatabaseException, FormException {
        when(userHandler.getLoggedInUser()).thenReturn(null);
        postEJB.saveReply(testPost, 1L);
    }

    @Test
    public void testFetchPostsWithoutParent() throws Exception {
        assertNotNull("fetchPostsWithoutParent() returned NULL", postEJB.fetchPostsWithoutParent());
        assertEquals("fetchPostsWithoutParent did not return a list of posts", testPostList, postEJB.fetchPostsWithoutParent());
    }

    @Test
    public void testFetchPostsOrderedByChildPostsLength() throws Exception {
        assertNotNull("fetchPostsOrderedByChildPostsLength() returned NULL", postEJB.fetchPostsOrderedByChildPostsLength());
        assertEquals("fetchPostsOrderedByChildPostsLength() did not return a list of posts", testPostList, postEJB.fetchPostsOrderedByChildPostsLength());
    }

    @Test
    public void testFetchPostsByKeyword() throws Exception {
        assertNotNull("fetchPostsByKeyword() returned NULL", postEJB.fetchPostsByKeyword("Pokemon"));
        assertEquals("fetchPostsByKeyword() did not return a list of posts", testPostList, postEJB.fetchPostsByKeyword("Pokemon"));
    }

    @Test(expected = FormException.class)
    public void testFetchPostsByKeyword_SearchWordIsShorterThan3Chars_FormExceptionThrown() throws FormException {
        postEJB.fetchPostsByKeyword("Bu");
    }

}