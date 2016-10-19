package com.pokemongo.services;

import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.Pokemon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class PokemonService {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LogManager.getLogger(PokemonService.class);

    public Pokemon savePokemon(Pokemon pokemon) throws DatabaseException {
        if (em.merge(pokemon) != null) {
            logger.debug("Pokemon saved. Name: {} Owner: {}", pokemon.getName(), pokemon.getOwner().getUserName());
            return pokemon;
        }
        throw new DatabaseException("Error saving pokemon");
    }

    public List<Pokemon> fetchAllPokemons() {
        return em.createNamedQuery("Pokemon.fetchAll").getResultList();
    }

    public List<Pokemon> fetchPokemonByOwnerId(Long id) {
        return em.createNamedQuery("Pokemon.fetchPokemonByOwnerId").setParameter("id", id).getResultList();
    }
    
    public Pokemon fetchPokemonById(Long id) {
        try {
            logger.debug("Fetching Pokemon by id");
            return em.find(Pokemon.class, id);
        } catch (NoResultException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}