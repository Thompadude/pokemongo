package com.pokemongo.controllers;

import com.pokemongo.business.interfaces.PokemonHandler;
import com.pokemongo.business.interfaces.UserHandler;
import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.models.Pokemon;
import com.pokemongo.models.User;
import com.pokemongo.utilities.GoogleAuthenticator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.List;

@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    private static final long serialVersionUID = 288653009259087195L;

    private boolean isUserLoggedIn;
    private String userName;
    private String email;
    private String tokenId;
    private List<Pokemon> pokemons;

    private String theme;
    private String team;

    private String profileImageUrl;
    @EJB
    private UserHandler userHandler;
    @EJB
    private PokemonHandler pokemonHandler;

    private static Logger logger = LogManager.getLogger(UserController.class);

    GoogleAuthenticator googleAuthenticator;

    @PostConstruct
    public void init() {

        logger.debug("@PostConstruct executed");

        isUserLoggedIn = false;
    }

    public void validateBeforeLogin() throws IOException {
        googleAuthenticator = new GoogleAuthenticator();

        boolean isGoogleTokenValid = googleAuthenticator.verifyToken(tokenId);

        if (isGoogleTokenValid){

            try {
                logIn(googleAuthenticator.getValidatedUser());
            } catch (DatabaseException e) {
                logger.error("Error in getting validated user");
            }

        }else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("invalidToken.xhtml");
            } catch (IOException e) {
                logger.error("Error while redirecting to invalidToken page");
            }
        }

    }


    public boolean logIn(User user) throws DatabaseException {

            if (isUserLoggedIn == false) {
                System.out.println("Hej hej för fan");
                logger.debug("User is logging in");
                isUserLoggedIn = userHandler.logIn(user);
                User currentUser = userHandler.fetchUserByEmail(user.getEmail());
                setUserName(currentUser.getUserName());
                setEmail(currentUser.getEmail());
                setTeam(currentUser.getTeam());

                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                } catch (IOException e) {
                    logger.error("Error while updating index when ligging in");
                }
            }

            return isUserLoggedIn;

    }

    public boolean logOut() {
        logger.debug("User is logging out");
        setIsUserLoggedIn(userHandler.logOut());
        return isUserLoggedIn;
    }

    public void changeTeam(ValueChangeEvent event) throws DatabaseException {
        logger.debug("Change team method called");
        User userToChange = userHandler.fetchUserByEmail(email);
        String newTeam = (String) event.getNewValue();
        setTeam(newTeam);
        userToChange.setTeam(getTeam());
        userHandler.saveUser(userToChange);
    }
    
    /* Getters and Setters */

    public boolean getIsUserLoggedIn() {
        return isUserLoggedIn;
    }

    public void setIsUserLoggedIn(boolean userLoggedIn) {
        isUserLoggedIn = userLoggedIn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public List<Pokemon> getPokemons() {
        pokemons = pokemonHandler.fetchPokemonByOwnerId(userHandler.getLoggedInUser().getId());
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
        setTheme(team);
    }

    public String getTheme() {
        if (team != null){
            return team.toLowerCase() + ".css";
        }else {
            return team;
        }
    }

    public void setTheme(String theme) {
        team = theme;
    }

    public String getProfileImageUrl() {
        profileImageUrl = "/images/" + userHandler.getLoggedInUser().getUserImageName();
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}