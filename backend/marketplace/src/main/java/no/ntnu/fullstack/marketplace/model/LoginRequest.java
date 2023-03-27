package no.ntnu.fullstack.marketplace.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;

/**
 * Login request model for the login endpoint in the API. This model is used to validate the request body. The request body is validated in the TokenController.
 * @param username Username of the user trying to login to the API.
 * @param password Password of the user trying to login to the API. The password gets hashed, and the hash is compared to the hash stored in the database.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record LoginRequest(String username, String password) {

    /**
     * Constructor for the LoginRequest model. This constructor is used to validate the request body. The request body is validated in the TokenController.
     * @param username Username of the user trying to login to the API.
     * @param password Password of the user trying to login to the API. The password gets hashed, and the hash is compared to the hash stored in the database.
     */
    @JsonCreator
    public LoginRequest(@JsonProperty("username") final String username, @JsonProperty("password") final String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Getter for the username of the user trying to login to the API.
     * @return Username of the user trying to login to the API.
     */
    @Override
    @JsonProperty("username")
    public String username() {
        return username;
    }

    /**
     * Getter for the password of the user trying to login to the API.
     * @return Password of the user trying to login to the API.
     */
    @Override
    @JsonProperty("password")
    public String password() {
        return password;
    }

    /**
     * like username() but with prepended get for closer java naming convention
     * @return
     */
    public String getUsername() {
        return this.username();
    }

    /**
     * like password() but with prepended get for closer java naming convention
     * @return
     */
    public String getPassword() {
        return this.password();
    }

}


