package com.pokemongo.utilities;

import java.io.IOException;
import java.net.Authenticator;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.pokemongo.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoogleAuthenticator{

    private static Logger logger;

    User validatedUser;

    public GoogleAuthenticator(){

        logger = LogManager.getLogger(GoogleAuthenticator.class);
        validatedUser = new User();

    }

    /**
     * Verifies the Google token.
     *
     * @param userIdToken is the token to verify.
     * @return true if validation is successful, else catch exception and return false.
     */
    public boolean verifyToken(String userIdToken) {


        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Arrays.asList("85390473584-aead42mt4mhtf5d2brevqd0dan46s0d7.apps.googleusercontent.com"))
                .setIssuer("accounts.google.com")
                .build();


        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(userIdToken);
        } catch (GeneralSecurityException e) {
            logger.error("Security exception when verifying Google token" + e.getMessage());
        } catch (IOException e) {
            logger.error("IO Exception when verifying Google token" + e.getMessage());
        }
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            setValidatedUser(payload);

            logger.info("Google token for " + payload.getSubject() + " validated");
            return true;

        } else {
            logger.error("Invalid Google token");
            return false;
        }

    }

    public User getValidatedUser() {
        return validatedUser;
    }

    public void setValidatedUser(GoogleIdToken.Payload userPayload) {
        validatedUser.setUserName((String) userPayload.get("name"));
        validatedUser.setEmail(userPayload.getEmail());
    }
}
