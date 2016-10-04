package com.pokemongo.business;

import com.pokemongo.models.Post;
import com.pokemongo.models.User;
import com.pokemongo.services.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
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
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FacesContext.class})
public class PostEJBTest {

    @Mock
    private PostService mockedPostService;

    // Mocks for the context
    @Mock
    private Map<String, Object> mockedSessionMap;
    @Mock
    private ExternalContext externalContext;
    @Mock
    private FacesContext facesContext;

    @InjectMocks
    private PostEJB testPostEJB;

    private Post testPost, testReply;
    private User testLoggedInUser;

    @Before
    public void setUp() throws Exception {
        testPostEJB = new PostEJB();
        testPost = new Post("Post Title", "Post Content");
        testReply = new Post(null, "Reply Content");
        testLoggedInUser = new User("User Name", "User Email", "User Token");

        MockitoAnnotations.initMocks(this);

        // Mock the service
        when(mockedPostService.savePost(testPost)).thenReturn(testPost);

        // TODO find out a way to mock out the logger. The test runs - but with small errors

        // Mock the context
        PowerMockito.mockStatic(FacesContext.class);
        when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
        when(facesContext.getExternalContext()).thenReturn(externalContext);
        when(externalContext.getSessionMap()).thenReturn(mockedSessionMap);

        // Mock a logged in user
        when(mockedSessionMap.get("loggedInUser")).thenReturn(testLoggedInUser);
    }

    @After
    public void tearDown() throws Exception {
        testPostEJB = null;
        testPost = null;
        testLoggedInUser = null;
    }

    @Test
    public void testSavePost() throws Exception {
        assertNotNull("The post returned from savePost() is NULL", testPostEJB.savePost(testPost));
        assertEquals("The post returned from savePost() is not equal to the post saved", testPost, testPostEJB.savePost(testPost));
    }

    @Test
    public void testSaveReply() throws Exception {

    }

    @Test
    public void testFetchPostsWithoutParent() throws Exception {

    }

    @Test
    public void testFetchPostsOrderedByChildPostsLength() throws Exception {

    }

    @Test
    public void testFetchPostsByKeyword() throws Exception {

    }
}