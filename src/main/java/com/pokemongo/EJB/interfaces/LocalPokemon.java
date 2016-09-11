package com.pokemongo.EJB.interfaces;

import com.pokemongo.model.Pokemon;

import javax.ejb.Local;

@Local
public interface LocalPokemon {

    void storePokeMon(Pokemon pokemon);

}