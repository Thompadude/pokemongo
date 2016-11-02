package com.pokemongo.business;

import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.exceptions.FormException;
import com.pokemongo.models.Pokemon;
import com.pokemongo.models.User;
import com.pokemongo.services.PokemonService;
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

public class PokemonEJBTest {

    @Mock
    private PokemonService mockedPokemonService;
    @Mock
    private UserHandler mockedUserHandler;
    @InjectMocks
    private PokemonEJB testPokemonEJB;

    private Pokemon testPokemon;
    private User testUser;
    private List<Pokemon> testPokemonList;

    @Before
    public void setUp() throws Exception {
        testPokemonEJB = new PokemonEJB();
        testPokemon = new Pokemon(null, null, "fakeLng", "fakeLat", 10, 10);

        testUser = new User();

        testPokemonList = new ArrayList<>();
        testPokemonList.add(testPokemon);

        MockitoAnnotations.initMocks(this);

        when(mockedPokemonService.savePokemon((testPokemon))).thenReturn(testPokemon);
        when(mockedPokemonService.fetchAllPokemons()).thenReturn(testPokemonList);
        when(mockedPokemonService.fetchPokemonByOwnerId(1L)).thenReturn(testPokemonList);

        when(mockedUserHandler.getLoggedInUser()).thenReturn(testUser);
    }

    @After
    public void tearDown() throws Exception {
        testPokemonEJB = null;
        testPokemon = null;
        testUser = null;
        testPokemonList = null;
        mockedUserHandler = null;
        mockedPokemonService = null;
    }

    @Test
    public void testSavePokemon() throws Exception {
        Pokemon actualPokemon = testPokemonEJB.savePokemon(testPokemon);
        assertNotNull(testPokemon);
        assertEquals(testPokemon, actualPokemon);
    }

    /**
     * Test three different scenarios which all should throw Form Exception.
     * First scenario: test save pokemon with hp but without cp.
     * Second scenario: test save pokemon with cp but without hp.
     * Third scenario: test save pokemon without both hp and cp.
     */
    @Test(expected = FormException.class)
    public void testSavePokemon_Without_Stats() throws Exception {
        testPokemon.setCombatPower(null);
        testPokemonEJB.savePokemon(testPokemon); // First test

        testPokemon.setCombatPower(10);
        testPokemon.setHealthPoints(null);
        testPokemonEJB.savePokemon(testPokemon); // Second test

        testPokemon.setCombatPower(null);
        testPokemonEJB.savePokemon(testPokemon); // Third test
    }

    @Test
    public void testFetchAllPokemons() throws Exception {
        List<Pokemon> actualPokemonList = testPokemonEJB.fetchAllPokemons();

        assertNotNull(actualPokemonList);
        assertEquals(testPokemonList, actualPokemonList);
    }

    @Test
    public void testFetchPokemonByOwnerId() throws Exception {
        assertNotNull(testPokemonEJB.fetchPokemonByOwnerId(1L));
        assertEquals(testPokemonList, testPokemonEJB.fetchPokemonByOwnerId(1L));
    }

}