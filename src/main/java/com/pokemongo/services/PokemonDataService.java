package com.pokemongo.services;

import com.pokemongo.models.PokemonData;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class PokemonDataService {

    @PersistenceContext
    private EntityManager em;

    public PokemonData fetchPokemonDataByPokedexNumber(int pokedexNumber) {
        return em.createNamedQuery("PokemonData.fetchPokemonDataByPokedexNumber", PokemonData.class)
                .setParameter("pokedexNumber", pokedexNumber)
                .getSingleResult();
    }

    public PokemonData fetchPokemonData(long id) {
        return em.find(PokemonData.class, id);
    }

    public List<PokemonData> fetchAllPokemonData() {
        return em.createNamedQuery("PokemonData.fetchAll").getResultList();
    }

}