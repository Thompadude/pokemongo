package com.pokemongo.services;

import com.pokemongo.models.Pokemon;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class PokemonService {

    @PersistenceContext
    private EntityManager em;

    public void savePokemon(Pokemon pokemon) {
        if (em.merge(pokemon) != null) {
            System.out.println("*LOG* " + pokemon.getName() + " saved to database.");
        }
    }
    
    public List<Pokemon> fetchAllPokemons() {
        return em.createNamedQuery("Pokemon.fetchAll").getResultList();
    }

}