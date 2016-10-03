package com.pokemongo.services;

import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.Pokemon;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class PokemonService {

    @PersistenceContext
    private EntityManager em;

    public Pokemon savePokemon(Pokemon pokemon) throws DatabaseException {
        if (em.merge(pokemon) != null) {
            System.out.println("*LOG* " + pokemon.getName() + " saved to database.");
            return pokemon;
        }
        throw new DatabaseException("Error saving pokemon");
    }

}