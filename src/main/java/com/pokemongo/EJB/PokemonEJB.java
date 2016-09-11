package com.pokemongo.EJB;

import com.pokemongo.DAO.PokemonDAO;
import com.pokemongo.EJB.interfaces.LocalPokemon;
import com.pokemongo.model.Pokemon;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PokemonEJB implements LocalPokemon {

    @EJB
    private PokemonDAO pokemonDAO;

    @Override
    public void storePokeMon(Pokemon pokemon) {
        pokemonDAO.storePokemon(pokemon);
    }
}