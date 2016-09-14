package com.pokemongo.services;

import com.pokemongo.models.Pokemon;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class PokemonService {

    @PersistenceContext
    private EntityManager em;

    public void savePokemon(Pokemon pokemon) {
        if (em.merge(pokemon) != null) {
            System.out.println("*LOG* " + pokemon.getName() + " saved to database.");
        }
    }

}