package com.pokemongo.controllers;

import com.pokemongo.business.UserEJB;
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
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FacesContext.class})
public class UserControllerTest {

    private User testLoggedInUser;

    @Mock
    private UserEJB mockedUserEJB;
    @Mock
    private UserHandler userHandler;

    // Mocks for the context
    @Mock
    private ExternalContext mockedExternalContext;
    @Mock
    private FacesContext mockedFacesContext;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        testLoggedInUser = new User("User Name", "User Email", "User Token");
        userController = new UserController();

        MockitoAnnotations.initMocks(this);

        when(mockedUserEJB.logIn(any())).thenReturn(true);
        when(mockedUserEJB.logOut()).thenReturn(false);

        when(userHandler.logIn(testLoggedInUser)).thenReturn(true);
        when(userHandler.fetchUserByEmail(testLoggedInUser.getEmail())).thenReturn(testLoggedInUser);

        // Mock the context
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
    public void testLogIn() throws Exception {


        assertTrue(userController.logIn(testLoggedInUser));
    }

    @Test
    public void testLogOut() throws Exception {
        assertFalse(userController.logOut());
    }

}