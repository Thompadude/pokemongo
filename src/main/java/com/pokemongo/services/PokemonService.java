package com.pokemongo.services;

import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.Pokemon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class PokemonService {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LogManager.getLogger(PokemonService.class);
    public Pokemon savePokemon(Pokemon pokemon) throws DatabaseException {
        if (em.merge(pokemon) != null) {
            logger.debug("Pokemon saved. Name: {}", pokemon.getName());
            return pokemon;
        }
        throw new DatabaseException("Error saving pokemon");
    }
    
    public List<Pokemon> fetchAllPokemons() {
        return em.createNamedQuery("Pokemon.fetchAll").getResultList();
    }

}