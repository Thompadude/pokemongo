package com.pokemongo.services;

import com.pokemongo.models.PokemonData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class PokemonDataService {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LogManager.getLogger(PokemonDataService.class);

    public PokemonData fetchPokemonDataByPokedexNumber(int pokedexNumber) {
        try {
            return em.createNamedQuery("PokemonData.fetchPokemonDataByPokedexNumber", PokemonData.class)
                    .setParameter("pokedexNumber", pokedexNumber)
                    .getSingleResult();
        } catch (NoResultException e) {
            logger.error("No pokemon found with that pokedex number");
            return null;
        }
    }

    public PokemonData fetchPokemonData(long id) {
        return em.find(PokemonData.class, id);
    }

    public List<PokemonData> fetchAllPokemonData() {
        return em.createNamedQuery("PokemonData.fetchAll").getResultList();
    }

}