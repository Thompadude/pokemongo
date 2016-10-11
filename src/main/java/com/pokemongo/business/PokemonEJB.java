package com.pokemongo.business;

import com.pokemongo.business.interfaces.PokemonHandler;
import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.exceptions.UserNotLoggedInException;
import com.pokemongo.models.Pokemon;
import com.pokemongo.models.User;
import com.pokemongo.services.PokemonService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PokemonEJB implements PokemonHandler {

    @EJB
    private PokemonService pokemonService;
    @EJB
    private UserHandler userHandler;

    @Override
    public Pokemon savePokemon(Pokemon pokemon) throws DatabaseException, UserNotLoggedInException {
        User owner = userHandler.getLoggedInUser();

        if (owner != null) {
            pokemon.setOwner(owner);
            pokemon = pokemonService.savePokemon(pokemon);
            return pokemon;
        }
        throw new UserNotLoggedInException("You must be logged in to add pokemon");
    }

    @Override
    public List<Pokemon> fetchAllPokemons() {
        return pokemonService.fetchAllPokemons();
    }

}