package com.pokemongo.services;

import com.pokemongo.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class UserService {

    @PersistenceContext
    EntityManager em;

    private static final Logger logger = LogManager.getLogger(UserService.class);

    public void saveUser(User user) {
        if (em.merge(user) != null) {
            logger.debug("User saved. Name: {}, E-mail: {}", user.getUserName(), user.getEmail());
        }
    }

    public User fetchUser(long userId) {
        logger.debug("Fetching user by id");
        return em.find(User.class, userId);
    }

    public List<User> fetchAllUsers() {
        logger.debug("Fetching all users");
        return em.createNamedQuery("User.fetchAll").getResultList();
    }

    public User fetchUserByEmail(String email) {
        logger.debug("Fetching user by e-mail");
        return em.createNamedQuery("User.fetchByEmail", User.class).setParameter("email", email).getSingleResult();
    }

}