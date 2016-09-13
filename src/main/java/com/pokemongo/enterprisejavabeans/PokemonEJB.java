package com.pokemongo.enterprisejavabeans;

import com.pokemongo.dataaccessobjects.PokemonDAO;
import com.pokemongo.enterprisejavabeans.interfaces.LocalPokemon;
import com.pokemongo.model.Pokemon;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PokemonEJB implements LocalPokemon {

    @EJB
    private PokemonDAO pokemonDAO;

    @Override
    public void savePokemon(Pokemon pokemon) {
        pokemonDAO.savePokemon(pokemon);
    }

}