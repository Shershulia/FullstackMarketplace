package no.ntnu.fullstack.marketplace.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record LoginRequest(String username, String password) {

    @JsonCreator
    public LoginRequest(@JsonProperty("username") final String username, @JsonProperty("password") final String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    @JsonProperty("username")
    public String username() {
        return username;
    }

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


