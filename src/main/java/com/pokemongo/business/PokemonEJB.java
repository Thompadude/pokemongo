package com.pokemongo.business;

import com.pokemongo.business.interfaces.PokemonHandler;
import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.exceptions.FormException;
import com.pokemongo.exceptions.UserException;
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

    /**
     * Handles logic before saving the pokemon to the database.
     *
     * @param pokemon is the pokemon to save.
     * @return the saved pokemon if save is successful.
     * @throws DatabaseException if something goes wrong while saving the pokemon to the database.
     * @throws UserException if the user is not logged in while trying to save the pokemon.
     * @throws FormException if some values are missing while trying to save the pokemon.
     */
    @Override
    public Pokemon savePokemon(Pokemon pokemon) throws DatabaseException, UserException, FormException {
        if (pokemon.getLat().equals("") || pokemon.getLng().equals("")) {
            throw new FormException("No pokemon position picked on map");
        }

        if (pokemon.getCombatPower() == null || pokemon.getHealthPoints() == null) {
            throw new FormException("No pokemon stats are entered");
        }

        User owner = userHandler.getLoggedInUser();
        if (owner != null) {
            pokemon.setOwner(owner);
            pokemon = pokemonService.savePokemon(pokemon);
            return pokemon;
        }

        throw new UserException("You must be logged in to add pokemon");
    }

    @Override
    public List<Pokemon> fetchAllPokemons() {
        return pokemonService.fetchAllPokemons();
    }

    @Override
    public List<Pokemon> fetchPokemonByOwnerId(Long id) {
        return pokemonService.fetchPokemonByOwnerId(id);
    }

}