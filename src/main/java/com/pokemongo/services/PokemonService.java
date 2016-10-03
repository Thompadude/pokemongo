package com.pokemongo.services;

import com.pokemongo.models.Pokemon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class PokemonService {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LogManager.getLogger(PokemonService.class);

    public void savePokemon(Pokemon pokemon) {
        if (em.merge(pokemon) != null) {
            logger.debug("Pokemon saved. Name: {}", pokemon.getName());
        }
    }

}