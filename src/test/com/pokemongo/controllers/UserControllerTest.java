package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FacesContext.class})
public class UserControllerTest {

    private User testLoggedInUser;

    @Mock
    private UserHandler mockedUserHandler;
    @Mock
    private ExternalContext mockedExternalContext; // For PowerMockito
    @Mock
    private FacesContext mockedFacesContext; // For PowerMockito

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        userController = new UserController();
        testLoggedInUser = new User("User Name", "User Email", "User Token");

        MockitoAnnotations.initMocks(this);
        when(mockedUserHandler.fetchUserByEmail(testLoggedInUser.getEmail())).thenReturn(testLoggedInUser);

        // Use PowerMockito to mock the context
        PowerMockito.mockStatic(FacesContext.class);
        when(FacesContext.getCurrentInstance()).thenReturn(mockedFacesContext);
        when(mockedFacesContext.getExternalContext()).thenReturn(mockedExternalContext);
        Mockito.doNothing().when(mockedExternalContext).redirect("index.xhtml");
    }

    @After
    public void tearDown() throws Exception {
        userController = null;
    }

    @Test
    public void testLogIn_Should_Return_True() throws Exception {
        when(mockedUserHandler.logIn(testLoggedInUser)).thenReturn(true);
        assertTrue(userController.logIn(testLoggedInUser));
    }

    @Test
    public void testLogIn_Should_Return_False() throws Exception {
        when(mockedUserHandler.logIn(testLoggedInUser)).thenReturn(false);
        assertFalse(userController.logIn(testLoggedInUser));
    }

    @Test
    public void testLogOut() throws Exception {
        when(mockedUserHandler.logOut()).thenReturn(false);
        assertFalse(userController.logOut());
    }

}