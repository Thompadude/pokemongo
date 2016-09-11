package com.pokemongo.DAO;

import com.pokemongo.model.Pokemon;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class PokemonDAO {

    @PersistenceContext
    private EntityManager em;

    public void storePokemon(Pokemon pokemon) {
        if (em.merge(pokemon) != null) {
            System.out.println(pokemon.getName() + " stored in database.");
        }
    }

}