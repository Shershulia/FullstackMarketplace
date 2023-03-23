package no.ntnu.fullstack.marketplace.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRequest(
//        @JsonProperty("id") Long id,
        @JsonProperty("username") String username,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("name") String name,
        @JsonProperty("lastname") String lastname,
        @JsonProperty("age") Integer age
) {

    @JsonCreator
    public UserRequest(
//            @JsonProperty("id") Long id,
            @JsonProperty("username") String username,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("name") String name,
            @JsonProperty("lastname") String lastname,
            @JsonProperty("age") Integer age
    ) {
//        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

//    @JsonProperty("id")
//    public Long id() {
//        return id;
//    }

    @JsonProperty("username")
    public String username() {
        return username;
    }

    @JsonProperty("email")
    public String email() {
        return email;
    }

    @JsonProperty("password")
    public String password() {
        return password;
    }

    @JsonProperty("name")
    public String name() {
        return name;
    }

    @JsonProperty("lastname")
    public String lastname() {
        return lastname;
    }

    @JsonProperty("age")
    public Integer age() {
        return age;
    }
}
