package com.pokemongo.business.interfaces;

import com.pokemongo.models.Pokemon;

import javax.ejb.Local;

@Local
public interface LocalPokemon {

    void savePokemon(Pokemon pokemon);

}