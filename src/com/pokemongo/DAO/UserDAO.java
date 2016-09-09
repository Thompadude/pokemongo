package com.pokemongo.DAO;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.pokemongo.model.User;

@Stateful
public class UserDAO {

    @PersistenceContext
    EntityManager em;

    public void storeUser(User user) {
        if (em.merge(user) != null) {
            System.out.println("Great Success!!!");
        }
    }

}