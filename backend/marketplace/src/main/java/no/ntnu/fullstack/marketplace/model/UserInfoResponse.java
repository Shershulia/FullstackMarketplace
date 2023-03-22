package no.ntnu.fullstack.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoResponse implements Serializable {

    final private String firstName;
    final private String lastName;
    final private String gender;
    final private Integer age;

    @JsonIgnore
    private String username;
    @JsonIgnore
    private String password;

    public UserInfoResponse(@JsonProperty("firstName") String firstName,
                            @JsonProperty("lastName") String lastName,
                            @JsonProperty("gender") String gender,
                            @JsonProperty("age") Integer age,
                            @JsonProperty("id") Integer id     ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

    public UserInfoResponse(@JsonProperty("firstName") String firstName,
                            @JsonProperty("lastName") String lastName,
                            @JsonProperty("gender") String gender,
                            @JsonProperty("age") Integer age,
                            final String username,
                            final String password
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }


    public String getUsername() {return username;}

    public String getPassword() {return password;}
}