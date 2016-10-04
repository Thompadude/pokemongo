package com.pokemongo.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserControllerTest {

    private UserController userController;

    @Before
    public void setUp() throws Exception {
        userController = new UserController();
    }

    @After
    public void tearDown() throws Exception {
        userController = null;
    }

    @Test
    public void testLogIn() throws Exception {
        // TODO need to mock UserHandler
        // assertTrue(userController.logIn());
    }

    @Test
    public void testLogOut() throws Exception {

    }
}