package com.pokemongo.controllers;

import com.pokemongo.business.UserEJB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserEJB mockedUserEJB;
    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        userController = new UserController();

        MockitoAnnotations.initMocks(this);

        when(mockedUserEJB.logIn(any())).thenReturn(true);
        when(mockedUserEJB.logOut()).thenReturn(false);
    }

    @After
    public void tearDown() throws Exception {
        userController = null;
    }

    @Test
    public void testLogIn() throws Exception {
        assertTrue(userController.logIn());
    }

    @Test
    public void testLogOut() throws Exception {
        assertFalse(userController.logOut());
    }

}