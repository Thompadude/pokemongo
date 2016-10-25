package com.pokemongo.services;

import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class UserService {

    @PersistenceContext
    EntityManager em;

    private static final Logger logger = LogManager.getLogger(UserService.class);

    public User saveUser(User user) throws DatabaseException {
        if (em.merge(user) != null) {
            logger.debug("User saved. Name: {}, E-mail: {}", user.getUserName(), user.getEmail());
            return user;
        } else {
            logger.error("Error saving user");
        }
        throw new DatabaseException("Error saving user");
    }

    public User fetchUser(long userId) {
        try {
            logger.debug("Fetching user by id");
            return em.find(User.class, userId);
        } catch (NoResultException e) {
            logger.error(e.getMessage());
            return null;
        }
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