package com.pokemongo.services;

import com.pokemongo.models.User;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class UserService {

    @PersistenceContext
    EntityManager em;

    public void saveUser(User user) {
        if (em.merge(user) != null) {
            System.out.println("*LOG* " + user.getUserName() + " saved to database.");
        }
    }

    public User fetchUser(long userId) {
        return em.find(User.class, userId);
    }

    public List<User> fetchAllUsers() {
        return em.createNamedQuery("User.fetchAll").getResultList();
    }

}