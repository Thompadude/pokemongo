package com.pokemongo.DAO;

import com.pokemongo.model.User;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class UserDAO {

    @PersistenceContext
    EntityManager em;

    public void storeUser(User user) {
        if (em.merge(user) != null) {
            System.out.println("Great Success!!!");
        }
    }

    public User getUser(long userId) {
        return em.find(User.class, userId);
    }

    public List<User> getAllUsers() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

}