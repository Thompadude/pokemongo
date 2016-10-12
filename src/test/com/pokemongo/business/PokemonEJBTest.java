package com.pokemongo.business;

import com.pokemongo.models.Pokemon;
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
    @InjectMocks
    private PokemonEJB testPokemonEJB;
    private Pokemon testPokemon;
    private List<Pokemon> testPokemonList;

    @Before
    public void setUp() throws Exception {
        testPokemonEJB = new PokemonEJB();
        testPokemon = new Pokemon();
        testPokemonList = new ArrayList<>();
        testPokemonList.add(testPokemon);

        MockitoAnnotations.initMocks(this);

        when(mockedPokemonService.savePokemon((testPokemon))).thenReturn(testPokemon);
        when(mockedPokemonService.fetchAllPokemons()).thenReturn(testPokemonList);
    }

    @After
    public void tearDown() throws Exception {
        testPokemonEJB = null;
        testPokemon = null;
    }

    @Test
    public void testSavePokemon() throws Exception {
        Pokemon actualPokemon = testPokemonEJB.savePokemon(testPokemon);

        assertNotNull(testPokemon);
        assertEquals(testPokemon, actualPokemon);
    }

    @Test
    public void testFetchAllPokemons() throws Exception {
        List<Pokemon> actualPokemonList = testPokemonEJB.fetchAllPokemons();

        assertNotNull(actualPokemonList);
        assertEquals(testPokemonList, actualPokemonList);
    }

}