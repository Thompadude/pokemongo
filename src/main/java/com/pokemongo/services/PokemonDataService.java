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

    public List<PokemonData> fetchAllPokemonData() {
        return em.createNamedQuery("PokemonData.fetchAll").getResultList();
    }

}