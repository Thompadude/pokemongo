package com.pokemongo.dataaccessobjects;

import com.pokemongo.model.Pokemon;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Stateful
public class PokemonDAO {

    @PersistenceContext
    private EntityManager em;

    public void savePokemon(Pokemon pokemon) {
        if (em.merge(pokemon) != null) {
            System.out.println("*LOG* " + pokemon.getName() + " saved to database.");
        }
    }

}