package com.pokemongo.enterprisejavabeans.interfaces;

import com.pokemongo.model.Pokemon;

import javax.ejb.Local;

@Local
public interface LocalPokemon {

    void savePokemon(Pokemon pokemon);

}