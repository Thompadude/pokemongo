package com.pokemongo.business;

import com.pokemongo.business.interfaces.PokemonHandler;
import com.pokemongo.models.Pokemon;
import com.pokemongo.models.User;
import com.pokemongo.services.PokemonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;

@Stateless
public class PokemonEJB implements PokemonHandler {

    @EJB
    private PokemonService pokemonService;
    private ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private Map<String, Object> sessionMap = externalContext.getSessionMap();
    private static final Logger logger = LogManager.getLogger(PokemonEJB.class);

    @Override
    public void savePokemon(Pokemon pokemon) {
        // TODO add error handling
        User owner = (User) sessionMap.get("loggedInUser");
        logger.debug(owner);
        pokemon.setOwner(owner);
        pokemonService.savePokemon(pokemon);
    }

    public void selectPokemontoMap(Pokemon pokemon) {

    }

}