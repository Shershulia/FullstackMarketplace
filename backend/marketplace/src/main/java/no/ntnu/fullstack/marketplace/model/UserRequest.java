package no.ntnu.fullstack.marketplace.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User request model for the user endpoint in the API.
 * This is used to translate the request body to java accepted format. The request body is validated and converted to a user object in the UserController.
 * @param username Username of the user.
 * @param email Email of the user.
 * @param password Password of the user.
 * @param name  Name of the user.
 * @param lastname Lastname of the user.
 * @param age Age of the user.
 * @param permission Permission level of the user.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRequest(
//        @JsonProperty("id") Long id,

        /**
         * username of the user.  String username, Should be unique.
         */
        @JsonProperty("username") String username,

        /**
         * email of the user. String email
         */
        @JsonProperty("email") String email,

        /**
         * password of the user. String password
         */
        @JsonProperty("password") String password,

        /**
         * name of the user. String name
         */
        @JsonProperty("name") String name,

        /**
         * lastname of the user. String lastname
         */
        @JsonProperty("lastname") String lastname,

        /**
         * age of the user. Integer age
         */
        @JsonProperty("age") Integer age,

        /**
         * permission level of the user. String permission
         */
        @JsonProperty("permission") String permission

) {

    @JsonCreator
    public UserRequest(
//            @JsonProperty("id") Long id,
            @JsonProperty("username") String username,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("name") String name,
            @JsonProperty("lastname") String lastname,
            @JsonProperty("age") Integer age,
            @JsonProperty("permission") String permission

    ) {
//        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.permission=permission;
    }

//    @JsonProperty("id")
//    public Long id() {
//        return id;
//    }

    /**
     * Getter for the username of the user.
     * @return Username of the user. Should be unique, and not null. is String.
     */
    @JsonProperty("username")
    public String username() {
        return username;
    }

    /**
     * Getter for the email of the user.
     * @return Email of the user. Should be not null. is String.
     */
    @JsonProperty("email")
    public String email() {
        return email;
    }

    /**
     * Getter for the password of the user.
     * @return Password of the user. Should be not null. is String.
     */
    @JsonProperty("password")
    public String password() {
        return password;
    }

    /**
     * Getter for the name of the user.
     * @return Name of the user. is String.
     */
    @JsonProperty("name")
    public String name() {
        return name;
    }

    /**
     * Getter for the lastname of the user.
     * @return Lastname of the user. is String.
     */
    @JsonProperty("lastname")
    public String lastname() {
        return lastname;
    }

    /**
     * Getter for the age of the user.
     * @return Age of the user. is Integer.
     */
    @JsonProperty("age")
    public Integer age() {
        return age;
    }
    /**
     * Getter for the permission level of the user.
     * @return Permission level of the user. is String.
     */
    @JsonProperty("permission")
    public String permission() {
        return permission;
    }
}
