package com.pokemongo.business;

import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.exceptions.FormException;
import com.pokemongo.exceptions.UserNotLoggedInException;
import com.pokemongo.models.Post;
import com.pokemongo.models.User;
import com.pokemongo.services.PostService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FacesContext.class})
public class PostEJBTest {

    // Mock the PostService
    @Mock
    private PostService mockedPostService;

    // Mocks for the context
    @Mock
    private Map<String, Object> mockedSessionMap;
    @Mock
    private ExternalContext mockedExternalContext;
    @Mock
    private FacesContext mockedFacesContext;

    // Inject above mocks in the class to be tested
    @InjectMocks
    private PostEJB postEJB;

    // Fake objects to help with testing
    private User testLoggedInUser;
    private Post testPost, testReply;
    private List<Post> testPostList;

    @Before
    public void setUp() throws Exception {
        // Create the instance of the class to be tested
        postEJB = new PostEJB();

        // Initialize the fake objects
        testLoggedInUser = new User("User Name", "User Email", "User Token");
        testPost = new Post("Post Title", "Post Content");
        testReply = new Post(null, "Reply Content");
        testPostList = new ArrayList<>();
        testPostList.add(testPost);
        testPostList.add(testReply);

        // Initialize the mocks
        MockitoAnnotations.initMocks(this);

        // Mock the methods in PostService
        when(mockedPostService.savePost(testPost)).thenReturn(testPost);
        when(mockedPostService.savePost(testReply)).thenReturn(testReply);
        when(mockedPostService.fetchPost(1L)).thenReturn(testPost);
        when(mockedPostService.fetchPostsWithoutParent()).thenReturn(testPostList);
        when(mockedPostService.fetchPostsOrderedByChildPostsLength()).thenReturn(testPostList);
        when(mockedPostService.fetchPostsByKeyword("Pokemon")).thenReturn(testPostList);

        // TODO find out a way to mock out the logger. All the test runs - but with trivial errors

        // Mock the context
        PowerMockito.mockStatic(FacesContext.class);
        when(FacesContext.getCurrentInstance()).thenReturn(mockedFacesContext);
        when(mockedFacesContext.getExternalContext()).thenReturn(mockedExternalContext);
        when(mockedExternalContext.getSessionMap()).thenReturn(mockedSessionMap);

        // Mock the logged in user
        when(mockedSessionMap.get("loggedInUser")).thenReturn(testLoggedInUser);
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

    @Test(expected = UserNotLoggedInException.class)
    public void testSavePost_UserNotLoggedIn_UserNotLoggedInExceptionThrown() throws UserNotLoggedInException, DatabaseException {
        when(mockedSessionMap.get("loggedInUser")).thenReturn(null);
        postEJB.savePost(testPost);
    }

    @Test
    public void testSaveReply() throws Exception {
        assertNotNull("The reply returned from saveReply() is NULL", postEJB.saveReply(testReply, 1L));
        assertEquals("The reply returned from saveReply() is not equal to the reply saved", testReply, postEJB.saveReply(testReply, 1L));
    }

    @Test(expected = UserNotLoggedInException.class)
    public void testSaveReply_UserNotLoggedIn_UserNotLoggedInExceptionThrown() throws UserNotLoggedInException, DatabaseException {
        when(mockedSessionMap.get("loggedInUser")).thenReturn(null);
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