package com.pokemongo.business;

import com.pokemongo.models.Pokemon;
import com.pokemongo.services.PokemonService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class PokemonEJBTest {

    @Mock
    private PokemonService mockedPokemonService;
    @InjectMocks
    private PokemonEJB testPokemonEJB;
    private Pokemon testPokemon;

    @Before
    public void setUp() throws Exception {
        testPokemonEJB = new PokemonEJB();
        testPokemon = new Pokemon();
        testPokemon.setName("Test");

        MockitoAnnotations.initMocks(this);

        when(mockedPokemonService.savePokemon((testPokemon))).thenReturn(testPokemon);
    }

    @After
    public void tearDown() throws Exception {
        testPokemonEJB = null;
        testPokemon = null;
    }

    @Test
    public void testSavePokemon() throws Exception {
        assertEquals(testPokemon, testPokemonEJB.savePokemon(testPokemon));
        assertNotNull(testPokemon);
    }

}